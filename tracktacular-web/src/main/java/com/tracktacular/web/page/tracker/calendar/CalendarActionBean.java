package com.tracktacular.web.page.tracker.calendar;

import net.sourceforge.stripes.action.UrlBinding;

import org.joda.time.DateTime;


/**
 * CalendarActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-calendar/calendar/date-{date=now}")
public class CalendarActionBean extends CalendarTrackerActionBean {

	/**
	 * Returns the previous month.
	 *
	 * @return DateTime
	 */
	public DateTime getPreviousMonth() {
		return getDate().minusMonths(1);
	}

	/**
	 * Returns the next month.
	 *
	 * @return DateTime
	 */
	public DateTime getNextMonth() {
		return getDate().plusMonths(1);
	}

}