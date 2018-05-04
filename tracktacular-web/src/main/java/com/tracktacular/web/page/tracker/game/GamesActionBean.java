package com.tracktacular.web.page.tracker.game;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.game.Games;


/**
 * GamesActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-game/games/by-{sortProperty=title}")
public class GamesActionBean extends GameTrackerActionBean {

	// properties
	private String sortProperty;
	private Games games;

	/**
	 * Returns a list of games.
	 *
	 * @return Games
	 */
	public Games getGames() {
		if(games == null)
			games = getTrackerFacade().findActiveGames(getTrackerUser(), sortProperty);

		return games;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}