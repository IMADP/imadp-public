package com.tracktacular.service.tracker.cholesterol;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * CholesterolTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CholesterolTrackerDemo extends AbstractTrackerDemo {
	private CholesterolTrackerFacade trackerFacade;

	// constructor
	public CholesterolTrackerDemo(CholesterolTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Cholesterol cholesterol;

		cholesterol = new Cholesterol();
		cholesterol.setUser(user);
		cholesterol.setLdlCholesterol(150);
		cholesterol.setHdlCholesterol(50);
		cholesterol.setTriglycerides(200);
		cholesterol.setTotalCholesterol(240);
		cholesterol.setDate(new DateTime().minusYears(5).withDayOfMonth(17));

		trackerFacade.saveCholesterol(cholesterol);

		cholesterol = new Cholesterol();
		cholesterol.setUser(user);
		cholesterol.setLdlCholesterol(140);
		cholesterol.setHdlCholesterol(52);
		cholesterol.setTriglycerides(190);
		cholesterol.setTotalCholesterol(230);
		cholesterol.setDate(new DateTime().minusYears(4).withDayOfMonth(17));

		trackerFacade.saveCholesterol(cholesterol);

		cholesterol = new Cholesterol();
		cholesterol.setUser(user);
		cholesterol.setLdlCholesterol(130);
		cholesterol.setHdlCholesterol(54);
		cholesterol.setTriglycerides(180);
		cholesterol.setTotalCholesterol(220);
		cholesterol.setDate(new DateTime().minusYears(3).withDayOfMonth(17));

		trackerFacade.saveCholesterol(cholesterol);

		cholesterol = new Cholesterol();
		cholesterol.setUser(user);
		cholesterol.setLdlCholesterol(120);
		cholesterol.setHdlCholesterol(56);
		cholesterol.setTriglycerides(170);
		cholesterol.setTotalCholesterol(210);
		cholesterol.setDate(new DateTime().minusYears(2).withDayOfMonth(17));

		trackerFacade.saveCholesterol(cholesterol);

		cholesterol = new Cholesterol();
		cholesterol.setUser(user);
		cholesterol.setLdlCholesterol(110);
		cholesterol.setHdlCholesterol(58);
		cholesterol.setTriglycerides(160);
		cholesterol.setTotalCholesterol(200);
		cholesterol.setDate(new DateTime().minusYears(1).withDayOfMonth(17));

		trackerFacade.saveCholesterol(cholesterol);

		cholesterol = new Cholesterol();
		cholesterol.setUser(user);
		cholesterol.setLdlCholesterol(100);
		cholesterol.setHdlCholesterol(60);
		cholesterol.setTriglycerides(150);
		cholesterol.setTotalCholesterol(190);
		cholesterol.setDate(new DateTime().withDayOfMonth(17));

		trackerFacade.saveCholesterol(cholesterol);
	}

}
