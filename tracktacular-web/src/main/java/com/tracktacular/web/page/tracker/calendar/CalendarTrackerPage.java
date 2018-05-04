package com.tracktacular.web.page.tracker.calendar;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.web.page.tracker.TrackerActionBean;
import com.tracktacular.web.page.tracker.TrackerPage;

/**
 * CalendarTrackerPage
 *
 * Enumerated page values.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public enum CalendarTrackerPage implements TrackerPage {
	ABOUT(AboutActionBean.class),
	CALENDAR(CalendarActionBean.class),
	ENTRIES(EntriesActionBean.class),
	REPORT(ReportActionBean.class),
	PREFERENCES(PreferencesActionBean.class),
	DELETED(DeletedActionBean.class);

	private Class<? extends CalendarTrackerActionBean> actionBean;

	// constructor
    private CalendarTrackerPage(Class<? extends CalendarTrackerActionBean> actionBean) {
        this.actionBean = actionBean;
    }

    @Override
    public Tracker getTracker() {
    	return Tracker.CALENDAR;
    }

    @Override
    public String getTrackerPageKey() {
    	return getClass().getSimpleName() + '.' + name();
    }

    @Override
	public TrackerPage getDefault() {
		return CALENDAR;
	}

	@Override
	public Class<? extends TrackerActionBean<?,?>> getActionBean() {
		return actionBean;
	}

}