package com.tracktacular.service.tracker.birthday;

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
import com.tracktacular.service.tracker.birthday.Birthdays.Month;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * BirthdayTrackerFacadeImpl
 *
 * The standard implementation of the BirthdayTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BirthdayTrackerFacadeImpl extends AbstractTrackerFacade
	implements BirthdayTrackerFacade {

	private BirthdayService birthdayService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(birthdayService);
	}

	@Override
	public Birthday getBirthday(User user, String uid) {
		return birthdayService.findFirstByUser(user, uid);
	}

	@Override
	public void saveBirthday(Birthday birthday) {
		new BirthdayValidator("birthday", birthday).validate();
		birthdayService.save(birthday);
	}

	@Override
	public void deleteBirthday(Birthday birthday) {
		birthdayService.delete(birthday);
	}

	@Override
	public Birthdays findActiveBirthdays(User user) {
		List<Birthday> activeBirthdays = birthdayService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Birthday>of(Results.ALL, Order.asc(Birthday.DATE)));
		return new Birthdays(activeBirthdays);
	}

	@Override
	public List<Birthday> findDeletedBirthdays(User user, CriteriaParams<Birthday> criteriaParams) {
		return birthdayService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedBirthdayCount(User user) {
		return birthdayService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Birthday> birthdays = birthdayService.findByUser(user, CriteriaParams.<Birthday>of(Results.ALL));

		for(Birthday birthday : birthdays)
			deleteBirthday(birthday);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new BirthdayTrackerDemo(this);
	}

	@Override
	public BirthdayTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new BirthdayTrackerReport(preferences.getTrackers().getBirthdayTrackerPreferences(), findActiveBirthdays(user));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active birthdays
		Birthdays birthdays = findActiveBirthdays(user);

		for(Month month : birthdays.getBirthdayMonths())
		{
			for(Birthday birthday : month.getBirthdays())
			{
				DateTime birthdateWithinInterval = birthday.getBirthdateWithinInterval(interval);

				if(birthdateWithinInterval != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, birthdateWithinInterval);
					calendarEntry.setTracker(Tracker.BIRTHDAY);
					calendarEntry.setName(String.format("%s's %s Birthday", birthday.getFullName(), birthday.getAgeOrdinal()));
					calendarEntries.add(calendarEntry);
				}
			}

		}

		return calendarEntries;
	}

	// getters and setters
	public BirthdayService getBirthdayService() {
		return birthdayService;
	}

	public void setBirthdayService(BirthdayService birthdayService) {
		this.birthdayService = birthdayService;
	}

}