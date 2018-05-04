package com.tracktacular.web.page.tracker.body;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.body.Body;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-body/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends BodyTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedBodyCount(user);
	}

	@Override
	protected List<Body> findItems(Results results, User user) {
		CriteriaParams<Body> params = CriteriaParams.<Body>of(results, Order.<Body>desc(Body.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedBodies(getTrackerUser(), params);
	}

}