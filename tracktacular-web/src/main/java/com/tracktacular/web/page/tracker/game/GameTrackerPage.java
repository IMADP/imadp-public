package com.tracktacular.web.page.tracker.game;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.web.page.tracker.TrackerActionBean;
import com.tracktacular.web.page.tracker.TrackerPage;

/**
 * GameTrackerPage
 *
 * Enumerated page values.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public enum GameTrackerPage implements TrackerPage {
	ABOUT(AboutActionBean.class),
	GAMES(GamesActionBean.class),
	REPORT(ReportActionBean.class),
	PREFERENCES(PreferencesActionBean.class),
	DELETED(DeletedActionBean.class);

	private Class<? extends GameTrackerActionBean> actionBean;

	// constructor
    private GameTrackerPage(Class<? extends GameTrackerActionBean> actionBean) {
        this.actionBean = actionBean;
    }

    @Override
    public Tracker getTracker() {
    	return Tracker.GAME;
    }

    @Override
    public String getTrackerPageKey() {
    	return getClass().getSimpleName() + '.' + name();
    }

    @Override
	public TrackerPage getDefault() {
		return GAMES;
	}

	@Override
	public Class<? extends TrackerActionBean<?,?>> getActionBean() {
		return actionBean;
	}

}