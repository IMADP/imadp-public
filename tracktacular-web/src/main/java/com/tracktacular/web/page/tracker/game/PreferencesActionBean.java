package com.tracktacular.web.page.tracker.game;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.web.stripes.AbstractAction;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * PreferencesActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-game/preferences")
public class PreferencesActionBean extends GameTrackerActionBean {

	// actions
	private SynchronizeSteamGamesAction synchronizeSteamGamesAction;

	/**
	 * Handles the submission of the synchronizeSteamGames form.
	 *
	 * @return Resolution
	 */
	public final Resolution synchronizeSteamGames() {
		return synchronizeSteamGamesAction.act(this);
	}

	// getters and setters
	public SynchronizeSteamGamesAction getSynchronizeSteamGamesAction() {
		return synchronizeSteamGamesAction;
	}

	public void setSynchronizeSteamGamesAction(SynchronizeSteamGamesAction synchronizeSteamGamesAction) {
		this.synchronizeSteamGamesAction = synchronizeSteamGamesAction;
	}

	/**
	 * SynchronizeSteamGamesAction
	 *
	 * The action for synchronizing Steam games.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public static class SynchronizeSteamGamesAction extends AbstractAction<PreferencesActionBean> {
		private int addedGames;

		@Override
		protected void doAction(PreferencesActionBean actionBean) {
			addedGames = actionBean.getTrackerFacade().synchronizeSteamGames(actionBean.getUserPreferences());
		}

		@Override
		protected Resolution getResolution(PreferencesActionBean actionBean) {
			actionBean.getContext().addSuccessMessage("game.synchronizeSteamGames.success", addedGames);

			return new RedirectResolution(PreferencesActionBean.class)
				.addParameter(TrackerActionBean.TRACKER_USER_USERNAME_PARAM, actionBean.getTrackerUserUsername());
		}

	}

}