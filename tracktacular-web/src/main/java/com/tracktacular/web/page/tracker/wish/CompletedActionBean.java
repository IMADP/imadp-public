package com.tracktacular.web.page.tracker.wish;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.wish.Item;


/**
 * CompletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-wish/completed/page-{itemsPageProvider.pageNumber=1}")
public class CompletedActionBean extends WishTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findCompletedItemCount(user);
	}

	@Override
	protected List<Item> findItems(Results results, User user) {
		CriteriaParams<Item> params = CriteriaParams.<Item>of(results, Order.<Item>desc(Item.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findCompletedItems(user, params);
	}

}