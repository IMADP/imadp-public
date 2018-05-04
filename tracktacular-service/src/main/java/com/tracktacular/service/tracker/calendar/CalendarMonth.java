package com.tracktacular.service.tracker.calendar;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.imadp.core.AbstractSerializable;

/**
 * CalendarMonth
 *
 * A representation of a calendar month.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CalendarMonth extends AbstractSerializable {

	// static properties
	private static final int CALENDAR_INDEX_FIRST = 0;
	private static final int CALENDAR_INDEX_LAST = 41;
	private static final int WEEKS_PER_CALENDAR_MONTH = 6;
	private static final int DAYS_PER_WEEK = 7;

	/**
	 * Returns the first sunday of a calendar month for a given date.
	 * Not that the date returned may not match the month of the date given.
	 *
	 * @param date
	 * @return DateTime
	 */
	public static DateTime getFirstDayOfCalendarMonth(DateTime date) {
		return getDateTime(CALENDAR_INDEX_FIRST, date.getMonthOfYear(), date.getYear());
	}

	/**
	 * Returns the last saturday of a calendar month for a given date.
	 * Not that the date returned may not match the month of the date given.
	 *
	 * @param date
	 * @return DateTime
	 */
	public static DateTime getLastDayOfCalendarMonth(DateTime date) {
		return getDateTime(CALENDAR_INDEX_LAST, date.getMonthOfYear(), date.getYear());
	}

	/**
	 * Assuming the standard calendar grid of 7x6, indexed from 0 to 41, this
	 * method returns the dateTime for a specific calendar index in a given year and month.
	 *
	 * @param index
	 * @param month
	 * @param year
	 * @return DateTime
	 */
	private static DateTime getDateTime(int index, int month, int year) {
		DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0, 0);
		return dateTime.plusDays(index - dateTime.getDayOfWeek());
	}

	// properties
	private final List<CalendarWeek> weeks;
	private final int monthOfYear;

	// constructor
	CalendarMonth(DateTime date, List<CalendarEntry> calendarEntries) {
		this.monthOfYear = date.getMonthOfYear();

		List<CalendarEntry> recurringEntries = new ArrayList<>();
		ListMultimap<DateTime, CalendarItem> nonRecurringItems = ArrayListMultimap.create();

		/*
		 * Loop through all the entries and separate the recurring and non recurring entries.
		 *
		 * Recurring entries are stored in a list as they need to be iterated through on a day by day
		 * basis to see if the recurring period matches the given date.
		 *
		 * CalendarItems are created for every day of non recurring entry periods and added to a map so they can
		 * be retrieved later by date specifically.
		 *
		 */
		for(CalendarEntry calendarEntry : calendarEntries)
		{
			// add recurring entries to the recurring entry list
			if(calendarEntry.isRecurring())
				recurringEntries.add(calendarEntry);

			// add non recurring entries to the non recurring entry item map
			else for(CalendarItem calendarItem : calendarEntry.getCalendarItems())
				nonRecurringItems.put(calendarItem.getDateTime().toDateMidnight().toDateTime(), calendarItem);
		}

		List<CalendarWeek> weeks = new ArrayList<>();

		// loop through and create each week of the calendar
		for(int i=0; i<WEEKS_PER_CALENDAR_MONTH; i++)
		{
			List<CalendarDay> days = new ArrayList<>();

			// loop through and create each day of the week
			for(int j=0; j<DAYS_PER_WEEK; j++)
			{
				int index = (DAYS_PER_WEEK * i) + j;
				DateTime dateTime = getDateTime(index, date.getMonthOfYear(), date.getYear());
				List<CalendarItem> items = nonRecurringItems.get(dateTime);

				for(CalendarEntry entry : recurringEntries)
					if(entry.isWithinRecurringPeriod(dateTime))
						items.add(new CalendarItem(dateTime, entry));

				days.add(new CalendarDay(index, monthOfYear, dateTime, items));
			}

			weeks.add(new CalendarWeek(i+1, monthOfYear, days));
		}

		this.weeks = weeks;
	}

	/**
	 * Returns the CalendarDay matching the given dateTime, or null if none was found for the given month.
	 *
	 * @param dateTime
	 * @return CalendarDay
	 */
	public CalendarDay getCalendarDay(DateTime dateTime) {
		for(CalendarWeek week : weeks)
			for(CalendarDay day : week.getDays())
				if(day.getDate().equals(dateTime))
					return day;

		return null;
	}

	/**
	 * Returns the calendar week representing the current week.
	 *
	 * @return CalendarWeek
	 */
	public CalendarWeek getCurrentWeek() {
		for(CalendarWeek week : weeks)
			if(week.isCurrentWeek())
				return week;

		return null;
	}

	// getters and setters
	public List<CalendarWeek> getWeeks() {
		return weeks;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

}