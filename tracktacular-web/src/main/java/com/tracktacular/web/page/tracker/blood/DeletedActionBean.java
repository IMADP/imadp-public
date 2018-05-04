package com.tracktacular.web.page.tracker.blood;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.blood.BloodPressure;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-blood/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends BloodTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedBloodPressureCount(user);
	}

	@Override
	protected List<BloodPressure> findItems(Results results, User user) {
		CriteriaParams<BloodPressure> params = CriteriaParams.<BloodPressure>of(
				results, Order.<BloodPressure>desc(BloodPressure.PERSISTABLE_STATE_DATE));

		return getTrackerFacade().findDeletedBloodPressures(getTrackerUser(), params);
	}

}