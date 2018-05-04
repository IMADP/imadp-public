package com.tracktacular.service.tracker.calendar;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.imadp.core.AbstractSerializable;



/**
 * CalendarDay
 *
 * A representation of a calendar day.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CalendarDay extends AbstractSerializable {
	private final int id;
	private final int monthOfYear;
	private final DateTime date;
	private final List<CalendarItem> items;

	// constructor
	CalendarDay(int id, int monthOfYear, DateTime date, List<CalendarItem> items) {
		this.id = id;
		this.monthOfYear = monthOfYear;
		this.date = date;
		this.items = items;
	}

	/**
	 * Returns true if any calendar items are present.
	 *
	 * @return boolean
	 */
	public boolean hasItems() {
		return !CollectionUtils.isEmpty(items);
	}

	/**
	 * Returns the number of items for this calendar day.
	 *
	 * @return int
	 */
	public int getItemCount() {
		return items == null ? 0 : items.size();
	}

	/**
	 * Returns true if this calendar day matches the month of the given CalendarMonth, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isActiveMonth() {
		return date.getMonthOfYear() == monthOfYear;
	}

	/**
	 * Returns true if this calendar day is before the current day, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isBeforeCurrentDay() {
		return date.toLocalDate().isBefore(new LocalDate());
	}

	/**
	 * Returns true if this calendar day matches the current day, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isCurrentDay() {
		return date.toLocalDate().isEqual(new LocalDate());
	}

	/**
	 * Returns true if this calendar day is a sunday, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isSunday() {
		return date.getDayOfWeek() == DateTimeConstants.SUNDAY;
	}

	// getters and setters
	public DateTime getDate() {
		return date;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public List<CalendarItem> getItems() {
		return items;
	}

	public int getId() {
		return id;
	}

}