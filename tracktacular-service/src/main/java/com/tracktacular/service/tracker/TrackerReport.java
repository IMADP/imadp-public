package com.tracktacular.service.tracker;


/**
 * TrackerReport
 *
 * Interface for all tracker report segments.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public interface TrackerReport {

	/**
	 * Returns the tracker associated with the report.
	 *
	 * @return Tracker
	 */
	public Tracker getTracker();

	/**
	 * Returns true if the tracker report segment has enough data to be enabled, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isEnabled();

	/**
	 * Returns the count of all alerts for the tracker report.
	 *
	 * @return int
	 */
	public int getAlertCount();

	/**
	 * Returns true if alerts were found, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isAlerts();

}