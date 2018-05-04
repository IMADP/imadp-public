package com.tracktacular.service.tracker.calendar;

import java.util.List;

import org.joda.time.Interval;

import com.imadp.dao.PersistableDao;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;


/**
 * CalendarEntry
 *
 * An extention of the PersistableDao which provides additional CalendarEntry queries.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CalendarEntryDao extends PersistableDao<CalendarEntry> {

	/**
     * Returns a list of calendar entries for a given user by persistable state and within a specified time range.
     *
     * @param user
     * @param persistableState
     * @param interval
     * @return List<CalendarEntry>
     */
    public List<CalendarEntry> findWithinInterval(User user, PersistableState persistableState, Interval interval);

}