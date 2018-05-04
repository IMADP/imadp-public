package com.tracktacular.service.tracker.birthday;

import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;
import com.tracktacular.service.tracker.birthday.Birthdays.Month;

/**
 * BirthdayTrackerReport
 *
 * Contains report tracker information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class BirthdayTrackerReport extends AbstractTrackerReport {
	private final List<Birthday> alertedBirthdays;
	private final List<Birthday> upcomingBirthdays;
	private final List<Birthday> currentBirthdays;
	private final DateTime currentMonth;
	private final DateTime nextMonth;
	private final int birthdayCount;
	private final boolean alertOnBirthdays;

    // constructor
    public BirthdayTrackerReport(BirthdayTrackerPreferences preferences, Birthdays birthdays) {
    	this.currentMonth = new DateTime();
    	this.nextMonth = currentMonth.plusMonths(1);
    	this.birthdayCount = birthdays.getAllBirthdayCount();
    	this.alertedBirthdays = Lists.newArrayList();
    	this.upcomingBirthdays = Lists.newArrayList();
    	this.currentBirthdays = Lists.newArrayList();
    	this.alertOnBirthdays = preferences.isAlertOnBirthdays();

    	// add alerted birthdays
    	for(Month month : birthdays.getBirthdayMonths())
    		for(Birthday birthday : month.getBirthdays())
    			if(birthday.isAlertInterval())
    				alertedBirthdays.add(birthday);

    	// add upcoming birthdays for the current month
    	for(Birthday birthday : birthdays.getBirthdayMonths().get(currentMonth.getMonthOfYear() - 1).getBirthdays())
    		if(!birthday.isPast())
    			this.upcomingBirthdays.add(birthday);

    	// add upcoming birthdays for the next month
    	for(Birthday birthday : birthdays.getBirthdayMonths().get(nextMonth.getMonthOfYear() - 1).getBirthdays())
    		if(!birthday.isPast())
    			this.upcomingBirthdays.add(birthday);

    	// add current birthdays
    	for(Birthday birthday : upcomingBirthdays)
    		if(birthday.isToday())
    			this.currentBirthdays.add(birthday);
    }

    @Override
	public boolean isEnabled() {
    	return getUpcomingBirthdayCount() + alertedBirthdays.size() > 0;
    }

    @Override
    public int getAlertCount() {
    	return alertedBirthdays.size() + (alertOnBirthdays ? currentBirthdays.size() : 0);
    }

    /**
     * Returns the count of birthdays for the current month.
     *
     * @return int
     */
    public int getUpcomingBirthdayCount() {
		return upcomingBirthdays.size();
	}

    // getters
	public DateTime getCurrentMonth() {
		return currentMonth;
	}

	public DateTime getNextMonth() {
		return nextMonth;
	}

	public List<Birthday> getUpcomingBirthdays() {
		return upcomingBirthdays;
	}

	public int getBirthdayCount() {
		return birthdayCount;
	}

	public List<Birthday> getCurrentBirthdays() {
		return currentBirthdays;
	}

	public List<Birthday> getAlertedBirthdays() {
		return alertedBirthdays;
	}

}