package com.tracktacular.service.tracker.body;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * BodyTrackerFacadeImpl
 *
 * The standard implementation of the BodyTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BodyTrackerFacadeImpl extends AbstractTrackerFacade
	implements BodyTrackerFacade {

	private BodyService bodyService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(bodyService);
	}

	@Override
	public Body getBody(User user, String uid) {
		return bodyService.findFirstByUser(user, uid);
	}

	@Override
	public List<Body> findActiveBodies(User user, CriteriaParams<Body> criteriaParams) {
		return bodyService.findByUser(user, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findActiveBodyCount(User user) {
		return bodyService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<Body> findDeletedBodies(User user, CriteriaParams<Body> criteriaParams) {
		return bodyService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedBodyCount(User user) {
		return bodyService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public void saveBody(Body body) {
		new BodyValidator("body", body).validate();
		bodyService.save(body);
	}

	@Override
	public void deleteBody(Body body) {
		bodyService.delete(body);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Body> bodies = bodyService.findByUser(user, CriteriaParams.<Body>of(Results.ALL));

		for(Body body : bodies)
			deleteBody(body);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new BodyTrackerDemo(this);
	}

	@Override
	public BodyTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new BodyTrackerReport(preferences.getTrackers().getBodyTrackerPreferences(), getFirst(findActiveBodies(user, CriteriaParams.<Body>of(
				Results.ONE, Order.desc(Body.DATE)))));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();
		List<Body> activeBodies = findActiveBodies(user, CriteriaParams.<Body>of(Results.ALL, Order.desc(Body.DATE)));

		// active body entries
		for(Body body : activeBodies)
		{
			if(interval.contains(body.getDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, body.getDate());
				calendarEntry.setTracker(Tracker.BODY);
				calendarEntry.setName("Body entry");
				calendarEntries.add(calendarEntry);
			}

		}

		// next recommended date
		Body body = getFirst(activeBodies);
		DateTime nextDate = preferences.getTrackers().getBodyTrackerPreferences().getRecommendedTestDate(body);

		if(body != null && interval.contains(nextDate))
		{
			CalendarEntry calendarEntry = new CalendarEntry(user, nextDate);
			calendarEntry.setTracker(Tracker.BODY);
			calendarEntry.setName("Body reminder");
			calendarEntry.setDescription(String.format("Your next body test is recommended on %s",
					Body.DATE_FORMATTER.print(nextDate)));

			calendarEntries.add(calendarEntry);
		}

		return calendarEntries;
	}

	// getters and setters
	public BodyService getBodyService() {
		return bodyService;
	}

	public void setBodyService(BodyService bodyService) {
		this.bodyService = bodyService;
	}

}
