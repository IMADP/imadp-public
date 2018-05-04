package com.tracktacular.web.page.tracker.cholesterol;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.cholesterol.Cholesterol;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-cholesterol/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends CholesterolTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedCholesterolCount(user);
	}

	@Override
	protected List<Cholesterol> findItems(Results results, User user) {
		CriteriaParams<Cholesterol> params = CriteriaParams.<Cholesterol>of(
				results, Order.<Cholesterol>desc(Cholesterol.PERSISTABLE_STATE_DATE));

		return getTrackerFacade().findDeletedCholesterols(user, params);
	}

}