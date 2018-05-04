package com.tracktacular.service.tracker;

import java.util.List;

import org.joda.time.Interval;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.service.FacadeComponent;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.calendar.CalendarEntry;

/**
 * AbstractTrackerFacade
 *
 * Base class for tracker facades.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public abstract class AbstractTrackerFacade extends FacadeComponent implements TrackerFacade {

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void deleteAll(User user) {
		onDeleteAll(user);
	}

	/**
	 * Called within a transaction, allowing subclasses to focus on deleting the necessary objects.
	 *
	 * @param user
	 */
	protected abstract void onDeleteAll(User user);

}