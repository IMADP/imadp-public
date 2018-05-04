package com.tracktacular.service.tracker.game;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.account.TracktacularAccountFacade;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.TrackerReport;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * GameTrackerFacadeImpl
 *
 * The standard implementation of the GameTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public final class GameTrackerFacadeImpl extends AbstractTrackerFacade
	implements GameTrackerFacade {

	private GameService gameService;
	private TracktacularAccountFacade accountFacade;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(accountFacade);
		Validate.notNull(gameService);
	}

	@Override
	public Game getGame(User user, String uid) {
		return gameService.findFirstByUser(user, uid);
	}

	@Override
	public void saveGame(Game game) {
		new GameValidator("game", game, findActiveGames(game.getUser(), Game.TITLE.getName())).validate();
		gameService.save(game);
	}

	@Override
	public void deleteGame(Game game) {
		gameService.delete(game);
	}

	@Override
	public Games findActiveGames(User user, String sortProperty) {
		List<Game> activeGames = gameService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Game>of(Results.ALL));

		return new Games(activeGames, sortProperty);
	}

	@Override
	public List<Game> findDeletedGames(User user, CriteriaParams<Game> criteriaParams) {
		return gameService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedGameCount(User user) {
		return gameService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Game> games = gameService.findByUser(user, CriteriaParams.<Game>of(Results.ALL));

		for(Game game : games)
			deleteGame(game);
	}

	@Override
	public int synchronizeSteamGames(Preferences preferences) {
		User user = preferences.getUser();
		String steamId = preferences.getTrackers().getGameTrackerPreferences().getSteamId();

		if(steamId == null)
			return 0;

		logger.info("Adding new steam games for user [{}]", user.getUid());

		// get all steam games
		List<Game> steamGames = gameService.getSteamGames(user, steamId);

		// get all active and inactive games
		Games activeGames = findActiveGames(user, Game.TITLE.getName());
		Games deletedGames = new Games(findDeletedGames(user, CriteriaParams.<Game>of(Results.ALL)), Game.TITLE.getName());

		// find missing games
		List<Game> missingGames = activeGames.getMissingGames(steamGames);
		missingGames = deletedGames.getMissingGames(missingGames);

		for(Game game : missingGames)
			saveGame(game);

		return missingGames.size();
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new GameTrackerDemo(this);
	}

	@Override
	public TrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Games games = findActiveGames(user, Game.TITLE.getName());
		return new GameTrackerReport(preferences.getTrackers().getGameTrackerPreferences(), games);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active games
		Games games = findActiveGames(user, Game.TITLE.getName());

		for(GameCategory category : games.getGameCategories())
			for(Game game : category.getItems())
			{
				if(game.getTargetDate() != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, game.getTargetDate());
					calendarEntry.setTracker(Tracker.GAME);
					calendarEntry.setName(String.format("Game: %s", game.getTitle()));
					calendarEntry.setDescription(game.getPlatform());
					calendarEntries.add(calendarEntry);
				}
			}

		return calendarEntries;
	}

	// getters and setters
	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public TracktacularAccountFacade getAccountFacade() {
		return accountFacade;
	}

	public void setAccountFacade(TracktacularAccountFacade accountFacade) {
		this.accountFacade = accountFacade;
	}

}