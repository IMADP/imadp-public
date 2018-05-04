package com.tracktacular.web.page.tracker.budget;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.budget.Budget;


/**
 * BudgetsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-budget/budgets")
public class BudgetsActionBean extends BudgetTrackerActionBean {
	private List<Budget> budgets;

	/**
	 * Returns a list of budgets.
	 *
	 * @return List<Budget>
	 */
	public List<Budget> getBudgets() {
		if(budgets == null)
			budgets = getTrackerFacade().findActiveBudgets(getTrackerUser());

		return budgets;
	}

}