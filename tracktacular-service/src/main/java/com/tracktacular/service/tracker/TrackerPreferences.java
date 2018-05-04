package com.tracktacular.service.tracker;


/**
 * TrackerPreferences
 *
 * Base class for tracker preferences.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public interface TrackerPreferences {

	/**
	 * Returns true if the tracker data is public, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isTrackerPublic();

	/**
	 * Sets the trackerPublic flag.
	 *
	 * @param trackerPublic
	 */
	public void setTrackerPublic(boolean trackerPublic);

	/**
	 * Returns true if the tracker is enabled, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isTrackerEnabled();

	/**
	 * Sets the trackerEnabled flag.
	 *
	 * @param trackerEnabled
	 */
	public void setTrackerEnabled(boolean trackerEnabled);

	/**
	 * Returns true if the tracker tutorial is enabled, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isTrackerTutorial();

	/**
	 * Sets the trackerTutorial flag.
	 *
	 * @param trackerTutorial
	 */
	public void setTrackerTutorial(boolean trackerTutorial);

}