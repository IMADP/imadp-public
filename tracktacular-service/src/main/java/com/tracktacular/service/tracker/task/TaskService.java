package com.tracktacular.service.tracker.task;

import java.util.List;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;
import com.tracktacular.service.tracker.task.Task.TaskPriority;

/**
 * ITaskService
 *
 * Provides common retrieval operations for Task objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TaskService extends PersistableUserService<Task> {

	/**
	 * Finds a List of Tasks by user, status, priority, and criteriaParams.
	 * If status or priority is null, they act as wildcards.
	 *
	 * @param user
	 * @param state
	 * @param priority
	 * @param criteriaParams
	 * @return List<Task>
	 */
	public List<Task> findByUser(User user, PersistableState state, TaskPriority priority, CriteriaParams<Task> criteriaParams);

	/**
	 * Finds the count of all Tasks by user, status, and priority.
	 * If status or priority is null, they act as wildcards.
	 *
	 * @param user
	 * @param state
	 * @param priority
	 * @return long
	 */
	public long findCountByUser(User user, PersistableState state, TaskPriority priority);

	/**
	 * Gets the TaskReportCompleted segment for the given user.
	 *
	 * @param user
	 * @return TaskReportCompleted
	 */
	public CompletedTaskStatistics getTaskReportCompleted(User user);

}