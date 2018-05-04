package com.tracktacular.web.page.tracker.birthday;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.web.page.tracker.TrackerActionBean;
import com.tracktacular.web.page.tracker.TrackerPage;

/**
 * BirthdayTrackerPage
 *
 * Enumerated page values.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public enum BirthdayTrackerPage implements TrackerPage {
	ABOUT(AboutActionBean.class),
	BIRTHDAYS(BirthdaysActionBean.class),
	REPORT(ReportActionBean.class),
	PREFERENCES(PreferencesActionBean.class),
	DELETED(DeletedActionBean.class);

	private Class<? extends BirthdayTrackerActionBean> actionBean;

	// constructor
    private BirthdayTrackerPage(Class<? extends BirthdayTrackerActionBean> actionBean) {
        this.actionBean = actionBean;
    }

    @Override
    public Tracker getTracker() {
    	return Tracker.BIRTHDAY;
    }

    @Override
    public String getTrackerPageKey() {
    	return getClass().getSimpleName() + '.' + name();
    }

    @Override
	public TrackerPage getDefault() {
		return BIRTHDAYS;
	}

	@Override
	public Class<? extends TrackerActionBean<?,?>> getActionBean() {
		return actionBean;
	}

}