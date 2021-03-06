package com.tracktacular.web.page.tracker.task;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.task.CompletedTaskStatistics;
import com.tracktacular.service.tracker.task.Task;


/**
 * CompletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-task/completed/page-{itemsPageProvider.pageNumber=1}")
public class CompletedActionBean extends TaskTrackerActionBean {
	private CompletedTaskStatistics completedTaskStatistics;

	/**
	 * Returns the completed task statistics for a user.
	 *
	 * @return CompletedTaskStatistics
	 */
	public CompletedTaskStatistics getCompletedTaskStatistics() {
		if(completedTaskStatistics == null)
			completedTaskStatistics = getTrackerFacade().getCompletedTaskStatistics(getTrackerUser());

		return completedTaskStatistics;
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findCompletedTaskCount(user);
	}

	@Override
	protected List<Task> findItems(Results results, User user) {
		CriteriaParams<Task> params = CriteriaParams.<Task>of(results, Order.<Task>desc(Task.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findCompletedTasks(user, params);
	}

}