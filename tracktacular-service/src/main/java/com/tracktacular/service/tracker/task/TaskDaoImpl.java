package com.tracktacular.service.tracker.task;

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
 * TaskDaoImpl
 *
 * Hibernate implementation of the TaskDaoImpl.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskDaoImpl extends PersistableDaoImpl<Task> implements TaskDao {

	// named queries
	private static final String GET_COMPLETED_TASK_SUMS = "getCompletedTaskSums";

	// parameters
	private static final String USER = "user";
	private static final String EARLIEST_COMPLETED_DATE = "earliestCompletedDate";

	@Override
	public CompletedTaskStatistics getTaskReportCompleted(User user) {
		logger.debug("Finding TaskReportCompleted by user [{}]", user.getId());

		long completedTasksCount = findCountBy(findCriteriaBuilder(Results.ONE)
				.whereEqualTo(Task.USER, user)
				.whereEqualTo(Task.PERSISTABLE_STATE, PersistableState.ARCHIVED).build());

		Query query = getNamedQuery(GET_COMPLETED_TASK_SUMS);
		query.setParameter(USER, user);
		query.setParameter(EARLIEST_COMPLETED_DATE, new DateTime().minusYears(1));

		List<Object[]> objects = findByQuery(query, Results.ALL);
		List<Long> completedTasksMonthList = new ArrayList<>(objects.size());
		List<Integer> completedLowPriorityTasksByMonthList = new ArrayList<>(objects.size());
		List<Integer> completedMediumPriorityTasksByMonthList = new ArrayList<>(objects.size());
		List<Integer> completedHighPriorityTasksByMonthList = new ArrayList<>(objects.size());

		// return value contains month, year, lowCount, mediumCount, highCount
		for(Object[] object : objects)
		{
			int month = Integer.valueOf(String.valueOf(object[0]));
			int year = Integer.valueOf(String.valueOf(object[1]));
			int lowCount = Integer.valueOf(String.valueOf(object[2]));
			int mediumCount = Integer.valueOf(String.valueOf(object[3]));
			int highCount = Integer.valueOf(String.valueOf(object[4]));
			long time = new DateMidnight().withMonthOfYear(month).withYear(year).getMillis();

			completedTasksMonthList.add(time);
			completedLowPriorityTasksByMonthList.add(lowCount);
			completedMediumPriorityTasksByMonthList.add(mediumCount);
			completedHighPriorityTasksByMonthList.add(highCount);
		}

		return new CompletedTaskStatistics(
				completedTasksCount,
				completedTasksMonthList,
				completedLowPriorityTasksByMonthList,
				completedMediumPriorityTasksByMonthList,
				completedHighPriorityTasksByMonthList);
	}

}