package com.tracktacular.service.tracker.dream;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.hibernate.PersistableDaoImpl;
import com.imadp.service.user.User;

/**
 * DreamDaoImpl
 *
 * Hibernate implementation of the CredentialsDao.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DreamDaoImpl extends PersistableDaoImpl<Dream> implements DreamDao {

	// named queries
	private static final String GET_DREAM_SUMS = "getDreamSums";

	// parameters
	private static final String USER = "user";
	private static final String EARLIEST_DATE = "earliestDate";

	@Override
	public DreamTrackerReport getDreamTrackerReport(User user) {
		logger.debug("Finding getDreamTrackerReport by user [{}]", user.getId());

		long dreamsCount = findCountBy(findCriteriaBuilder(Results.ONE)
				.whereEqualTo(Dream.USER, user)
				.whereEqualTo(Dream.PERSISTABLE_STATE, PersistableState.ACTIVE).build());

		Query query = getNamedQuery(GET_DREAM_SUMS);
		query.setParameter(USER, user);
		query.setParameter(EARLIEST_DATE, new DateTime().minusYears(1));

		List<Object[]> objects = findByQuery(query, Results.ALL);

		List<Long> dreamsMonthList = new ArrayList<>(objects.size());
		List<Integer> allDreamsByMonthList = new ArrayList<>(objects.size());
		List<Integer> favoriteDreamsByMonthList = new ArrayList<>(objects.size());
		List<Integer> lucidDreamsByMonthList = new ArrayList<>(objects.size());

		// return value contains month, year, allDreamsCount, favoriteDreamsCount, lucidDreamsCount
		for(Object[] object : objects)
		{
			int month = Integer.valueOf(String.valueOf(object[0]));
			int year = Integer.valueOf(String.valueOf(object[1]));
			int allDreamsCount = Integer.valueOf(String.valueOf(object[2]));
			int favoriteDreamsCount = Integer.valueOf(String.valueOf(object[3]));
			int lucidDreamsCount = Integer.valueOf(String.valueOf(object[4]));
			long time = new DateMidnight().withMonthOfYear(month).withYear(year).getMillis();

			dreamsMonthList.add(time);
			allDreamsByMonthList.add(allDreamsCount);
			favoriteDreamsByMonthList.add(favoriteDreamsCount);
			lucidDreamsByMonthList.add(lucidDreamsCount);
		}

		return new DreamTrackerReport(
				dreamsCount,
				dreamsMonthList,
				allDreamsByMonthList,
				favoriteDreamsByMonthList,
				lucidDreamsByMonthList);
	}

}