package com.tracktacular.service.tracker.calendar;

import java.util.List;

import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;

/**
 * CalendarEntryService
 *
 * Provides common retrieval operations for CalendarEntry objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CalendarEntryService extends PersistableUserService<CalendarEntry> {

	/**
	 * Returns a list of calendar entries by user, persistable state, that occur within the given interval.
	 *
	 * @param user
	 * @param persistableState
	 * @param interval
	 * @return List<CalendarEntry>
	 */
	public List<CalendarEntry> findWithinInterval(User user, PersistableState persistableState, Interval interval);

}