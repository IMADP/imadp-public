package com.tracktacular.service.tracker.goal;

import com.tracktacular.service.tracker.AbstractTrackerPreferences;


/**
 * GoalTrackerPreferences
 *
 * Tracker preferences for a Tracktacular user.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GoalTrackerPreferences extends AbstractTrackerPreferences {
	private boolean alertOnGoalTargetDates = true;
	private boolean alertOnObjectiveTargetDates = true;

	// getters and setters
	public boolean isAlertOnGoalTargetDates() {
		return alertOnGoalTargetDates;
	}

	public void setAlertOnGoalTargetDates(boolean alertOnGoalTargetDates) {
		this.alertOnGoalTargetDates = alertOnGoalTargetDates;
	}

	public boolean isAlertOnObjectiveTargetDates() {
		return alertOnObjectiveTargetDates;
	}

	public void setAlertOnObjectiveTargetDates(boolean alertOnObjectiveTargetDates) {
		this.alertOnObjectiveTargetDates = alertOnObjectiveTargetDates;
	}

}