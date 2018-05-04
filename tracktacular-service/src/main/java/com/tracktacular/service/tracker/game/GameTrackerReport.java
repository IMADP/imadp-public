package com.tracktacular.service.tracker.game;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * GameTrackerReport
 *
 * Contains report information.
 *
 * @platform Anthony DePalma
 * @version 1.0
 */
public final class GameTrackerReport extends AbstractTrackerReport {
	private final Games games;
	private final List<Game> unratedGames;
	private final List<Game> targetDateGames;

    // constructor
    public GameTrackerReport(GameTrackerPreferences preferences, Games games) {
    	this.games = games;
    	this.unratedGames = games.getUnratedGames();
    	this.targetDateGames = Lists.newArrayList();

    	if(preferences.isAlertOnTargetDates())
    	  	for(GameCategory gameCategory : games.getGameCategories())
	    		for(Game game : gameCategory.getItems())
	    			if(isCurrentDate(game.getTargetDate()))
	    				targetDateGames.add(game);
    }

    @Override
	public boolean isEnabled() {
    	return getGameCount() > 0;
    }

    @Override
    public int getAlertCount() {
    	return targetDateGames.size();
    }

    /**
     * Returns the count of games.
     *
     * @return int
     */
    public int getGameCount() {
		return games.getGameCount();
	}

    /**
     * Returns the count of unrated games.
     *
     * @return int
     */
    public int getUnratedGameCount() {
		return unratedGames.size();
	}

    // getters
	public List<Game> getUnratedGames() {
		return unratedGames;
	}

	public List<Game> getTargetDateGames() {
		return targetDateGames;
	}

}