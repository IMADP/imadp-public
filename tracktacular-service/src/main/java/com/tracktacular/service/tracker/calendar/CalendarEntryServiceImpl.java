package com.tracktacular.service.tracker.calendar;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.core.cache.TripleCacheKey;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * CalendarEntryServiceImpl
 *
 * The standard implementation of the CalendarEntryService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CalendarEntryServiceImpl extends PersistableUserServiceImpl<CalendarEntry>
	implements CalendarEntryService {

	private CalendarEntryDao calendarEntryDao;
	private Cache<User, Cache<TripleCacheKey<User, PersistableState, Interval>, List<CalendarEntry>>> findWithinIntervalCache;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(calendarEntryDao);
		Validate.notNull(findWithinIntervalCache);
	}

	@Override
	protected com.imadp.dao.PersistableDao<CalendarEntry> getPersistableDao() {
		return calendarEntryDao;
	}

	@Override
	protected void onAfterSave(CalendarEntry calendarEntry, boolean initialSave) {
		super.onAfterSave(calendarEntry, initialSave);

		findWithinIntervalCache.remove(calendarEntry.getUser());
	}

	@Override
	protected void onAfterDelete(CalendarEntry calendarEntry) {
		super.onAfterDelete(calendarEntry);

		findWithinIntervalCache.remove(calendarEntry.getUser());
	}

	@Override
	public List<CalendarEntry> findWithinInterval(User user, PersistableState persistableState, Interval interval) {
		TripleCacheKey<User, PersistableState, Interval> key = TripleCacheKey.of(user, persistableState, interval);

		return customFind(getSubCache(findWithinIntervalCache, user),
				key, createValueFindWithinInterval);
	}

	private CreateValue<TripleCacheKey<User, PersistableState, Interval>, List<CalendarEntry>> createValueFindWithinInterval =
		new CreateValue<TripleCacheKey<User, PersistableState, Interval>, List<CalendarEntry>>() {
		@Override
		public List<CalendarEntry> create(TripleCacheKey<User, PersistableState, Interval> key) {
			User user = key.getKeyOne();
			PersistableState persistableState = key.getKeyTwo();
			Interval interval = key.getKeyThree();
			return calendarEntryDao.findWithinInterval(user, persistableState, interval);
		}
	};

	// getters and setters
	public CalendarEntryDao getCalendarEntryDao() {
		return calendarEntryDao;
	}

	public void setCalendarEntryDao(CalendarEntryDao calendarEntryDao) {
		this.calendarEntryDao = calendarEntryDao;
	}

	public Cache<User, Cache<TripleCacheKey<User, PersistableState, Interval>, List<CalendarEntry>>> getFindWithinIntervalCache() {
		return findWithinIntervalCache;
	}

	public void setFindWithinIntervalCache(
			Cache<User, Cache<TripleCacheKey<User, PersistableState, Interval>, List<CalendarEntry>>> findWithinIntervalCache) {
		this.findWithinIntervalCache = findWithinIntervalCache;
	}

}