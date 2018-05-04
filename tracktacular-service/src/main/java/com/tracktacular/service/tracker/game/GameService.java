package com.tracktacular.service.tracker.game;

import java.util.List;

import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;

/**
 * GameService
 *
 * Provides common retrieval operations for Game objects.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public interface GameService extends PersistableUserService<Game> {

	/**
	 * Returns a list of games associated with a user's steam account.
	 *
	 * @param user
	 * @param steamId
	 * @return List<Game>
	 */
	public List<Game> getSteamGames(User user, String steamId);

}