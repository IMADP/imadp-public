package com.tracktacular.web.page.tracker.task;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.task.Task;
import com.tracktacular.service.tracker.task.TaskCategories;
import com.tracktacular.service.tracker.task.TaskCategory;
import com.tracktacular.service.tracker.task.TaskTrackerFacade;
import com.tracktacular.service.tracker.task.TaskTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * TaskTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class TaskTrackerActionBean extends TrackerActionBean<TaskTrackerFacade, TaskTrackerPreferences> {
	private Task task;
	private TaskCategory taskCategory;
	private TaskCategories taskCategories;

	/**
	 * Retrieves the task categories object for a user.
	 *
	 * @return TaskCategories
	 */
	public TaskCategories getTaskCategories() {
		if(taskCategories == null)
			taskCategories = getTrackerFacade().getTaskCategories(getTrackerUser());

		return taskCategories;
	}

	@Override
	public Tracker getTracker() {
		return Tracker.TASK;
	}

	/**
	 * Save or updates a Task.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveTask() {
		Task task = getTask();
		populatePersistableUser(task);
		getTrackerFacade().saveTask(task);

		if(task.isActiveState())
			getContext().addSuccessMessage("task.saveTask.success");

		else if(task.isArchivedState())
			getContext().addSuccessMessage("task.completeTask.success", task.getName());

		else if(task.isDeletedState())
			getContext().addSuccessMessage("task.deleteTask.success", task.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Task.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteTask() {
		Task task = getTask();
		getTrackerFacade().deleteTask(task);
		getContext().addSuccessMessage("task.deleteTask.success", task.getName());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setTask(Task task) {
		this.task = task;
	}

	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

	public Task getTask() {
		return task;
	}

	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

}