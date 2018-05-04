package com.tracktacular.service.tracker;

import java.util.List;

import org.joda.time.Interval;

import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.calendar.CalendarEntry;

/**
 * TrackerFacade
 *
 * Provides functionality for all tracker facades.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TrackerFacade {

	/**
	 * Deletes all associated tracker data for a given user.
	 *
	 * @param user
	 */
	public void deleteAll(User user);

    /**
	 * Returns a TrackerDemo object for the tracker.
	 *
	 * @return TrackerDemo
	 */
	public TrackerDemo getTrackerDemo();

	/**
	 * Returns a TrackerReport object for a user.
	 * The publicOnly flag is used only when a report contains information from other trackers besides this one.
	 *
	 * @param user
	 * @param publicOnly
	 * @param preferences
	 * @return TrackerReport
	 */
	public TrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly);

	/**
	 * Returns a list of calendar entries relevant to the tracker for a given user with the specified interval.
	 * This method will only be called when entries should be returned - there is no need to inspect permissions
	 * through the preferences object. Preferences are merely added for scenarios involving preferential calendar
	 * entries, such as reminders or integration with other systems.
	 *
	 * @param user
	 * @param interval
	 * @param preferences
	 * @return List<CalendarEntry>
	 */
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences);

}