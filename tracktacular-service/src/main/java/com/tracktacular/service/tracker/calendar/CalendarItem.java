package com.tracktacular.service.tracker.calendar;

import org.joda.time.DateTime;

import com.imadp.core.AbstractSerializable;


/**
 * CalendarItem
 *
 * A representation of an individual item on a calendar.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CalendarItem extends AbstractSerializable {
	private final CalendarEntry calendarEntry;
	private final DateTime dateTime;

	// constructor
	CalendarItem(DateTime dateTime, CalendarEntry calendarEntry) {
		this.dateTime = dateTime;
		this.calendarEntry = calendarEntry;
	}

	// getters and setters
	public CalendarEntry getCalendarEntry() {
		return calendarEntry;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

}