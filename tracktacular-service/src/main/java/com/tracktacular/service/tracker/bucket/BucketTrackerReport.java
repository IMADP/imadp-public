package com.tracktacular.service.tracker.bucket;

import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * BucketTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class BucketTrackerReport extends AbstractTrackerReport {
	private final Items items;

    // constructor
    public BucketTrackerReport(Items items) {
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