package com.tracktacular.service.tracker.game;

import com.tracktacular.service.tracker.AbstractTrackerPreferences;


/**
 * GameTrackerPreferences
 *
 * Tracker preferences for a Tracktacular user.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public class GameTrackerPreferences extends AbstractTrackerPreferences {
	private boolean alertOnTargetDates = true;
	private String steamId;

	// getters and setters
	public boolean isAlertOnTargetDates() {
		return alertOnTargetDates;
	}

	public void setAlertOnTargetDates(boolean alertOnTargetDates) {
		this.alertOnTargetDates = alertOnTargetDates;
	}

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}

}