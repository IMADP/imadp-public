package com.tracktacular.service.tracker.body;

import org.joda.time.DateTime;

import com.tracktacular.service.Recurrence;
import com.tracktacular.service.RecurrenceType;
import com.tracktacular.service.tracker.AbstractTrackerPreferences;


/**
 * BodyTrackerPreferences
 *
 * Tracker preferences for a Tracktacular user.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BodyTrackerPreferences extends AbstractTrackerPreferences {
	private Recurrence alertRecurrence = new Recurrence(RecurrenceType.MONTH, 1);

	/**
     * Returns the recommended next test date for body measurement.
     *
     * @param body
     * @return DateTime
     */
    public DateTime getRecommendedTestDate(Body body) {
    	if(body == null)
    		return new DateTime();

    	if(!alertRecurrence.hasPeriod())
    		return body.getDate().plusMonths(1);

    	return alertRecurrence.getNextOccurrence(body.getDate());
    }

	// getters and setters
	public Recurrence getAlertRecurrence() {
		return alertRecurrence;
	}

	public void setAlertRecurrence(Recurrence alertRecurrence) {
		this.alertRecurrence = alertRecurrence;
	}

}