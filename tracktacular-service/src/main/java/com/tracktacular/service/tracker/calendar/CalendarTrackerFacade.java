package com.tracktacular.service.tracker.calendar;

import java.util.List;

import org.joda.time.DateTime;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * CalendarTrackerFacade
 *
 * Provides functionality for calendar tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CalendarTrackerFacade extends TrackerFacade {

	/**
	 * Gets a calendar month by user for a given date.
	 *
	 * @param user
	 * @param date
	 * @param publicOnly
	 * @return CalendarMonth
	 */
	public CalendarMonth getCalendarMonth(User user, DateTime date, boolean publicOnly);

	/**
	 * Gets a calendarEntry by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return CalendarEntry
	 */
	public CalendarEntry getCalendarEntry(User user, String uid);

	/**
	 * Finds a List of deleted CalendarEntries for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<CalendarEntry>
	 */
	public List<CalendarEntry> findDeletedCalendarEntries(User user, CriteriaParams<CalendarEntry> criteriaParams);

	/**
	 * Finds the count of all deleted CalendarEntries for a user.
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedCalendarEntryCount(User user);

	/**
	 * Finds a list of active CalendarEntries for a user.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<CalendarEntry>
	 */
	public List<CalendarEntry> findActiveCalendarEntries(User user, CriteriaParams<CalendarEntry> criteriaParams);

	/**
	 * Finds the count of all CalendarEntries for a user.
	 *
	 * @param user
	 * @return long
	 */
	public long findActiveCalendarEntryCount(User user);

	/**
	 * Saves a calendarEntry.
	 *
	 * @param calendarEntry
	 */
	public void saveCalendarEntry(CalendarEntry calendarEntry);

	/**
	 * Deletes a calendarEntry.
	 *
	 * @param calendarEntry
	 */
	public void deleteCalendarEntry(CalendarEntry calendarEntry);

}