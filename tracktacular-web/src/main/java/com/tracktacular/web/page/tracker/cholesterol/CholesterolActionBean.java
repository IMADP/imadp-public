package com.tracktacular.web.page.tracker.cholesterol;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.cholesterol.Cholesterol;


/**
 * CholesterolActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-cholesterol/cholesterol/page-{itemsPageProvider.pageNumber=1}")
public class CholesterolActionBean extends CholesterolTrackerActionBean {
	private List<Cholesterol> cholesterols;

	/**
	 * Returns a list of cholesterols.
	 *
	 * @return List<Cholesterol>
	 */
	public List<Cholesterol> getCholesterols() {
		if(cholesterols == null)
			cholesterols = getTrackerFacade().findActiveCholesterols(getTrackerUser(),
					CriteriaParams.<Cholesterol>of(Results.ALL, Order.desc(Cholesterol.DATE)));

		return cholesterols;
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findActiveCholesterolCount(user);
	}

	@Override
	protected List<Cholesterol> findItems(Results results, User user) {
		CriteriaParams<Cholesterol> params = CriteriaParams.<Cholesterol>of(
				results, Order.<Cholesterol>desc(Cholesterol.DATE));

		return getTrackerFacade().findActiveCholesterols(user, params);
	}

}