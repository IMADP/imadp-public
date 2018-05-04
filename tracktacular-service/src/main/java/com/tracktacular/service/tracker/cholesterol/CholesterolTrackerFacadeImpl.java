package com.tracktacular.service.tracker.cholesterol;

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
import com.tracktacular.service.tracker.TrackerReport;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * CholesterolTrackerFacadeImpl
 *
 * The standard implementation of the CholesterolTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CholesterolTrackerFacadeImpl extends AbstractTrackerFacade
	implements CholesterolTrackerFacade {

	private CholesterolService cholesterolService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(cholesterolService);
	}

	@Override
	public Cholesterol getCholesterol(User user, String uid) {
		return cholesterolService.findFirstByUser(user, uid);
	}

	@Override
	public List<Cholesterol> findActiveCholesterols(User user, CriteriaParams<Cholesterol> criteriaParams) {
		return cholesterolService.findByUser(user, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findActiveCholesterolCount(User user) {
		return cholesterolService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<Cholesterol> findDeletedCholesterols(User user, CriteriaParams<Cholesterol> criteriaParams) {
		return cholesterolService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedCholesterolCount(User user) {
		return cholesterolService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public void saveCholesterol(Cholesterol cholesterol) {
		new CholesterolValidator("cholesterol", cholesterol).validate();
		cholesterolService.save(cholesterol);
	}

	@Override
	public void deleteCholesterol(Cholesterol cholesterol) {
		cholesterolService.delete(cholesterol);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Cholesterol> cholesterols = cholesterolService.findByUser(user, CriteriaParams.<Cholesterol>of(Results.ALL));

		for(Cholesterol cholesterol : cholesterols)
			deleteCholesterol(cholesterol);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new CholesterolTrackerDemo(this);
	}

	@Override
	public TrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new CholesterolTrackerReport(preferences.getTrackers().getCholesterolTrackerPreferences(), getFirst(findActiveCholesterols(user,
				CriteriaParams.<Cholesterol>of(Results.ALL, Order.desc(Cholesterol.DATE)))));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();
		List<Cholesterol> activeCholesterols = findActiveCholesterols(user,
				CriteriaParams.<Cholesterol>of(Results.ALL, Order.desc(Cholesterol.DATE)));

		// active cholesterol entries
		for(Cholesterol cholesterol : activeCholesterols)
		{
			if(interval.contains(cholesterol.getDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, cholesterol.getDate());
				calendarEntry.setTracker(Tracker.CHOLESTEROL);
				calendarEntry.setName("Cholesterol entry");
				calendarEntries.add(calendarEntry);
			}
		}

		// next recommended date
		Cholesterol cholesterol = getFirst(activeCholesterols);
		DateTime nextDate = preferences.getTrackers().getCholesterolTrackerPreferences().getRecommendedTestDate(cholesterol);

		if(cholesterol != null && interval.contains(nextDate))
		{
			CalendarEntry calendarEntry = new CalendarEntry(user, nextDate);
			calendarEntry.setTracker(Tracker.CHOLESTEROL);
			calendarEntry.setName("Cholesterol reminder");
			calendarEntry.setDescription(String.format("Your next cholesterol test is recommended on %s",
					Cholesterol.DATE_FORMATTER.print(nextDate)));

			calendarEntries.add(calendarEntry);
		}

		return calendarEntries;
	}

	// getters and setters
	public CholesterolService getCholesterolService() {
		return cholesterolService;
	}

	public void setCholesterolService(CholesterolService cholesterolService) {
		this.cholesterolService = cholesterolService;
	}

}
