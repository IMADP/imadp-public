package com.tracktacular.service.tracker.blood;

import org.joda.time.DateTime;

import com.tracktacular.service.Recurrence;
import com.tracktacular.service.RecurrenceType;
import com.tracktacular.service.tracker.AbstractTrackerPreferences;


/**
 * BloodTrackerPreferences
 *
 * Tracker preferences for a Tracktacular user.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BloodTrackerPreferences extends AbstractTrackerPreferences {
	private Recurrence alertRecurrence = new Recurrence(RecurrenceType.YEAR, 1);

	/**
     * Returns the recommended next test date for bloodPressure levels.
     *
     * @param bloodPressure
     * @return DateTime
     */
    public DateTime getRecommendedTestDate(BloodPressure bloodPressure) {
    	if(bloodPressure == null)
    		return new DateTime();

    	if(!alertRecurrence.hasPeriod())
    		return bloodPressure.getDate().plusYears(1);

    	return alertRecurrence.getNextOccurrence(bloodPressure.getDate());
    }

	// getters and setters
	public Recurrence getAlertRecurrence() {
		return alertRecurrence;
	}

	public void setAlertRecurrence(Recurrence alertRecurrence) {
		this.alertRecurrence = alertRecurrence;
	}

}