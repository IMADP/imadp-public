package com.tracktacular.service.tracker.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * TaskTrackerFacadeImpl
 *
 * The standard implementation of the TaskTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TaskTrackerFacadeImpl extends AbstractTrackerFacade
	implements TaskTrackerFacade {

	private TaskService taskService;
	private TaskCategoryService taskCategoryService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(taskService);
		Validate.notNull(taskCategoryService);
	}

	@Override
	public Task getTask(User user, String uid) {
		return taskService.findFirstByUser(user, uid);
	}

	@Override
	public void saveTask(Task task) {
		validateTask(task);
		taskCategoryService.saveTask(task);
	}

	@Override
	public void saveTasks(List<Task> tasks) {
		for(Task task : tasks)
			validateTask(task);

		taskCategoryService.saveTasks(tasks);
	}

	/**
	 * Validates a Task.
	 *
	 * @param task
	 */
	private void validateTask(Task task) {
		new TaskValidator("task", task).validate();
	}

	@Override
	public void deleteTask(Task task) {
		taskCategoryService.deleteTask(task);
	}

	@Override
	public List<Task> findDeletedTasks(User user, CriteriaParams<Task> criteriaParams) {
		return taskService.findByUser(user, PersistableState.DELETED, null, criteriaParams);
	}

	@Override
	public long findDeletedTaskCount(User user) {
		return taskService.findCountByUser(user, PersistableState.DELETED, null);
	}

	@Override
	public List<Task> findCompletedTasks(User user, CriteriaParams<Task> criteriaParams) {
		return taskService.findByUser(user, PersistableState.ARCHIVED, null, criteriaParams);
	}

	@Override
	public long findCompletedTaskCount(User user) {
		return taskService.findCountByUser(user, PersistableState.ARCHIVED, null);
	}

	@Override
	public TaskCategory getTaskCategory(User user, String uid) {
		return taskCategoryService.findFirstByUser(user, uid);
	}

	@Override
	public TaskCategories getTaskCategories(User user) {
		Order<TaskCategory> sortOrder = Order.asc(TaskCategory.SORT);
		Order<TaskCategory> nameOrder = Order.asc(TaskCategory.NAME);
		CriteriaParams<TaskCategory> params = CriteriaParams.<TaskCategory>of(Results.ALL, sortOrder, nameOrder);
		List<TaskCategory> rootCategories = taskCategoryService.findByParentByUser(user, null, params);
		return new TaskCategories(rootCategories);
	}

	@Override
	public void saveTaskCategory(TaskCategory category) {
		validateTaskCategory(category);
		taskCategoryService.save(category);
	}

	@Override
	public void saveTaskCategories(List<TaskCategory> categories) {
		for(TaskCategory category : categories)
			validateTaskCategory(category);

		taskCategoryService.save(categories);
	}

	/**
	 * Validates a TaskCategory.
	 *
	 * @param taskCategory
	 */
	private void validateTaskCategory(TaskCategory taskCategory) {
		new TaskCategoryValidator("taskCategory", taskCategory, taskCategoryService).validate();
	}

	@Override
	public void deleteTaskCategory(TaskCategory category) {
		taskCategoryService.delete(category);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<TaskCategory> categories = getTaskCategories(user).getRootCategories();

		for(TaskCategory category : categories)
			deleteTaskCategory(category);

		for(Task task : findCompletedTasks(user, CriteriaParams.<Task>of(Results.ALL)))
			deleteTask(task);

		for(Task task : findDeletedTasks(user, CriteriaParams.<Task>of(Results.ALL)))
			deleteTask(task);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new TaskTrackerDemo(this);
	}

	@Override
	public TaskTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new TaskTrackerReport(preferences.getTrackers().getTaskTrackerPreferences(), getTaskCategories(user));
	}

	@Override
	public CompletedTaskStatistics getCompletedTaskStatistics(User user) {
		return taskService.getTaskReportCompleted(user);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();
		TaskCategories taskCategories = getTaskCategories(user);

		// active tasks
		for(TaskCategory taskCategory : taskCategories.getAllCategories())
		{
			for(Task task : taskCategory.getTasks())
			{
				// target tasks
				if(interval.contains(task.getTargetDate()))
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, task.getTargetDate());
					calendarEntry.setTracker(Tracker.TASK);
					calendarEntry.setName("Task due");
					calendarEntry.setDescription(String.format("%s", task.getName()));
					calendarEntries.add(calendarEntry);
				}
			}

		}

		return calendarEntries;
	}

	// getters and setters
	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public TaskCategoryService getTaskCategoryService() {
		return taskCategoryService;
	}

	public void setTaskCategoryService(TaskCategoryService taskCategoryService) {
		this.taskCategoryService = taskCategoryService;
	}

}