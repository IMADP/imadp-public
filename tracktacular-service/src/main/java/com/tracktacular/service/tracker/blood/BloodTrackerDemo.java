package com.tracktacular.service.tracker.blood;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * BloodTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BloodTrackerDemo extends AbstractTrackerDemo {
	private BloodTrackerFacade trackerFacade;

	// constructor
	public BloodTrackerDemo(BloodTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		addBloodPressure(user, 130, 85, new DateTime().minusYears(5).withDayOfMonth(17));
		addBloodPressure(user, 125, 84, new DateTime().minusYears(4).withDayOfMonth(17));
		addBloodPressure(user, 120, 83, new DateTime().minusYears(3).withDayOfMonth(17));
		addBloodPressure(user, 115, 82, new DateTime().minusYears(2).withDayOfMonth(17));
		addBloodPressure(user, 110, 81, new DateTime().minusYears(1).withDayOfMonth(17));
		addBloodPressure(user, 110, 80, new DateTime().withDayOfMonth(17));
	}

	/**
	 * Adds a blood pressure entry.
	 *
	 * @param user
	 * @param systolic
	 * @param diastolic
	 * @param date
	 */
	private void addBloodPressure(User user, Integer systolic, Integer diastolic, DateTime date) {
		BloodPressure bloodPressure = new BloodPressure(user);
		bloodPressure.setSystolic(systolic);
		bloodPressure.setDiastolic(diastolic);
		bloodPressure.setDate(date);
		trackerFacade.saveBloodPressure(bloodPressure);
	}

}
