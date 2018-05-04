package com.tracktacular.service.tracker.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.imadp.core.Property;
import com.imadp.service.category.AbstractCategory;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.task.Task.TaskPriority;


/**
 * TaskCategory
 *
 * A category grouping of tasks.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskCategory extends AbstractCategory<TaskCategory> {

	// static properties
	public static final Property<TaskCategory, Boolean> COLLAPSED = Property.of("collapsed");

	// properties
	private Set<Task> tasks;
	private TaskCategoryPriorities priorities;
	private boolean collapsed;

	// constructor
	public TaskCategory() {

	}

	// constructor
	public TaskCategory(User user) {
		super(user);
	}

	// constructor
	public TaskCategory(User user, TaskCategory taskCategory) {
		super(user, taskCategory);
	}

	/**
	 * Returns a new task for this category.
	 *
	 * @return Task
	 */
	public Task getNewTask() {
		return new Task(user, this);
	}

	/**
	 * Returns a new sub category.
	 *
	 * @return TaskCategory
	 */
	public TaskCategory getNewSubTaskCategory() {
		return new TaskCategory(user, this);
	}

	/**
	 * Returns true if tasks are present, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasTasks() {
		return tasks != null && !tasks.isEmpty();
	}

	/**
	 * Returns the tasks as a List of Tasks.
	 *
	 * @return List<Task>
	 */
	public List<Task> getTasksAsList() {
		 return new ArrayList<>(tasks);
	}

	/**
	 * Returns the count of all tasks in this category and child categories.
	 *
	 * @return int
	 */
	public int getTaskCount() {
		int taskCount = 0;

		if(hasTasks())
			taskCount += tasks.size();

		for(TaskCategory taskCategory : getChildCategories())
			taskCount += taskCategory.getTaskCount();

		return taskCount;
	}

	/**
	 * Returns a list of the high priority tasks.
	 *
	 * @return List<Task>
	 */
	public List<Task> getHighPriorityTasks() {
		List<Task> highPriorityTasks = new ArrayList<>(tasks.size());

		for(Task task : tasks)
			if(task.getPriority().equals(TaskPriority.HIGH))
				highPriorityTasks.add(task);

		return highPriorityTasks;
	}

	/**
	 * Returns a list of the medium priority tasks.
	 *
	 * @return List<Task>
	 */
	public List<Task> getMediumPriorityTasks() {
		List<Task> mediumPriorityTasks = new ArrayList<>(tasks.size());

		for(Task task : tasks)
			if(task.getPriority().equals(TaskPriority.MEDIUM))
				mediumPriorityTasks.add(task);

		return mediumPriorityTasks;
	}

	/**
	 * Returns a list of the low priority tasks.
	 *
	 * @return List<Task>
	 */
	public List<Task> getLowPriorityTasks() {
		List<Task> lowPriorityTasks = new ArrayList<>(tasks.size());

		for(Task task : tasks)
			if(task.getPriority().equals(TaskPriority.LOW))
				lowPriorityTasks.add(task);

		return lowPriorityTasks;
	}

	/**
	 * Retrieves the priority count for all tasks.
	 *
	 * @return TaskCategoryPriorities
	 */
	public TaskCategoryPriorities getPriorities() {
		if(priorities != null)
			return priorities;

		this.priorities = new TaskCategoryPriorities();
		populatePriorities(this, priorities);
		return priorities;
	}

	/**
	 * Populate the priorities for a given category.
	 *
	 * @param category
	 * @param taskCategoryPriorities
	 */
	private void populatePriorities(TaskCategory category, TaskCategoryPriorities priorities) {
		for(Task task : category.getTasks())
		{
			switch(task.getPriority())
			{
				case LOW:	  priorities.low++;		break;
				case MEDIUM:  priorities.medium++; 	break;
				case HIGH: 	  priorities.high++; 	break;
			}
		}

		for(TaskCategory childCategory : category.getChildCategories())
			populatePriorities(childCategory, priorities);
	}

	// getters and setters
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

}