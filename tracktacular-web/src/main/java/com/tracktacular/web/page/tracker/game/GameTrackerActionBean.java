package com.tracktacular.web.page.tracker.game;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.game.Game;
import com.tracktacular.service.tracker.game.GameTrackerFacade;
import com.tracktacular.service.tracker.game.GameTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * GameTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class GameTrackerActionBean extends TrackerActionBean<GameTrackerFacade, GameTrackerPreferences> {
	private Game game;

	@Override
	public Tracker getTracker() {
		return Tracker.GAME;
	}

	/**
	 * Save or updates a Game.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveGame() {
		Game game = getGame();
		populatePersistableUser(game);
		getTrackerFacade().saveGame(game);

		if(game.isActiveState())
			getContext().addSuccessMessage("game.saveGame.success", game.getTitle());

		else if(game.isDeletedState())
			getContext().addSuccessMessage("game.deleteGame.success", game.getTitle());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Game.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteGame() {
		Game game = getGame();
		getTrackerFacade().deleteGame(game);
		getContext().addSuccessMessage("game.deleteGame.success", game.getTitle());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

}