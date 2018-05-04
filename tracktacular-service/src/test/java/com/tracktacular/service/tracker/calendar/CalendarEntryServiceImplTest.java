package com.tracktacular.service.tracker.calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.Recurrence;
import com.tracktacular.service.RecurrenceType;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * CalendarEntryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CalendarEntryServiceImplTest extends TracktacularServiceTestCase {

	public static class CalendarEntryOperations extends TracktacularServiceTestCase {
		CalendarEntry calendarEntry;

		@Override
		public void before() throws Exception {
			super.before();

			User user = new User();

			calendarEntry = new CalendarEntry(user);
			calendarEntry.setName("name");
			calendarEntry.setStartDate(new DateMidnight().toDateTime());
			calendarEntry.setEndDate(new DateMidnight().plusDays(1).toDateTime());

			userService.save(user);
		}

		@Test
		public void commonPersistableOperations() {
			ServiceTestUtil.assertPersistable(calendarEntry, calendar_calendarEntryService);
		}

		@Test
		public void noCalendarItem() {
			assertTrue(new CalendarEntry().getCalendarItems().isEmpty());
		}

		@Test
		public void singleCalendarItem() {
			CalendarEntry entry = new CalendarEntry();
			entry.setStartDate(new DateMidnight().toDateTime());
			assertEquals(1, entry.getCalendarItems().size());
		}

		@Test
		public void multipleCalendarItems() {
			CalendarEntry entry = new CalendarEntry();
			entry.setStartDate(new DateMidnight().toDateTime());
			entry.setEndDate(new DateMidnight().plusDays(5).toDateTime());
			List<CalendarItem> items = entry.getCalendarItems();

			assertEquals(6, items.size());

			int index = 0;
			CalendarItem item = items.get(index);
			assertEquals(entry, item.getCalendarEntry());
			assertEquals(new DateMidnight().plusDays(index).toDateTime(), item.getDateTime());

			index = 1;
			item = items.get(index);
			assertEquals(entry, item.getCalendarEntry());
			assertEquals(new DateMidnight().plusDays(index).toDateTime(), item.getDateTime());

			index = 2;
			item = items.get(index);
			assertEquals(entry, item.getCalendarEntry());
			assertEquals(new DateMidnight().plusDays(index).toDateTime(), item.getDateTime());

			index = 3;
			item = items.get(index);
			assertEquals(entry, item.getCalendarEntry());
			assertEquals(new DateMidnight().plusDays(index).toDateTime(), item.getDateTime());

			index = 4;
			item = items.get(index);
			assertEquals(entry, item.getCalendarEntry());
			assertEquals(new DateMidnight().plusDays(index).toDateTime(), item.getDateTime());

			index = 5;
			item = items.get(index);
			assertEquals(entry, item.getCalendarEntry());
			assertEquals(new DateMidnight().plusDays(index).toDateTime(), item.getDateTime());
		}


		@Test
	    public void withinRecurringPeriod() {
			DateTime startDateTime = new DateMidnight(2010, 11, 1).toDateTime();

			CalendarEntry entry = new CalendarEntry();
			entry.setStartDate(startDateTime);
			entry.setRecurrence(new Recurrence(RecurrenceType.DAY, 7));

	        assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(7)));
	        assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(77)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(9)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(99)));

	        entry = new CalendarEntry();
			entry.setStartDate(startDateTime);
			entry.setRecurrence(new Recurrence(RecurrenceType.WEEK, 1));

			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(7)));
	        assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(77)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(9)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(99)));

	        entry = new CalendarEntry();
	        entry.setStartDate(startDateTime);
	        entry.setEndDate(startDateTime.plusDays(4));
			entry.setRecurrence(new Recurrence(RecurrenceType.WEEK, 1));

			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(7)));
			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(8)));
			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(9)));
			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(10)));
			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusDays(11)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(12)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(13)));

	        entry = new CalendarEntry();
			entry.setStartDate(startDateTime);
			entry.setRecurrence(new Recurrence(RecurrenceType.MONTH, 1));

			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusMonths(1)));
	        assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusMonths(5)));
	        assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusMonths(12)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(100)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(99)));

	        entry = new CalendarEntry();
			entry.setStartDate(startDateTime);
			entry.setRecurrence(new Recurrence(RecurrenceType.MONTH, 2));

			assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusMonths(3)));

			entry = new CalendarEntry();
			entry.setStartDate(startDateTime);
			entry.setRecurrence(new Recurrence(RecurrenceType.YEAR, 1));

			assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusMonths(12)));
	        assertTrue(entry.isWithinRecurringPeriod(startDateTime.plusYears(5)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(100)));
	        assertFalse(entry.isWithinRecurringPeriod(startDateTime.plusDays(99)));
	    }

	}

	public static class CalendarOperations extends TracktacularServiceTestCase {

		@Test
		public void getCalendarDay() {
			DateMidnight midnight = new DateMidnight().withDayOfMonth(1);
			List<CalendarEntry> entries = new ArrayList<>();

			CalendarEntry entry = new CalendarEntry();
			entry.setStartDate(midnight.toDateTime());
			entries.add(entry);

			entry = new CalendarEntry();
			entry.setStartDate(midnight.plusDays(5).toDateTime());
			entry.setEndDate(midnight.plusDays(10).toDateTime());
			entries.add(entry);

			CalendarMonth calendarMonth = new CalendarMonth(midnight.toDateTime(), entries);
			CalendarDay calendarDay = calendarMonth.getCalendarDay(midnight.toDateTime());

			assertNotNull(calendarDay);
			assertTrue(calendarDay.hasItems());
			assertEquals(1, calendarDay.getItems().size());

			calendarDay = calendarMonth.getCalendarDay(midnight.plusDays(5).toDateTime());

			assertNotNull(calendarDay);
			assertTrue(calendarDay.hasItems());
			assertEquals(1, calendarDay.getItems().size());

			calendarDay = calendarMonth.getCalendarDay(midnight.plusDays(6).toDateTime());

			assertNotNull(calendarDay);
			assertTrue(calendarDay.hasItems());
			assertEquals(1, calendarDay.getItems().size());

			calendarDay = calendarMonth.getCalendarDay(midnight.plusDays(7).toDateTime());

			assertNotNull(calendarDay);
			assertTrue(calendarDay.hasItems());
			assertEquals(1, calendarDay.getItems().size());

			calendarDay = calendarMonth.getCalendarDay(midnight.plusDays(8).toDateTime());

			assertNotNull(calendarDay);
			assertTrue(calendarDay.hasItems());
			assertEquals(1, calendarDay.getItems().size());

			calendarDay = calendarMonth.getCalendarDay(midnight.plusDays(9).toDateTime());

			assertNotNull(calendarDay);
			assertTrue(calendarDay.hasItems());
			assertEquals(1, calendarDay.getItems().size());

			calendarDay = calendarMonth.getCalendarDay(midnight.plusDays(10).toDateTime());

			assertNotNull(calendarDay);
			assertTrue(calendarDay.hasItems());
			assertEquals(1, calendarDay.getItems().size());

		}

	}

}