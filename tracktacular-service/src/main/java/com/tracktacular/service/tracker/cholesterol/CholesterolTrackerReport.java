package com.tracktacular.service.tracker.cholesterol;

import org.joda.time.DateTime;

import com.tracktacular.service.Recurrence;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * CholesterolTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class CholesterolTrackerReport extends AbstractTrackerReport {
	private final Cholesterol cholesterol;
	private final Recurrence alertRecurrence;
	private final DateTime recommendedTestDate;

    // constructor
    public CholesterolTrackerReport(CholesterolTrackerPreferences preferences, Cholesterol cholesterol) {
        this.cholesterol = cholesterol;
        this.alertRecurrence = preferences.getAlertRecurrence();
        this.recommendedTestDate = preferences.getRecommendedTestDate(cholesterol);
    }

    @Override
    public boolean isEnabled() {
    	return cholesterol != null;
    }

    @Override
    public int getAlertCount() {
    	return alertRecurrence.hasPeriod() && isDue(getRecommendedTestDate()) ? 1 : 0;
    }

    /**
     * Returns the recommended next test date for cholesterol levels.
     *
     * @return DateTime
     */
    public DateTime getRecommendedTestDate() {
    	return recommendedTestDate;
    }

	// getters
    public Cholesterol getCholesterol() {
		return cholesterol;
	}

}