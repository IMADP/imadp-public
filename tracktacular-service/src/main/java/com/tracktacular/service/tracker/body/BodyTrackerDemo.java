package com.tracktacular.service.tracker.body;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * BodyTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BodyTrackerDemo extends AbstractTrackerDemo {
	private BodyTrackerFacade trackerFacade;

	// constructor
	public BodyTrackerDemo(BodyTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Body body;

		body = new Body();
		body.setUser(user);
		body.setWeight(175.5);
		body.setHeight(5.9);
		body.setChest(33.5);
		body.setBiceps(13.5);
		body.setWaist(32.0);
		body.setDate(new DateTime().minusMonths(5).withDayOfMonth(1));

		trackerFacade.saveBody(body);

		body = new Body();
		body.setUser(user);
		body.setWeight(177.0);
		body.setHeight(5.9);
		body.setChest(34.0);
		body.setBiceps(14.0);
		body.setWaist(32.0);
		body.setDate(new DateTime().minusMonths(4).withDayOfMonth(1));

		trackerFacade.saveBody(body);

		body = new Body();
		body.setUser(user);
		body.setWeight(179.0);
		body.setHeight(5.9);
		body.setChest(34.5);
		body.setBiceps(14.0);
		body.setWaist(32.0);
		body.setDate(new DateTime().minusMonths(3).withDayOfMonth(1));

		trackerFacade.saveBody(body);

		body = new Body();
		body.setUser(user);
		body.setWeight(181.0);
		body.setHeight(5.9);
		body.setChest(35.0);
		body.setBiceps(14.5);
		body.setWaist(32.0);
		body.setDate(new DateTime().minusMonths(2).withDayOfMonth(1));

		trackerFacade.saveBody(body);

		body = new Body();
		body.setUser(user);
		body.setWeight(183.0);
		body.setHeight(5.9);
		body.setChest(35.0);
		body.setBiceps(14.5);
		body.setWaist(33.0);
		body.setDate(new DateTime().minusMonths(1).withDayOfMonth(1));

		trackerFacade.saveBody(body);

		body = new Body();
		body.setUser(user);
		body.setWeight(185.5);
		body.setHeight(5.9);
		body.setChest(35.5);
		body.setBiceps(15.0);
		body.setWaist(33.0);
		body.setDate(new DateTime().withDayOfMonth(1));

		trackerFacade.saveBody(body);
	}

}
