package com.tracktacular.service.tracker.birthday;

import com.tracktacular.service.tracker.AbstractTrackerPreferences;


/**
 * BirthdayTrackerPreferences
 *
 * Tracker preferences for a Tracktacular user.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BirthdayTrackerPreferences extends AbstractTrackerPreferences {
	private boolean alertOnBirthdays = true;

	// getters and setters
	public boolean isAlertOnBirthdays() {
		return alertOnBirthdays;
	}

	public void setAlertOnBirthdays(boolean alertOnBirthdays) {
		this.alertOnBirthdays = alertOnBirthdays;
	}

}