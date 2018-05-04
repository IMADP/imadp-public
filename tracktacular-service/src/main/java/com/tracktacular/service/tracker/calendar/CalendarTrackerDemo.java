package com.tracktacular.service.tracker.calendar;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.Recurrence;
import com.tracktacular.service.RecurrenceType;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * CalendarTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CalendarTrackerDemo extends AbstractTrackerDemo {
	private CalendarTrackerFacade trackerFacade;

	// constructor
	public CalendarTrackerDemo(CalendarTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		// spaghetti sundays
		CalendarEntry calendarEntry = new CalendarEntry(user);
		calendarEntry.setName("Spaghetti Sundays");
		calendarEntry.setStartDate(new DateMidnight().minusYears(1).withDayOfWeek(7).toDateTime());
		calendarEntry.setStartTime(new DateTime().withHourOfDay(19).withMinuteOfHour(0));
		calendarEntry.setEndTime(new DateTime().withHourOfDay(20).withMinuteOfHour(0));
		calendarEntry.setRecurrence(new Recurrence(RecurrenceType.WEEK, 1));

		trackerFacade.saveCalendarEntry(calendarEntry);

		// dentist
		calendarEntry = new CalendarEntry(user);
		calendarEntry.setName("Dentist Appointment");
		calendarEntry.setStartDate(new DateMidnight().withDayOfMonth(1).plusDays(23).toDateTime());

		trackerFacade.saveCalendarEntry(calendarEntry);

		// paychecks
		calendarEntry = new CalendarEntry(user);
		calendarEntry.setName("Paycheck Day");
		calendarEntry.setStartDate(new DateMidnight().withDayOfMonth(1).toDateTime());
		calendarEntry.setRecurrence(new Recurrence(RecurrenceType.MONTH, 1));

		trackerFacade.saveCalendarEntry(calendarEntry);

		calendarEntry = new CalendarEntry(user);
		calendarEntry.setName("Paycheck Day");
		calendarEntry.setStartDate(new DateMidnight().withDayOfMonth(15).toDateTime());
		calendarEntry.setRecurrence(new Recurrence(RecurrenceType.MONTH, 1));

		trackerFacade.saveCalendarEntry(calendarEntry);
	}

}
