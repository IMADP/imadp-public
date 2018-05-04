package com.tracktacular.service.tracker.calendar;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.account.TracktacularAccountFacade;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.TrackerFacade;
import com.tracktacular.service.tracker.TrackerReport;
import com.tracktacular.service.tracker.TracktacularTrackersFacade;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;


/**
 * CalendarTrackerFacadeImpl
 *
 * The standard implementation of the CalendarTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CalendarTrackerFacadeImpl extends AbstractTrackerFacade implements CalendarTrackerFacade {
	private CalendarEntryService calendarEntryService;
	private TracktacularAccountFacade accountFacade;
	private TracktacularTrackersFacade trackersFacade;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(calendarEntryService);
		Validate.notNull(accountFacade);
		Validate.notNull(trackersFacade);
	}

	@Override
	public CalendarMonth getCalendarMonth(User user, DateTime date, boolean publicOnly) {
		DateTime startDate = CalendarMonth.getFirstDayOfCalendarMonth(date);
		DateTime endDate = CalendarMonth.getLastDayOfCalendarMonth(date);
		Interval interval = new Interval(startDate, endDate);
		List<CalendarEntry> calendarEntries = new ArrayList<>();
		Preferences preferences = accountFacade.getPreferences(user);

		// add holidays
		for(Holiday holiday : HolidayManager.getInstance(HolidayCalendar.UNITED_STATES).getHolidays(interval))
		{
			CalendarEntry calendarEntry = new CalendarEntry();
			calendarEntry.setStartDate(holiday.getDate().toDateTimeAtStartOfDay());
			calendarEntry.setName(holiday.getDescription());
			calendarEntries.add(calendarEntry);
		}

		// for all trackers, get calendarEntries for the given date range
		for(Tracker tracker : Tracker.values())
		{
			TrackerFacade trackerFacade = trackersFacade.getTrackerFacade(tracker);

			if(publicOnly && !preferences.getTrackers().getTrackerPreferences(tracker).isTrackerPublic())
				continue;

			List<CalendarEntry> trackerCalendarEntries = trackerFacade.getTrackerCalendarEntries(
					user, interval, preferences);

			if(!CollectionUtils.isEmpty(trackerCalendarEntries))
				calendarEntries.addAll(trackerCalendarEntries);
		}

		return new CalendarMonth(date, calendarEntries);
	}

	@Override
	public CalendarEntry getCalendarEntry(User user, String uid) {
		return calendarEntryService.findFirstByUser(user, uid);
	}

	@Override
	public List<CalendarEntry> findActiveCalendarEntries(User user, CriteriaParams<CalendarEntry> criteriaParams) {
		return calendarEntryService.findByUser(user, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findActiveCalendarEntryCount(User user) {
		return calendarEntryService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<CalendarEntry> findDeletedCalendarEntries(User user, CriteriaParams<CalendarEntry> criteriaParams) {
		return calendarEntryService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedCalendarEntryCount(User user) {
		return calendarEntryService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public void saveCalendarEntry(CalendarEntry calendarEntry) {
		new CalendarEntryValidator("calendarEntry", calendarEntry).validate();
		calendarEntryService.save(calendarEntry);
	}

	@Override
	public void deleteCalendarEntry(CalendarEntry calendarEntry) {
		calendarEntryService.delete(calendarEntry);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<CalendarEntry> calendarEntries = calendarEntryService.findByUser(
				user, CriteriaParams.<CalendarEntry>of(Results.ALL));

		for(CalendarEntry calendarEntry : calendarEntries)
			deleteCalendarEntry(calendarEntry);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new CalendarTrackerDemo(this);
	}

	@Override
	public TrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new CalendarTrackerReport(getCalendarMonth(user, new DateTime(), publicOnly));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		return calendarEntryService.findWithinInterval(user, PersistableState.ACTIVE, interval);
	}

	// getters and setters
	public CalendarEntryService getCalendarEntryService() {
		return calendarEntryService;
	}

	public void setCalendarEntryService(CalendarEntryService calendarEntryService) {
		this.calendarEntryService = calendarEntryService;
	}

	public TracktacularTrackersFacade getTrackersFacade() {
		return trackersFacade;
	}

	public void setTrackersFacade(TracktacularTrackersFacade trackersFacade) {
		this.trackersFacade = trackersFacade;
	}

	public TracktacularAccountFacade getAccountFacade() {
		return accountFacade;
	}

	public void setAccountFacade(TracktacularAccountFacade accountFacade) {
		this.accountFacade = accountFacade;
	}

}