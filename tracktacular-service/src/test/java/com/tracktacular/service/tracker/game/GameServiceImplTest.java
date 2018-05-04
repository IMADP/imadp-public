package com.tracktacular.service.tracker.game;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * GameServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GameServiceImplTest extends TracktacularServiceTestCase {
	Game game;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		game = new Game(user);
		game.setTitle("title");
		game.setPlatform("platform");
		game.setTag("tag");
		game.setRating(5);
		game.setTargetDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(game, game_gameService);
	}

	@Test
	public void getSteamGames() {
		List<Game> games = game_gameService.getSteamGames(user, "76561197960440092");

		assertFalse(games.isEmpty());
	}

}