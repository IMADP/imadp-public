package com.tracktacular.web.page.tracker.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.codehaus.plexus.util.StringUtils;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.task.Task;
import com.tracktacular.service.tracker.task.Task.TaskPriority;
import com.tracktacular.service.tracker.task.TaskCategory;
import com.tracktacular.web.IgnoreInPublicMode;


/**
 * TasksActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-task/tasks/category-{selectedTaskCategory=all}")
public class TasksActionBean extends TaskTrackerActionBean {

	// static properties
	private static final int MAX_MULTIPLE_TASKS = 50;

	// properties
	private String multipleTasks;
	private String sortedCategories;
	private String sortedHighPriorityTasks;
	private String sortedMediumPriorityTasks;
	private String sortedLowPriorityTasks;
	private String selectedTaskCategory;

	/**
	 * Saves multiple Tasks.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveTasks() {
		String[] taskNames = multipleTasks.split("\\r?\\n");

		// safeguard against too many tasks
		if(taskNames.length > MAX_MULTIPLE_TASKS)
			taskNames = Arrays.copyOfRange(taskNames, 0, MAX_MULTIPLE_TASKS);

		List<Task> tasks = Lists.newArrayListWithExpectedSize(taskNames.length);

		if(multipleTasks != null)
			for(String taskName : taskNames)
			{
				if(StringUtils.isBlank(taskName))
					continue;

				Task task = new Task();
				task.setName(taskName);
				task.setCategory(getTaskCategory());
				populatePersistableUser(task);
				tasks.add(task);
			}

		if(!tasks.isEmpty())
			getTrackerFacade().saveTasks(tasks);

		getContext().addSuccessMessage("task.saveTasks.success");
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Category.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveTaskCategory() {
		TaskCategory taskCategory = getTaskCategory();
		populatePersistableUser(taskCategory);
		getTrackerFacade().saveTaskCategory(taskCategory);
		getContext().addSuccessMessage("task.saveTaskCategory.success", taskCategory.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Categories.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortTaskCategories() {
		List<TaskCategory> sortedCategoriesList = convertObjectList(sortedCategories, TaskCategory.class);
		List<TaskCategory> taskCategories = getResortedList(sortedCategoriesList);
		getTrackerFacade().saveTaskCategories(taskCategories);
		getContext().addSuccessMessage("task.sortTaskCategories.success");
		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the Category collapsed property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleTaskCategoryCollapse() {
		TaskCategory taskCategory = getTaskCategory();
		taskCategory.setCollapsed(!taskCategory.isCollapsed());
		getTrackerFacade().saveTaskCategory(taskCategory);
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Category.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteTaskCategory() {
		TaskCategory taskCategory = getTaskCategory();
		getTrackerFacade().deleteTaskCategory(taskCategory);
		getContext().addSuccessMessage("task.deleteTaskCategory.success", taskCategory.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Tasks.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortTasks() {
		List<Task> sortedHighPriorityTasksList = convertObjectList(sortedHighPriorityTasks, Task.class);
		List<Task> sortedMediumPriorityTasksList = convertObjectList(sortedMediumPriorityTasks, Task.class);
		List<Task> sortedLowPriorityTasksList = convertObjectList(sortedLowPriorityTasks, Task.class);

		// resort and prioritize high priority tasks
		for(int i = 0; i < sortedHighPriorityTasksList.size(); i++)
		{
			Task task = sortedHighPriorityTasksList.get(i);
			task.setSort(i);
			task.setPriority(TaskPriority.HIGH);
		}

		// resort and prioritize medium priority tasks
		for(int i = 0; i < sortedMediumPriorityTasksList.size(); i++)
		{
			Task task = sortedMediumPriorityTasksList.get(i);
			task.setSort(i);
			task.setPriority(TaskPriority.MEDIUM);
		}

		// resort and prioritize low tasks
		for(int i = 0; i < sortedLowPriorityTasksList.size(); i++)
		{
			Task task = sortedLowPriorityTasksList.get(i);
			task.setSort(i);
			task.setPriority(TaskPriority.LOW);
		}

		List<Task> tasks = new ArrayList<>();
		tasks.addAll(sortedHighPriorityTasksList);
		tasks.addAll(sortedMediumPriorityTasksList);
		tasks.addAll(sortedLowPriorityTasksList);

		getTrackerFacade().saveTasks(tasks);
		getContext().addSuccessMessage("task.sortTasks.success");
		return getAjaxSourceResolution();
	}

	// setters
	public void setSortedCategories(String sortedCategories) {
		this.sortedCategories = sortedCategories;
	}

	public String getSelectedTaskCategory() {
		return selectedTaskCategory;
	}

	public void setSelectedTaskCategory(String selectedTaskCategory) {
		this.selectedTaskCategory = selectedTaskCategory;
	}

	public void setSortedHighPriorityTasks(String sortedHighPriorityTasks) {
		this.sortedHighPriorityTasks = sortedHighPriorityTasks;
	}

	public void setSortedMediumPriorityTasks(String sortedMediumPriorityTasks) {
		this.sortedMediumPriorityTasks = sortedMediumPriorityTasks;
	}

	public void setSortedLowPriorityTasks(String sortedLowPriorityTasks) {
		this.sortedLowPriorityTasks = sortedLowPriorityTasks;
	}

	public String getSortedCategories() {
		return sortedCategories;
	}

	public String getSortedHighPriorityTasks() {
		return sortedHighPriorityTasks;
	}

	public String getSortedMediumPriorityTasks() {
		return sortedMediumPriorityTasks;
	}

	public String getSortedLowPriorityTasks() {
		return sortedLowPriorityTasks;
	}

	public String getMultipleTasks() {
		return multipleTasks;
	}

	public void setMultipleTasks(String multipleTasks) {
		this.multipleTasks = multipleTasks;
	}

}