package com.tracktacular.service.tracker.calendar;

import java.util.List;

import org.hibernate.Query;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.hibernate.PersistableDaoImpl;
import com.imadp.service.user.User;

/**
 * CalendarEntryDaoImpl
 *
 * Hibernate implementation of the CalendarEntryDao.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CalendarEntryDaoImpl extends PersistableDaoImpl<CalendarEntry> implements CalendarEntryDao {

	// named queries
	private static final String FIND_WITHIN_INTERVAL = "findWithinInterval";

	// parameters
	private static final String USER = "user";
	private static final String PERSISTABLE_STATE = "persistableState";
	private static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";

	@Override
	public List<CalendarEntry> findWithinInterval(User user, PersistableState persistableState, Interval interval) {
		logger.debug("Finding CalendarEntries within interval by user [{}], persistableState [{}], interval [{}]",
				new Object[] {user.getId(), persistableState, interval});

		Query query = getNamedQuery(FIND_WITHIN_INTERVAL);
		query.setParameter(USER, user);
		query.setParameter(PERSISTABLE_STATE, persistableState);
		query.setParameter(START_DATE, interval.getStart());
		query.setParameter(END_DATE, interval.getEnd());
		return findByQuery(query, Results.ALL);
	}

}