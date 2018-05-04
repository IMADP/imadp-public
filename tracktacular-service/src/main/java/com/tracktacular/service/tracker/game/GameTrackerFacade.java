package com.tracktacular.service.tracker.game;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * GameTrackerFacade
 *
 * Provides functionality for game tracking.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public interface GameTrackerFacade extends TrackerFacade {

	/**
	 * Gets a game by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Game
	 */
	public Game getGame(User user, String uid);

	/**
	 * Saves a game.
	 *
	 * @param game
	 */
	public void saveGame(Game game);

	/**
	 * Deletes a game.
	 *
	 * @param game
	 */
	public void deleteGame(Game game);

	/**
	 * Adds new games from Steam to a users's existing library.
	 *
	 * @param preferences
	 * @return int
	 */
	public int synchronizeSteamGames(Preferences preferences);

	/**
	 * Finds a List of active Games for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Games
	 */
	public Games findActiveGames(User user, String sortProperty);

	/**
	 * Finds a List of deleted Games for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Game>
	 */
	public List<Game> findDeletedGames(User user, CriteriaParams<Game> criteriaParams);

	/**
	 * Finds the count of all deleted Games for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedGameCount(User user);

}