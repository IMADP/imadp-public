package com.tracktacular.service.tracker.calendar;

import java.util.List;

import com.imadp.core.AbstractSerializable;

/**
 * CalendarWeek
 *
 * A representation of a calendar week.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CalendarWeek extends AbstractSerializable {
	private final int monthOfYear;
	private final int id;
	private final List<CalendarDay> days;

	// constructor
	CalendarWeek(int id, int monthOfYear, List<CalendarDay> days) {
		this.id = id;
		this.monthOfYear = monthOfYear;
		this.days = days;
	}

	/**
	 * Returns the number of items for this calendar day.
	 *
	 * @return int
	 */
	public int getItemCount() {
		int itemCount = 0;

		for(CalendarDay day : days)
			itemCount += day.getItemCount();

		return itemCount;
	}

	/**
	 * Returns true if this is the current week, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isCurrentWeek() {
		for(CalendarDay day : days)
			if(day.isCurrentDay())
				return true;

		return false;
	}

	/**
	 * Returns true if this is before the current week, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isBeforeCurrentWeek() {
		return days.get(days.size() - 1).isBeforeCurrentDay();
	}

	/**
	 * Gets the first day of the week.
	 *
	 * @return CalendarDay
	 */
	public CalendarDay getFirstDay() {
		return days.get(0);
	}

	/**
	 * Gets the current day of the week.
	 *
	 * @return CalendarDay
	 */
	public CalendarDay getCurrentDay() {
		for(CalendarDay calendarDay : getDays())
    		if(calendarDay.isCurrentDay())
    			return calendarDay;

		throw new IllegalArgumentException("Attempted to call getCurrentDay() on a CalendarWeek that is not the current week");
	}

	/**
	 * Gets the last day of the week.
	 *
	 * @return CalendarDay
	 */
	public CalendarDay getLastDay() {
		return days.get(days.size() - 1);
	}

	// getters
	public List<CalendarDay> getDays() {
		return days;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public int getId() {
		return id;
	}

}