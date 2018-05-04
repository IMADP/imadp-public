package com.tracktacular.web.page.tracker.gift;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.gift.Gift;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-gift/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends GiftTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedGiftCount(user);
	}

	@Override
	protected List<Gift> findItems(Results results, User user) {
		CriteriaParams<Gift> params = CriteriaParams.<Gift>of(results, Order.<Gift>desc(Gift.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedGifts(user, params);
	}

}