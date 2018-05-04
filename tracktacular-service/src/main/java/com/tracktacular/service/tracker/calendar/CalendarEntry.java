package com.tracktacular.service.tracker.calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;
import com.tracktacular.service.Recurrence;
import com.tracktacular.service.tracker.Tracker;


/**
 * CalendarEntry
 *
 * A representation of a calendar entry.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CalendarEntry extends AbstractPersistableUser {

	// static Properties
	public static final Property<CalendarEntry, String> NAME = Property.of("name");
	public static final Property<CalendarEntry, String> DESCRIPTION = Property.of("description");
	public static final Property<CalendarEntry, String> NOTES = Property.of("notes");
	public static final Property<CalendarEntry, Boolean> ALERT = Property.of("alert");
	public static final Property<CalendarEntry, Recurrence> RECURRENCE = Property.of("recurrence");
	public static final Property<CalendarEntry, DateTime> START_DATE = Property.of("startDate");
	public static final Property<CalendarEntry, DateTime> END_DATE = Property.of("endDate");
	public static final Property<CalendarEntry, DateTime> START_TIME = Property.of("startTime");
	public static final Property<CalendarEntry, DateTime> END_TIME = Property.of("endTime");

	// properties
	private String name;
	private String description;
	private String notes;
	private DateTime startDate;
	private DateTime endDate;
	private DateTime startTime;
	private DateTime endTime;
	private Recurrence recurrence;
	private Tracker tracker = Tracker.CALENDAR;
	private boolean alert;

	// constructor
	public CalendarEntry() {
		this(null, null);
	}

	// constructor
	public CalendarEntry(User user) {
		this(user, null);
	}

	// constructor
	public CalendarEntry(User user, DateTime startDate) {
		super(user);
		this.startDate = startDate;
		this.persistableState =  PersistableState.ACTIVE;
		this.recurrence = new Recurrence();
	}

	/**
	 * Returns true if this entry
	 *
	 * @return boolean
	 */
	public boolean isRecurring() {
		return recurrence != null && recurrence.hasPeriod();
	}

	/**
	 * Retrieves a List of CalendarItems based on the start and end date range.
	 * If no start date is present, an empty list will be returned.
	 * If no end date is present, the list will contain only one item based on the current date.
	 *
	 * @return List<CalendarItem>
	 */
	public List<CalendarItem> getCalendarItems() {
		if(startDate == null)
			return Collections.emptyList();

		List<CalendarItem> calendarItemList = new ArrayList<>();

		// if there is no end date return one item for the start date
		if(endDate == null)
		{
			calendarItemList.add(new CalendarItem(startDate, this));
			return calendarItemList;
		}

		if(startDate.isAfter(endDate))
			throw new IllegalArgumentException("Start date must not exceed end date for CalendarEntry ["+id+"]");

		// loop through each day and add a new entry item to the list
		for(int i=0; i<=Days.daysBetween(startDate, endDate).getDays(); i++)
			calendarItemList.add(new CalendarItem(startDate.plusDays(i), this));

		return calendarItemList;
	}

	/**
     * Returns true if the given dateTime occurs within a recurring period specified by this CalendarEntry,
     *
     * @note This method assumes that the recurrence period is EITHER day, week, month, or year based.
     *       It will not work for any combination of these fields.
     *
     * @param dateTime
     * @return boolean
     */
	public boolean isWithinRecurringPeriod(DateTime dateTime) {
		if(!isRecurring() || dateTime.isBefore(startDate))
			return false;

		int calendarEntryRange = endDate == null ? 0 : Days.daysBetween(startDate, endDate).getDays();

		// if the period is based on days
		if(recurrence.isDays())
		{
			MutablePeriod period = new MutablePeriod(startDate, dateTime, PeriodType.days());

			// loop through the length of the start and end range to find any recurring days
			for(int i = 0; i <= calendarEntryRange; i++)
			{
				// return true if the difference in days is a multiple of the day period
				if(period.getDays() % recurrence.getLength() == 0)
					return true;

				// traverse backwards to see if this date is already within the range
				period.addDays(-1);
			}

			return false;
		}

		// if the period is based on weeks
		if(recurrence.isWeeks())
		{
			MutablePeriod period = new MutablePeriod(startDate, dateTime, PeriodType.forFields(
					new DurationFieldType[] {DurationFieldType.weeks(), DurationFieldType.days()}));

			// loop through the length of the start and end range to find any recurring weeks
			for(int i = 0; i <= calendarEntryRange; i++)
			{
				// return true if the difference in weeks is a multiple of the week period with no days left over
				if(period.getWeeks() % recurrence.getLength() == 0 && period.getDays() == 0)
					return true;

				// traverse backwards to see if this date is already within the range
				period.addDays(-1);
			}

			return false;
		}

		// if the period is based on months
		if(recurrence.isMonths())
		{
			MutablePeriod period = new MutablePeriod(startDate, dateTime, PeriodType.forFields(
					new DurationFieldType[] {DurationFieldType.months(), DurationFieldType.days()}));

			// loop through the length of the start and end range to find any recurring months
			for(int i = 0; i <= calendarEntryRange; i++)
			{
				// return true if the difference in months is a multiple of the month period with no days left over
				if(period.getMonths() % recurrence.getLength() == 0 && period.getDays() == 0)
					return true;

				// traverse backwards to see if this date is already within the range
				period.addDays(-1);
			}

			return false;
		}

		// if the period is based on years
		if(recurrence.isYears())
		{
			MutablePeriod period = new MutablePeriod(startDate, dateTime, PeriodType.forFields(
					new DurationFieldType[] {DurationFieldType.years(), DurationFieldType.days()}));

			// loop through the length of the start and end range to find any recurring years
			for(int i = 0; i <= calendarEntryRange; i++)
			{
				// return true if the difference in years is a multiple of the year period with no days left over
				if(period.getYears() % recurrence.getLength() == 0 && period.getDays() == 0)
					return true;

				// traverse backwards to see if this date is already within the range
				period.addDays(-1);
			}

			return false;
		}

		return false;
	}

	// getters and setters
	public String getStartDateString() {
		return toDateString(startDate);
	}

	public void setStartDateString(String startDateString) {
		this.startDate = fromDateString(startDateString);
	}

	public String getEndDateString() {
		return toDateString(endDate);
	}

	public void setEndDateString(String endDateString) {
		this.endDate = fromDateString(endDateString);
	}

	public String getStartTimeString() {
		return toTimeString(startTime);
	}

	public void setStartTimeString(String startTimeString) {
		this.startTime = fromTimeString(startTimeString);
	}

	public String getEndTimeString() {
		return toTimeString(endTime);
	}

	public void setEndTimeString(String endTimeString) {
		this.endTime = fromTimeString(endTimeString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Recurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

}