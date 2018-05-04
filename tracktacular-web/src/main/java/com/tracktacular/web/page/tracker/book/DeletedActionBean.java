package com.tracktacular.web.page.tracker.book;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.book.Book;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-book/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends BookTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedBookCount(user);
	}

	@Override
	protected List<Book> findItems(Results results, User user) {
		CriteriaParams<Book> params = CriteriaParams.<Book>of(results, Order.<Book>desc(Book.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedBooks(user, params);
	}

}