package com.tracktacular.service.tracker.calendar;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * CalendarTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class CalendarTrackerReport extends AbstractTrackerReport {
	private final CalendarWeek calendarWeek;
	private final List<CalendarEntry> calendarEntryAlerts;

    // constructor
    public CalendarTrackerReport(CalendarMonth calendarMonth) {
    	this.calendarWeek = calendarMonth.getCurrentWeek();
    	this.calendarEntryAlerts = Lists.newArrayList();

    	// add alerts
		for(CalendarItem calendarItem : calendarWeek.getCurrentDay().getItems())
			if(calendarItem.getCalendarEntry().isAlert())
				calendarEntryAlerts.add(calendarItem.getCalendarEntry());
    }

    @Override
    public boolean isEnabled() {
    	return calendarWeek.getItemCount() > 0;
    }

    @Override
    public int getAlertCount() {
    	return calendarEntryAlerts.size();
    }

    // getters
    public CalendarWeek getCalendarWeek() {
		return calendarWeek;
	}

	public List<CalendarEntry> getCalendarEntryAlerts() {
		return calendarEntryAlerts;
	}

}