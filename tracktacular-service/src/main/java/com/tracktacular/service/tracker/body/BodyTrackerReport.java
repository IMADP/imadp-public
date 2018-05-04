package com.tracktacular.service.tracker.body;

import org.joda.time.DateTime;

import com.tracktacular.service.Recurrence;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * BodyTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class BodyTrackerReport extends AbstractTrackerReport {
	private final Body body;
	private final Recurrence alertRecurrence;
	private final DateTime recommendedTestDate;

    // constructor
    public BodyTrackerReport(BodyTrackerPreferences preferences, Body body) {
        this.body = body;
        this.alertRecurrence = preferences.getAlertRecurrence();
        this.recommendedTestDate = preferences.getRecommendedTestDate(body);
    }

    @Override
    public boolean isEnabled() {
    	return body != null;
    }

    @Override
    public int getAlertCount() {
    	return alertRecurrence.hasPeriod() && isDue(getRecommendedTestDate()) ? 1 : 0;
    }

    /**
     * Returns the recommended next test date for body levels.
     *
     * @return DateTime
     */
    public DateTime getRecommendedTestDate() {
    	return recommendedTestDate;
    }

	// getters
    public Body getBody() {
		return body;
	}

}