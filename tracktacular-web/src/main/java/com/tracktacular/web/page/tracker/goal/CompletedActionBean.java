package com.tracktacular.web.page.tracker.goal;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.goal.Goal;

/**
 * CompletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-goal/completed/page-{itemsPageProvider.pageNumber=1}")
public class CompletedActionBean extends GoalTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findCompletedGoalCount(user);
	}

	@Override
	protected List<Goal> findItems(Results results, User user) {
		CriteriaParams<Goal> params = CriteriaParams.<Goal>of(results, Order.<Goal>desc(Goal.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findCompletedGoals(user, params);
	}

}