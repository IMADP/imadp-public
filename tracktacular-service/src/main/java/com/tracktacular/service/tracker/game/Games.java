package com.tracktacular.service.tracker.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.imadp.core.AbstractSerializable;


/**
 * Games
 *
 * A collection of a games.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public class Games extends AbstractSerializable {

	// game count
	private final int gameCount;

	// list of all games
	private final List<Game> games;

	// list of games by category
	private final Collection<GameCategory> gameCategories;

	// constructor
	public Games(List<Game> games, String sortProperty) {
		this.games = games;
		this.gameCount = games.size();
		this.gameCategories = getGameCategories(games, sortProperty);
	}

	/**
	 * Categorizes and returns a list of GameCategories based on the sortProperty.
	 *
	 * @param games
	 * @param sortProperty
	 * @return Collection<GameCategory>
	 */
	private Collection<GameCategory> getGameCategories(List<Game> games, String sortProperty) {

		// platform
		if(Game.PLATFORM.getName().equalsIgnoreCase(sortProperty))
			return getGameCategoriesByPlatform(games);

		// tag
		if(Game.TAG.getName().equalsIgnoreCase(sortProperty))
			return getGameCategoriesByTag(games);

		// rating
		if(Game.RATING.getName().equalsIgnoreCase(sortProperty))
			return getGameCategoriesByRating(games);

		// platform
		return getGameCategoriesByTitle(games);
	}

	/**
	 * Returns a collection of game categories by title.
	 *
	 * @param games
	 * @return Collection<GameCategory>
	 */
	private Collection<GameCategory> getGameCategoriesByTitle(List<Game> games) {
		Collections.sort(games, Game.TITLE_COMPARATOR);

		Map<String, GameCategory> categoryMap = new LinkedHashMap<>();

		for(Game game : games)
			getGameCategory(categoryMap, game.getTitle().substring(0, 1)).addItem(game);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of game categories by platform.
	 *
	 * @param games
	 * @return Collection<GameCategory>
	 */
	private Collection<GameCategory> getGameCategoriesByPlatform(List<Game> games) {
		Collections.sort(games, Game.PLATFORM_COMPARATOR);

		Map<String, GameCategory> categoryMap = new LinkedHashMap<>();

		for(Game game : games)
			getGameCategory(categoryMap, game.getPlatform()).addItem(game);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of game categories by tag.
	 *
	 * @param games
	 * @return Collection<GameCategory>
	 */
	private Collection<GameCategory> getGameCategoriesByTag(List<Game> games) {
		Collections.sort(games, Game.TITLE_COMPARATOR);

		Map<String, GameCategory> categoryMap = new LinkedHashMap<>();

		for(Game game : games)
			if(!StringUtils.isBlank(game.getTag()))
				for(String tag : game.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getGameCategory(categoryMap, tag).addItem(game);

		List<GameCategory> gameCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(gameCategories);
		return gameCategories;
	}

	/**
	 * Returns a collection of game categories by rating.
	 *
	 * @param games
	 * @return Collection<GameCategory>
	 */
	private Collection<GameCategory> getGameCategoriesByRating(List<Game> games) {
		Collections.sort(games, Game.RATING_COMPARATOR);

		Map<String, GameCategory> categoryMap = new LinkedHashMap<>();
		getGameCategory(categoryMap, "Unrated");

		for(Game game : games)
		{
			String categoryName = "Unrated";

			if(game.isCompleted())
				categoryName = game.getRating() + "/10";

			getGameCategory(categoryMap, categoryName).addItem(game);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a GameCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return GameCategory
	 */
	private GameCategory getGameCategory(Map<String, GameCategory> categoryMap, String categoryName) {
		GameCategory gameCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(gameCategory == null)
		{
			gameCategory = new GameCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), gameCategory);
		}

		return gameCategory;
	}

	/**
	 * Returns a list of unrated games.
	 *
	 * @return List<Game>
	 */
	public List<Game> getUnratedGames() {
		List<Game> unratedGames = new ArrayList<>();

		for(Game game : games)
			if(!game.isCompleted())
				unratedGames.add(game);

		Collections.sort(unratedGames, Game.TITLE_COMPARATOR);

		return unratedGames;
	}

	/**
	 * Returns the count of all games.
	 *
	 * @return int
	 */
	public int getGameCount() {
		return gameCount;
	}

	/**
	 * Returns a collection of GameCategories.
	 *
	 * @return Collection<GameCategory>
	 */
	public Collection<GameCategory> getGameCategories() {
		return gameCategories;
	}

	/**
	 * Returns a list of games not currently found in a game collection.
	 *
	 * @param externalGames
	 * @return List<Game>
	 */
	public List<Game> getMissingGames(List<Game> externalGames) {

		// create a map to easily identify games by title slug
		Map<String, Game> gameMap = Maps.newHashMap();

		for(Game game : games)
			gameMap.put(game.getTitleSlug(), game);

		List<Game> missingGames = Lists.newArrayList();

		for(Game game : externalGames)
			if(!gameMap.containsKey(game.getTitleSlug()))
				missingGames.add(game);

		return missingGames;
	}

	/**
	 * Returns true if the given game was found in the games collection, according to title and platform.
	 *
	 * @param otherGame
	 * @return boolean
	 */
	public boolean hasDuplicate(Game otherGame) {
		for(Game game : games)
			if(StringUtils.equals(toSlug(game.getTitle()), toSlug(otherGame.getTitle())))
				if(StringUtils.equals(toSlug(game.getPlatform()), toSlug(otherGame.getPlatform())))
					return !game.equals(otherGame);

		return false;
	}

}