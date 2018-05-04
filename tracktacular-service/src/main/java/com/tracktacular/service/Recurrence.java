package com.tracktacular.service;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.imadp.core.AbstractSerializable;

/**
 * Recurrence
 *
 * A specified period of recurrence, used in conjunction with calendar entries.
 *
 * @version 1.0
 */
public class Recurrence extends AbstractSerializable {
	private RecurrenceType type;
	private int length;

	// constructor
	public Recurrence() {
		this(RecurrenceType.NONE, 0);
	}

	// constructor
	public Recurrence(RecurrenceType type, int length) {
		this.type = type;
		this.length = length;
	}

	/**
	 * Returns true if the recurrence has a period, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasPeriod() {
		return type != null && type != RecurrenceType.NONE && length > 0;
	}

	/**
	 * Returns true if the recurrence is based on days, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isDays() {
		return RecurrenceType.DAY == type;
	}

	/**
	 * Returns true if the recurrence is based on weeks, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isWeeks() {
		return RecurrenceType.WEEK == type;
	}

	/**
	 * Returns true if the recurrence is based on months, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isMonths() {
		return RecurrenceType.MONTH == type;
	}

	/**
	 * Returns true if the recurrence is based on years, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isYears() {
		return RecurrenceType.YEAR == type;
	}

	/**
	 * Returns the next occurence after the given date, or null if no recurrence is specified.
	 *
	 * @param date
	 * @return DateTime
	 */
	public DateTime getNextOccurrence(DateTime date) {
		return !hasPeriod() ? null : date.plus(getPeriod());
	}

	/**
	 * Returns the Period for this recurrence instance, or null if no period was found.
	 *
	 * @return Period
	 */
	public Period getPeriod() {
		if(!hasPeriod())
			return null;

		switch(type)
		{
			case DAY: 	return Period.days(length);
			case WEEK: 	return Period.weeks(length);
			case MONTH: return Period.months(length);
			case YEAR: 	return Period.years(length);
			default: 	return null;
		}
	}

	/**
	 * Sets the period by desconstructing it into recurrence type and length.
	 *
	 * @param period
	 */
	public void setPeriod(Period period) {

		// set default values
		this.type = RecurrenceType.NONE;
		this.length = 0;

		// null periods
		if(period == null)
			return;

		// day periods
		if(setRecurrenceType(RecurrenceType.DAY, period.getDays()))
			return;

		// week periods
		if(setRecurrenceType(RecurrenceType.WEEK, period.getWeeks()))
			return;

		// month periods
		if(setRecurrenceType(RecurrenceType.MONTH, period.getMonths()))
			return;

		// year periods
		if(setRecurrenceType(RecurrenceType.YEAR, period.getYears()))
			return;
	}

	/**
	 * Given a recurrence type and a length, this method will conditionally set
	 * the value of the type and length only if the length was greater than 0.
	 * Returns true if the value was set, false otherwise.
	 *
	 * @param type
	 * @param length
	 * @return boolean
	 */
	private boolean setRecurrenceType(RecurrenceType type, int length) {
		if(length > 0)
		{
			this.type = type;
			this.length = length;
			return true;
		}

		return false;
	}

	// getters and setters
	public RecurrenceType getType() {
		return type;
	}

	public void setType(RecurrenceType type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return length + "-" + type;
	}

}