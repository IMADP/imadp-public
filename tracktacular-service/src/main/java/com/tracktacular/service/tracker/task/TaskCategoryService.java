package com.tracktacular.service.tracker.task;

import java.util.List;

import com.imadp.service.category.CategoryService;

/**
 * ITaskCategoryService
 *
 * Provides common retrieval operations for TaskCategory objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TaskCategoryService extends CategoryService<TaskCategory> {

	/**
	 * Returns true if the category name is not in use according to the user and parent category. Category names
	 * can be in use across different users and across different parent categories, but must be unique within both.
	 *
	 * @param category
	 * @return boolean
	 */
	public boolean isCategoryNameInUse(TaskCategory category);

	/**
	 * Saves a task.
	 *
	 * @param task
	 */
	public void saveTask(Task task);

	/**
	 * Saves a List of tasks.
	 *
	 * @param tasks
	 */
	public void saveTasks(List<Task> tasks);

	/**
	 * Deletes a task.
	 *
	 * @param task
	 */
	public void deleteTask(Task task);

}