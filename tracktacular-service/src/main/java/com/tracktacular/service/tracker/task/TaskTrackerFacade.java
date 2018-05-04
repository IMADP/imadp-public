package com.tracktacular.service.tracker.task;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * TaskTrackerFacade
 *
 * Provides functionality for task tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TaskTrackerFacade extends TrackerFacade {

	/**
	 * Gets a Task by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return <T> task or <code>null</code> if no task was found.
	 */
	public Task getTask(User user, String uid);

	/**
	 * Saves or updates a Task.
	 *
	 * @param task
	 */
	public void saveTask(Task task);

	/**
	 * Saves or updates a List of Tasks.
	 *
	 * @param tasks
	 */
	public void saveTasks(List<Task> tasks);

	/**
	 * Removes a Task, without completing it.
	 *
	 * @param task
	 */
	public void deleteTask(Task task);

	/**
	 * Finds a List of completed Tasks for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Task>
	 */
	public List<Task> findCompletedTasks(User user, CriteriaParams<Task> criteriaParams);

	/**
	 * Finds the count of all completed Tasks for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findCompletedTaskCount(User user);

	/**
	 * Finds a List of deleted Tasks for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Task>
	 */
	public List<Task> findDeletedTasks(User user, CriteriaParams<Task> criteriaParams);

	/**
	 * Finds the count of all deleted Tasks for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedTaskCount(User user);

	/**
	 * Gets a TaskCategory by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return <T> category or <code>null</code> if no category was found.
	 */
	public TaskCategory getTaskCategory(User user, String uid);

	/**
	 * Gets a TaskCategories object for a user.
	 *
	 * @param user
	 * @return TaskCategories
	 */
	public TaskCategories getTaskCategories(User user);

	/**
	 * Saves a Category.
	 *
	 * @param category
	 */
	public void saveTaskCategory(TaskCategory category);

	/**
	 * Saves or updates a List of Categories.
	 *
	 * @param categories
	 */
	public void saveTaskCategories(List<TaskCategory> categories);

	/**
	 * Removes a Category.
	 *
	 * @param category
	 */
	public void deleteTaskCategory(TaskCategory category);

    /**
     * Gets the CompletedTaskStatistics for the given user.
     *
     * @param user
     * @return CompletedTaskStatistics
     */
    public CompletedTaskStatistics getCompletedTaskStatistics(User user);

}