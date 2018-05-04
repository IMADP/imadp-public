package com.tracktacular.web.page.tracker.birthday;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.birthday.Birthday;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-birthday/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends BirthdayTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedBirthdayCount(user);
	}

	@Override
	protected List<Birthday> findItems(Results results, User user) {
		CriteriaParams<Birthday> params = CriteriaParams.<Birthday>of(results, Order.<Birthday>desc(Birthday.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedBirthdays(user, params);
	}

}