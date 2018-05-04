package com.tracktacular.service.tracker.calendar;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.tracktacular.service.Recurrence;
import com.tracktacular.service.RecurrenceType;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * TaskDaoImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Transactional
public class CalendarEntryDaoImplTest extends TracktacularServiceTestCase {
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		userService.save(user);
	}

	@Test
    public void test_findWithinIntervalStartDate() {
    	CalendarEntry calendarEntry = newCalendarEntry(user, new DateTime().plusDays(1), null, "name");

    	assertEquals(0, calendar_calendarEntryDao.findCount());

    	calendar_calendarEntryDao.save(calendarEntry);

    	assertEquals(1, calendar_calendarEntryDao.findCount());

    	Interval interval = new Interval(new DateTime(), new DateTime().plusDays(7));
    	List<CalendarEntry> results = calendar_calendarEntryDao.findWithinInterval(user, PersistableState.ACTIVE, interval);

    	assertEquals(1, results.size());
    }

	@Test
    public void test_findWithinIntervalSameStartDate() {
    	CalendarEntry calendarEntry = newCalendarEntry(user, new DateMidnight().toDateTime(), null, "name");

    	assertEquals(0, calendar_calendarEntryDao.findCount());

    	calendar_calendarEntryDao.save(calendarEntry);

    	assertEquals(1, calendar_calendarEntryDao.findCount());

    	Interval interval = new Interval(new DateMidnight().toDateTime(), new DateTime().plusDays(7));
    	List<CalendarEntry> results = calendar_calendarEntryDao.findWithinInterval(user, PersistableState.ACTIVE, interval);

    	assertEquals(1, results.size());
    }

	@Test
    public void test_findWithinIntervalEndDate() {
    	CalendarEntry calendarEntry = newCalendarEntry(user, new DateTime().minusDays(3), new DateTime().plusDays(1), "name");

    	assertEquals(0, calendar_calendarEntryDao.findCount());

    	calendar_calendarEntryDao.save(calendarEntry);

    	assertEquals(1, calendar_calendarEntryDao.findCount());

    	Interval interval = new Interval(new DateTime(), new DateTime().plusDays(7));
    	List<CalendarEntry> results = calendar_calendarEntryDao.findWithinInterval(user, PersistableState.ACTIVE, interval);

    	assertEquals(1, results.size());
    }

	@Test
    public void test_findWithinIntervalRecurring() {
    	CalendarEntry calendarEntry = newCalendarEntry(user, new DateTime().minusDays(3), null, "name");

    	assertEquals(0, calendar_calendarEntryDao.findCount());

    	calendar_calendarEntryDao.save(calendarEntry);

    	assertEquals(1, calendar_calendarEntryDao.findCount());

    	Interval interval = new Interval(new DateTime(), new DateTime().plusDays(7));
    	List<CalendarEntry> results = calendar_calendarEntryDao.findWithinInterval(user, PersistableState.ACTIVE, interval);

    	assertEquals(0, results.size());

    	calendarEntry.setRecurrence(new Recurrence(RecurrenceType.DAY, 10));

    	calendar_calendarEntryDao.save(calendarEntry);

    	results = calendar_calendarEntryDao.findWithinInterval(user, PersistableState.ACTIVE, interval);

    	assertEquals(1, results.size());
    }

    /**
     * Returns a new CalendarEntry.
     *
     * @param user
     * @param startDate
     * @param endDate
     * @param name
     * @return CalendarEntry
     */
    private CalendarEntry newCalendarEntry(User user, DateTime startDate, DateTime endDate, String name) {
    	CalendarEntry calendarEntry = new CalendarEntry(user);
    	calendarEntry.setStartDate(startDate);
    	calendarEntry.setEndDate(endDate);
    	calendarEntry.setName(name);
    	return calendarEntry;
    }

}