package com.tracktacular.service.tracker;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.imadp.core.AbstractSerializable;

/**
 * AbstractTrackerReport
 *
 * Base class for tracker report segments.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public abstract class AbstractTrackerReport extends AbstractSerializable implements TrackerReport {

	@Override
	public final Tracker getTracker() {
		String simpleClassName = getClass().getSimpleName();
		return Tracker.valueOf(simpleClassName.substring(0, simpleClassName.indexOf("TrackerReport")).toUpperCase());
	}

	@Override
	public int getAlertCount() {
		return 0;
	}

	@Override
	public final boolean isAlerts() {
		return isEnabled() ? getAlertCount() > 0 : false;
	}

	/**
	 * Returns true if the given date occurs in the past or on the current day.
	 *
	 * @param date
	 * @return boolean
	 */
	protected final boolean isDue(DateTime date) {
		return date == null ? false : date.toLocalDate().isBefore(new LocalDate().plusDays(1));
	}

    /**
     * Returns true if the given date matches the current date, false otherwise.
     *
     * @param date
     * @return boolean
     */
    protected final boolean isCurrentDate(DateTime date) {
    	return date == null ? false : new LocalDate().isEqual(date.toLocalDate());
    }

}