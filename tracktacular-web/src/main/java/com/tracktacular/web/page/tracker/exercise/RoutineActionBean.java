package com.tracktacular.web.page.tracker.exercise;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.imadp.web.stripes.link.Link;
import com.tracktacular.service.tracker.exercise.Workout;
import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;


/**
 * RoutineActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-exercise/routine-{routine=null}/{nameSlug=null}/page-{itemsPageProvider.pageNumber=1}")
public class RoutineActionBean extends ExerciseTrackerActionBean {

	@Override
	public Resolution index() {
		if(getRoutine() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return super.index();
	}

	@Override
	public String getTrackerPageTitle() {
		return getRoutine().getName();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}


	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findWorkoutCount(user, getRoutine());
	}

	@Override
	protected List<Workout> findItems(Results results, User user) {
		return getTrackerFacade().findWorkouts(user, getRoutine(),
				CriteriaParams.<Workout>of(results, Order.desc(Workout.DATE)));
	}

	@Override
	protected void addLinkParameters(Link link) {
		link.addParameter("nameSlug", getRoutine().getNameSlug())
			.addParameter("routine", getRoutine().getUid());
	}

}