package com.tracktacular.web.page.tracker.calendar;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.Resolution;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.calendar.CalendarEntry;
import com.tracktacular.service.tracker.calendar.CalendarMonth;
import com.tracktacular.service.tracker.calendar.CalendarTrackerFacade;
import com.tracktacular.service.tracker.calendar.CalendarTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * CalendarTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class CalendarTrackerActionBean extends TrackerActionBean<CalendarTrackerFacade, CalendarTrackerPreferences> {

	// date formatters
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("MM-yyyy");

	// default date param
	private static final String DEFAULT_DATE_PARAM = "now";

	// properties
	private CalendarEntry calendarEntry;
	private CalendarMonth calendarMonth;
	private DateTime date;

	@Override
	public Tracker getTracker() {
		return Tracker.CALENDAR;
	}

	/**
	 * Converts a DateTime.
	 *
	 * @param input
	 * @return DateTime
	 */
	public DateTime convertDateTime(String input) {
		if(DEFAULT_DATE_PARAM.equalsIgnoreCase(input))
			return new DateTime();

		return DATE_FORMATTER.parseDateTime(input);
	}

	/**
	 * Returns the calendarMonth for a given user.
	 *
	 * @return CalendarMonth
	 */
	public CalendarMonth getCalendarMonth() {
		if(calendarMonth == null)
			calendarMonth = getTrackerFacade().getCalendarMonth(getTrackerUser(), getDate(), isPublicMode());

		return calendarMonth;
	}

	/**
	 * Returns a list of all months, starting from January.
	 *
	 * @return List<DateTime>
	 */
	public List<DateTime> getMonths() {
		List<DateTime> months = new ArrayList<>();

		for(int i=1; i<=12; i++)
			months.add(getDate().withMonthOfYear(i));

		return months;
	}

	/**
	 * Save or updates a CalendarEntry.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveCalendarEntry() {
		CalendarEntry calendarEntry = getCalendarEntry();
		populatePersistableUser(calendarEntry);
		getTrackerFacade().saveCalendarEntry(calendarEntry);

		if(calendarEntry.isActiveState())
			getContext().addSuccessMessage("calendarEntry.saveCalendarEntry.success");

		else if(calendarEntry.isDeletedState())
			getContext().addSuccessMessage("calendarEntry.deleteCalendarEntry.success");

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a CalendarEntry.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteCalendarEntry() {
		CalendarEntry calendarEntry = getCalendarEntry();
		getTrackerFacade().deleteCalendarEntry(calendarEntry);
		getContext().addSuccessMessage("calendarEntry.deleteCalendarEntry.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public DateTime getDate() {
		return date == null ? new DateTime() : date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public CalendarTrackerFacade getCalendarTrackerFacade() {
		return getTrackerFacade();
	}

	public CalendarEntry getCalendarEntry() {
		return calendarEntry;
	}

	public void setCalendarEntry(CalendarEntry calendarEntry) {
		this.calendarEntry = calendarEntry;
	}

}