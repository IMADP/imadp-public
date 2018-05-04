package com.tracktacular.service.tracker.budget;

import java.util.Collections;
import java.util.List;

import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * BudgetTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class BudgetTrackerReport extends AbstractTrackerReport {
	private final List<Budget> activeBudgetsList;

    // constructor
    public BudgetTrackerReport(List<Budget> activeBudgetsList) {
    	this.activeBudgetsList = Collections.unmodifiableList(activeBudgetsList);
    }

    @Override
	public boolean isEnabled() {
    	return !activeBudgetsList.isEmpty();
    }

    /**
     * Returns the count of all Active goals.
     *
     * @return long
     */
    public long getActiveBudgetsCount() {
		return activeBudgetsList.size();
	}

    // getters
    public List<Budget> getActiveBudgetsList() {
		return activeBudgetsList;
	}

}