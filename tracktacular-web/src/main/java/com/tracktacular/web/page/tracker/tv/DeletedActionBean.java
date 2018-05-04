package com.tracktacular.web.page.tracker.tv;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.tv.Show;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-tv/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends TvTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedShowCount(user);
	}

	@Override
	protected List<Show> findItems(Results results, User user) {
		CriteriaParams<Show> params = CriteriaParams.<Show>of(results, Order.<Show>desc(Show.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedShows(user, params);
	}

}