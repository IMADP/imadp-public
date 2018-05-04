package com.tracktacular.web.page.tracker.birthday;

import net.sourceforge.stripes.action.UrlBinding;

import org.joda.time.DateTime;

import com.tracktacular.service.tracker.birthday.Birthdays;


/**
 * BirthdaysActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-birthday/birthdays/month-{selectedMonth=now}")
public class BirthdaysActionBean extends BirthdayTrackerActionBean {

	// default selectedMonth param
	private static final String DEFAULT_SELECTED_MONTH_PARAM = "now";

	private Birthdays birthdays;
	private String selectedMonth;

	/**
	 * Returns a list of birthdays.
	 *
	 * @return Birthdays
	 */
	public Birthdays getBirthdays() {
		if(birthdays == null)
			birthdays = getTrackerFacade().findActiveBirthdays(getTrackerUser());

		return birthdays;
	}

	// getter and setters
	public String getSelectedMonth() {
		if(DEFAULT_SELECTED_MONTH_PARAM.equalsIgnoreCase(selectedMonth))
			return String.valueOf(new DateTime().getMonthOfYear());

		return selectedMonth;
	}

	public void setSelectedMonth(String selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

}