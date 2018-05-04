package com.tracktacular.web.page.tracker.journal;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.web.page.tracker.TrackerActionBean;
import com.tracktacular.web.page.tracker.TrackerPage;

/**
 * JournalPage
 *
 * Enumerated page values.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public enum JournalTrackerPage implements TrackerPage {
	ABOUT(AboutActionBean.class),
	JOURNALS(JournalsActionBean.class),
	COMPLETED(CompletedActionBean.class),
	REPORT(ReportActionBean.class),
	PREFERENCES(PreferencesActionBean.class),
	DELETED(DeletedActionBean.class);

	private Class<? extends JournalTrackerActionBean> actionBean;

	// constructor
    private JournalTrackerPage(Class<? extends JournalTrackerActionBean> actionBean) {
        this.actionBean = actionBean;
    }

    @Override
    public Tracker getTracker() {
    	return Tracker.JOURNAL;
    }

    @Override
    public String getTrackerPageKey() {
    	return getClass().getSimpleName() + '.' + name();
    }

    @Override
	public TrackerPage getDefault() {
		return JOURNALS;
	}

	@Override
	public Class<? extends TrackerActionBean<?,?>> getActionBean() {
		return actionBean;
	}

}