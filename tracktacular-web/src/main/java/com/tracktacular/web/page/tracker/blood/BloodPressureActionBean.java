package com.tracktacular.web.page.tracker.blood;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.blood.BloodPressure;


/**
 * BloodPressureActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-blood/blood-pressure/page-{itemsPageProvider.pageNumber=1}")
public class BloodPressureActionBean extends BloodTrackerActionBean {
	private List<BloodPressure> bloodPressures;

	/**
	 * Returns a list of bloodPressures.
	 *
	 * @return List<BloodPressure>
	 */
	public List<BloodPressure> getBloodPressures() {
		if(bloodPressures == null)
			bloodPressures = getTrackerFacade().findActiveBloodPressures(getTrackerUser(),
					CriteriaParams.<BloodPressure>of(Results.ALL, Order.desc(BloodPressure.DATE)));

		return bloodPressures;
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findActiveBloodPressureCount(user);
	}

	@Override
	protected List<BloodPressure> findItems(Results results, User user) {
		CriteriaParams<BloodPressure> params = CriteriaParams.<BloodPressure>of(
				results, Order.<BloodPressure>desc(BloodPressure.DATE));

		return getTrackerFacade().findActiveBloodPressures(user, params);
	}

}