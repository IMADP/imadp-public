package com.tracktacular.web.page.tracker.calendar;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-calendar/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends CalendarTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedCalendarEntryCount(user);
	}

	@Override
	protected List<CalendarEntry> findItems(Results results, User user) {
		CriteriaParams<CalendarEntry> params = CriteriaParams.<CalendarEntry>of(
				results, Order.<CalendarEntry>desc(CalendarEntry.PERSISTABLE_STATE_DATE));

		return getTrackerFacade().findDeletedCalendarEntries(user, params);
	}

}