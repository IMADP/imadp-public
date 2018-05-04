package com.tracktacular.service.tracker.blood;

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
 * BloodTrackerFacadeImpl
 *
 * The standard implementation of the BloodTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BloodTrackerFacadeImpl extends AbstractTrackerFacade
	implements BloodTrackerFacade {

	private BloodPressureService bloodPressureService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(bloodPressureService);
	}

	@Override
	public BloodPressure getBloodPressure(User user, String uid) {
		return bloodPressureService.findFirstByUser(user, uid);
	}

	@Override
	public List<BloodPressure> findActiveBloodPressures(User user, CriteriaParams<BloodPressure> criteriaParams) {
		return bloodPressureService.findByUser(user, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findActiveBloodPressureCount(User user) {
		return bloodPressureService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<BloodPressure> findDeletedBloodPressures(User user, CriteriaParams<BloodPressure> criteriaParams) {
		return bloodPressureService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedBloodPressureCount(User user) {
		return bloodPressureService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public void saveBloodPressure(BloodPressure bloodPressure) {
		new BloodPressureValidator("bloodPressure", bloodPressure).validate();
		bloodPressureService.save(bloodPressure);
	}

	@Override
	public void deleteBloodPressure(BloodPressure bloodPressure) {
		bloodPressureService.delete(bloodPressure);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<BloodPressure> bloodPressures = bloodPressureService.findByUser(user, CriteriaParams.<BloodPressure>of(Results.ALL));

		for(BloodPressure bloodPressure : bloodPressures)
			deleteBloodPressure(bloodPressure);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new BloodTrackerDemo(this);
	}

	@Override
	public BloodTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new BloodTrackerReport(preferences.getTrackers().getBloodTrackerPreferences(), getFirst(findActiveBloodPressures(user,
				CriteriaParams.<BloodPressure>of(Results.ALL, Order.desc(BloodPressure.DATE)))));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();
		List<BloodPressure> activeBloodPressures = findActiveBloodPressures(user,
				CriteriaParams.<BloodPressure>of(Results.ALL, Order.desc(BloodPressure.DATE)));

		// active bloodPressure entries
		for(BloodPressure bloodPressure : activeBloodPressures)
		{
			if(interval.contains(bloodPressure.getDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, bloodPressure.getDate());
				calendarEntry.setTracker(Tracker.BLOOD);
				calendarEntry.setName("Blood pressure entry");
				calendarEntries.add(calendarEntry);
			}

		}

		// next recommended date
		BloodPressure bloodPressure = getFirst(activeBloodPressures);
		DateTime nextDate = preferences.getTrackers().getBloodTrackerPreferences().getRecommendedTestDate(bloodPressure);

		if(bloodPressure != null && interval.contains(nextDate))
		{
			CalendarEntry calendarEntry = new CalendarEntry(user, nextDate);
			calendarEntry.setTracker(Tracker.BLOOD);
			calendarEntry.setName("Blood pressure reminder");
			calendarEntry.setDescription(String.format("Your next blood pressure test is recommended on %s",
					BloodPressure.DATE_FORMATTER.print(nextDate)));

			calendarEntries.add(calendarEntry);
		}

		return calendarEntries;
	}

	// getters and setters
	public BloodPressureService getBloodPressureService() {
		return bloodPressureService;
	}

	public void setBloodPressureService(BloodPressureService bloodPressureService) {
		this.bloodPressureService = bloodPressureService;
	}

}
