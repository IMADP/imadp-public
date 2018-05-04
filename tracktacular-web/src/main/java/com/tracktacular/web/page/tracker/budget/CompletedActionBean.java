package com.tracktacular.web.page.tracker.budget;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.budget.Budget;

/**
 * CompletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-budget/completed/page-{itemsPageProvider.pageNumber=1}")
public class CompletedActionBean extends BudgetTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findCompletedBudgetCount(getTrackerUser());
	}

	@Override
	protected List<Budget> findItems(Results results, User user) {
		CriteriaParams<Budget> params = CriteriaParams.<Budget>of(results, Order.<Budget>desc(Budget.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findCompletedBudgets(getTrackerUser(), params);
	}

}