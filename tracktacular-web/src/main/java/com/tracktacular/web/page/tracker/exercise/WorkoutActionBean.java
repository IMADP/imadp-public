package com.tracktacular.web.page.tracker.exercise;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;


/**
 * WorkoutActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-exercise/workout-{workout=null}/{nameSlug=null}")
public class WorkoutActionBean extends ExerciseTrackerActionBean {

	@Override
	public Resolution index() {
		if(getWorkout() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return super.index();
	}

	@Override
	public String getTrackerPageTitle() {
		return getWorkout().getName();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

}