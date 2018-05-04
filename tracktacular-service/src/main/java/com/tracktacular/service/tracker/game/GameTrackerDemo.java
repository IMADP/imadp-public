package com.tracktacular.service.tracker.game;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * GameTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public class GameTrackerDemo extends AbstractTrackerDemo {

	private GameTrackerFacade trackerFacade;

	// constructor
	public GameTrackerDemo(GameTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Game game;

		// target date
		addGame(user, "Halo 4", "Xbox 360", "FPS", null, null);

		// completed
		addGame(user, "Halo", "Xbox", "FPS", 8, null);
		addGame(user, "Halo 2", "Xbox", "FPS", 7, null);
		addGame(user, "Halo 3", "Xbox 360", "FPS", 6, null);

		addGame(user, "Skyrim", "Xbox 360", "Action, RPG", 8, null);
		addGame(user, "Gears of War", "Xbox 360", "FPS", 6, null);

		addGame(user, "Earthbound", "SNES", "RPG", 8, null);
		addGame(user, "Super Mario RPG", "SNES", "RPG", 8, null);
		addGame(user, "Final Fantasy 3", "SNES", "RPG", 10, null);
		addGame(user, "Chrono Trigger", "SNES", "RPG", 9, null);

		addGame(user, "The Legend of Zelda: Ocarina of Time ", "Nintendo 64", "Action, Adventure", 10, null);
		addGame(user, "Super Mario 64 ", "Nintendo 64", "Platforming", 8, null);
		addGame(user, "Super Smash Bros", "Nintendo 64", "Fighting", 10, null);

		addGame(user, "Settlers of Catan", "Board Games", "Strategy", 7, null);
		addGame(user, "Risk", "Board Games", "Strategy", 8, null);
		addGame(user, "Carcassonne", "Board Games", "Strategy", 7, null);
		addGame(user, "Dominion", "Board Games", "Strategy", 5, null);

		addGame(user, "Super Mario Sunshine", "Gamecube", "Platforming", 4, null);
		addGame(user, "Metroid Prime", "Gamecube", "FPS, Action, Adventure", 9, null);

		game = addGame(user, "Secret of Mana", "SNES", "RPG", 10, null);
		game.setNotes("Legendary game in the golden era of RPGs.");
		trackerFacade.saveGame(game);
	}

	/**
	 * Adds a game.
	 *
	 * @param user
	 * @param title
	 * @param platform
	 * @param tag
	 * @param rating
	 * @param targetDate
	 * @return game
	 */
	private Game addGame(User user, String title, String platform, String tag, Integer rating, DateTime targetDate) {
		Game game = new Game(user);
		game.setTitle(title);
		game.setPlatform(platform);
		game.setTag(tag);
		game.setRating(rating);
		game.setTargetDate(targetDate);

		trackerFacade.saveGame(game);

		return game;
	}

}
