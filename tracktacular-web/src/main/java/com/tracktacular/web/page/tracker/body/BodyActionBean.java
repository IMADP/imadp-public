package com.tracktacular.web.page.tracker.body;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.body.Body;


/**
 * BodyActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-body/body/page-{itemsPageProvider.pageNumber=1}")
public class BodyActionBean extends BodyTrackerActionBean {
	private List<Body> bodies;

	/**
	 * Returns a list of bodies.
	 *
	 * @return List<Body>
	 */
	public List<Body> getBodies() {
		if(bodies == null)
			bodies = getTrackerFacade().findActiveBodies(getTrackerUser(),
					CriteriaParams.<Body>of(Results.ALL, Order.desc(Body.DATE)));

		return bodies;
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findActiveBodyCount(user);
	}

	@Override
	protected List<Body> findItems(Results results, User user) {
		CriteriaParams<Body> params = CriteriaParams.<Body>of(results, Order.<Body>desc(Body.DATE));
		return getTrackerFacade().findActiveBodies(user, params);
	}

}