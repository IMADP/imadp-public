package com.tracktacular.web.page.tracker.exercise;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.exercise.Routine;
import com.tracktacular.service.tracker.exercise.RoutineDto;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-exercise/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends ExerciseTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedRoutineCount(user);
	}

	@Override
	protected List<RoutineDto> findItems(Results results, User user) {
		CriteriaParams<Routine> params = CriteriaParams.<Routine>of(results, Order.<Routine>desc(Routine.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedRoutines(user, params);
	}

}