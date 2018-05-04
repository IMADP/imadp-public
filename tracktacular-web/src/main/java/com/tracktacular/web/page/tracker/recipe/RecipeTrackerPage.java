package com.tracktacular.web.page.tracker.recipe;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.web.page.tracker.TrackerActionBean;
import com.tracktacular.web.page.tracker.TrackerPage;

/**
 * RecipeTrackerPage
 *
 * Enumerated page values.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public enum RecipeTrackerPage implements TrackerPage {
	ABOUT(AboutActionBean.class),
	RECIPE_BOOK(RecipeBookActionBean.class),
	RECIPE_TAGS(RecipeTagsActionBean.class),
	REPORT(ReportActionBean.class),
	PREFERENCES(PreferencesActionBean.class),
	DELETED(DeletedActionBean.class);

	private Class<? extends RecipeTrackerActionBean> actionBean;

	// constructor
    private RecipeTrackerPage(Class<? extends RecipeTrackerActionBean> actionBean) {
        this.actionBean = actionBean;
    }

    @Override
    public Tracker getTracker() {
    	return Tracker.RECIPE;
    }

    @Override
    public String getTrackerPageKey() {
    	return getClass().getSimpleName() + '.' + name();
    }

    @Override
	public TrackerPage getDefault() {
		return RECIPE_BOOK;
	}

	@Override
	public Class<? extends TrackerActionBean<?,?>> getActionBean() {
		return actionBean;
	}

}