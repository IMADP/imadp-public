package com.tracktacular.web.page.tracker.dream;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.dream.Dream;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-dream/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends DreamTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedDreamCount(user);
	}

	@Override
	protected List<Dream> findItems(Results results, User user) {
		CriteriaParams<Dream> params = CriteriaParams.<Dream>of(
				results, Order.<Dream>desc(Dream.PERSISTABLE_STATE_DATE));

		return getTrackerFacade().findDeletedDreams(user, params);
	}

}