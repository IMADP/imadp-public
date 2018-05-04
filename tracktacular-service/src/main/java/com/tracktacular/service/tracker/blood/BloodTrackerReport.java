package com.tracktacular.service.tracker.blood;

import org.joda.time.DateTime;

import com.tracktacular.service.Recurrence;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * BloodTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class BloodTrackerReport extends AbstractTrackerReport {
	private final BloodPressure bloodPressure;
	private final Recurrence alertRecurrence;
	private final DateTime recommendedTestDate;

    // constructor
    public BloodTrackerReport(BloodTrackerPreferences preferences, BloodPressure bloodPressure) {
        this.bloodPressure = bloodPressure;
        this.alertRecurrence = preferences.getAlertRecurrence();
        this.recommendedTestDate = preferences.getRecommendedTestDate(bloodPressure);
    }

    @Override
    public boolean isEnabled() {
    	return bloodPressure != null;
    }

    @Override
    public int getAlertCount() {
    	return alertRecurrence.hasPeriod() && isDue(getRecommendedTestDate()) ? 1 : 0;
    }

    /**
     * Returns the recommended next test date for bloodPressure levels.
     *
     * @return DateTime
     */
    public DateTime getRecommendedTestDate() {
    	return recommendedTestDate;
    }

	// getters
    public BloodPressure getBloodPressure() {
		return bloodPressure;
	}

}