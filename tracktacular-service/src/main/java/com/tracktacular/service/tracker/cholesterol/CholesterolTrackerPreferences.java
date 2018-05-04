package com.tracktacular.service.tracker.cholesterol;

import org.joda.time.DateTime;

import com.tracktacular.service.Recurrence;
import com.tracktacular.service.RecurrenceType;
import com.tracktacular.service.tracker.AbstractTrackerPreferences;


/**
 * CholesterolTrackerPreferences
 *
 * Tracker preferences for a Tracktacular user.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CholesterolTrackerPreferences extends AbstractTrackerPreferences {
	private Recurrence alertRecurrence = new Recurrence(RecurrenceType.YEAR, 1);

    /**
     * Returns the recommended next test date for cholesterol levels.
     *
     * @param cholesterol
     * @return DateTime
     */
    public DateTime getRecommendedTestDate(Cholesterol cholesterol) {
    	if(cholesterol == null)
    		return new DateTime();

    	if(!alertRecurrence.hasPeriod())
    		return cholesterol.getDate().plusYears(1);

    	return alertRecurrence.getNextOccurrence(cholesterol.getDate());
    }

	// getters and setters
	public Recurrence getAlertRecurrence() {
		return alertRecurrence;
	}

	public void setAlertRecurrence(Recurrence alertRecurrence) {
		this.alertRecurrence = alertRecurrence;
	}

}