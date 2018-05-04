package com.tracktacular.web.page.tracker.journal;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.journal.Journal;
import com.tracktacular.service.tracker.journal.JournalDto;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-journal/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends JournalTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedJournalCount(user);
	}

	@Override
	protected List<JournalDto> findItems(Results results, User user) {
		CriteriaParams<Journal> params = CriteriaParams.<Journal>of(results, Order.<Journal>desc(Journal.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedJournals(user, params);
	}

}