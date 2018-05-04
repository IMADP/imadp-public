package com.tracktacular.service.tracker.task;

import com.tracktacular.service.tracker.AbstractTrackerPreferences;


/**
 * TaskTrackerPreferences
 *
 * Tracker preferences for a Tracktacular user.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskTrackerPreferences extends AbstractTrackerPreferences {
	private boolean alertOnTargetDates = true;

	// getters and setters
	public boolean isAlertOnTargetDates() {
		return alertOnTargetDates;
	}

	public void setAlertOnTargetDates(boolean alertOnTargetDates) {
		this.alertOnTargetDates = alertOnTargetDates;
	}

}