package com.tracktacular.service.tracker.task;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * TaskTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class TaskTrackerReport extends AbstractTrackerReport {
	private final long rootCategoriesCount;
	private final List<TaskCategory> rootCategoriesList;
	private final List<Task> highPriorityTasks;
	private final List<Task> lateTasks;
	private final TaskCategoryPriorities allPriorities;
	private final long taskCount;

	// constructor
	public TaskTrackerReport(TaskTrackerPreferences preferences, TaskCategories taskCategories) {
		this.highPriorityTasks = Lists.newArrayList();
		this.lateTasks = Lists.newArrayList();

		for(TaskCategory taskCategory : taskCategories.getAllCategories())
		{
			// add high priority tasks
			highPriorityTasks.addAll(taskCategory.getHighPriorityTasks());

			// add late tasks
			if(preferences.isAlertOnTargetDates())
				for(Task task : taskCategory.getTasks())
	    			if(isDue(task.getTargetDate()))
	    				lateTasks.add(task);
		}

		this.taskCount = taskCategories.getTaskCount();
		this.rootCategoriesList = taskCategories.getRootCategories();
		this.rootCategoriesCount = rootCategoriesList.size();
		this.allPriorities = taskCategories.getAllPriorities();
	}

	@Override
	public boolean isEnabled() {
		return taskCount > 0;
	}

	@Override
    public int getAlertCount() {
    	return lateTasks.size();
    }

	public long getHighPriorityTasksCount() {
		return highPriorityTasks.size();
	}

	public long getLateTasksCount() {
		return lateTasks.size();
	}

	// getters
	public long getRootCategoriesCount() {
		return rootCategoriesCount;
	}

	public List<TaskCategory> getRootCategoriesList() {
		return rootCategoriesList;
	}

	public TaskCategoryPriorities getAllPriorities() {
		return allPriorities;
	}

	public long getTaskCount() {
		return taskCount;
	}

	public List<Task> getLateTasks() {
		return lateTasks;
	}

	public List<Task> getHighPriorityTasks() {
		return highPriorityTasks;
	}

}