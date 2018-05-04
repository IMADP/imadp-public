package com.tracktacular.web.page.tracker.restaurant;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.restaurant.Restaurant;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-restaurant/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends RestaurantTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedRestaurantCount(user);
	}

	@Override
	protected List<Restaurant> findItems(Results results, User user) {
		CriteriaParams<Restaurant> params = CriteriaParams.<Restaurant>of(results, Order.<Restaurant>desc(Restaurant.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedRestaurants(user, params);
	}

}