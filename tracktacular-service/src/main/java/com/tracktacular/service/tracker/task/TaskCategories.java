package com.tracktacular.service.tracker.task;

import java.util.ArrayList;
import java.util.List;

import com.imadp.core.AbstractSerializable;


/**
 * TaskCategories
 *
 * A collection of task categories.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskCategories extends AbstractSerializable {
	private List<TaskCategory> rootCategories;
	private List<TaskCategory> allCategories;
	private TaskCategoryPriorities allPriorities;

	// constructor
	public TaskCategories(List<TaskCategory> rootCategories) {
		this.rootCategories = rootCategories;
	}

	/**
	 * Returns the total task count.
	 *
	 * @return int
	 */
	public int getTaskCount() {
		int taskCount = 0;

		for(TaskCategory taskCategory : rootCategories)
			taskCount += taskCategory.getTaskCount();

		return taskCount;
	}

	/**
	 * Returns true if the root categories are sortable as determined by more than one value, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isRootCategoriesSortable() {
		return getRootCategories().size() > 1;
	}

	/**
	 * Retrieves a flattened list of all categories for a user.
	 *
	 * @return List<TaskCategory>
	 */
	public List<TaskCategory> getAllCategories() {
		if(allCategories == null)
		{
			allCategories = new ArrayList<>();

			for(TaskCategory taskCategory : getRootCategories())
				addCategory(allCategories, taskCategory);
		}

		return allCategories;
	}

	/**
	 * Recursively adds a category and child categories to the allCategories list.
	 *
	 * @param allCategories
	 * @param category
	 */
	private void addCategory(List<TaskCategory> allCategories, TaskCategory category) {
		allCategories.add(category);

		if(category.hasChildCategories())
			for(TaskCategory taskCategory : category.getChildCategories())
				addCategory(allCategories, taskCategory);
	}

	/**
	 * Retrieves the TaskCategoryPriorities for all categories for a user.
	 *
	 * @return TaskCategoryPriorities
	 */
	public TaskCategoryPriorities getAllPriorities() {
		if(allPriorities == null)
		{
			allPriorities = new TaskCategoryPriorities();

			for(TaskCategory taskCategory : getRootCategories())
				allPriorities.add(taskCategory.getPriorities());
		}

		return allPriorities;
	}

	// getters
	public List<TaskCategory> getRootCategories() {
		return rootCategories;
	}

}