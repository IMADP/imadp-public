package com.tracktacular.service.tracker.wish;

import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * WishTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class WishTrackerReport extends AbstractTrackerReport {
	private final Items items;

    // constructor
    public WishTrackerReport(Items items) {
    	this.items = items;
    }

    @Override
	public boolean isEnabled() {
    	return items.getItemCount() > 0;
    }

	// getters
    public Items getItems() {
		return items;
	}

}