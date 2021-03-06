package com.tracktacular.web.page.tracker.exercise;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.web.page.tracker.TrackerActionBean;
import com.tracktacular.web.page.tracker.TrackerPage;

/**
 * ExerciseTrackerPage
 *
 * Enumerated page values.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public enum ExerciseTrackerPage implements TrackerPage {
	ABOUT(AboutActionBean.class),
	ROUTINES(RoutinesActionBean.class),
	COMPLETED(CompletedActionBean.class),
	REPORT(ReportActionBean.class),
	PREFERENCES(PreferencesActionBean.class),
	DELETED(DeletedActionBean.class);

	private Class<? extends ExerciseTrackerActionBean> actionBean;

	// constructor
    private ExerciseTrackerPage(Class<? extends ExerciseTrackerActionBean> actionBean) {
        this.actionBean = actionBean;
    }

    @Override
    public Tracker getTracker() {
    	return Tracker.EXERCISE;
    }

    @Override
    public String getTrackerPageKey() {
    	return getClass().getSimpleName() + '.' + name();
    }

    @Override
	public TrackerPage getDefault() {
		return ROUTINES;
	}

	@Override
	public Class<? extends TrackerActionBean<?,?>> getActionBean() {
		return actionBean;
	}

}