package com.tracktacular.service.tracker.task;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.category.CategoryServiceImpl;


/**
 * TaskCategoryServiceImpl
 *
 * The standard implementation of the TaskCategoryService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TaskCategoryServiceImpl extends CategoryServiceImpl<TaskCategory>
	implements TaskCategoryService {

	private TaskService taskService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(taskService);
	}

	@Override
	protected void onBeforeDelete(TaskCategory category) {
		super.onBeforeDelete(category);

		taskService.delete(category.getTasks());

		if(category.hasChildCategories())
			for(TaskCategory taskCategory : category.getChildCategories())
				delete(taskCategory);
	}

	@Override
	public boolean isCategoryNameInUse(TaskCategory category) {
		if(!category.hasUser())
			throw new IllegalArgumentException("TaskCategory must have a user to determine if category name is in use");

		// find all categories with the same name, parent, and user
		List<TaskCategory> categories = findByUser(category.getUser(), category.getName(), category.getParentCategory(),
				CriteriaParams.<TaskCategory>of(Results.ONE));

		// if a category was found that does not match the one we are validating, we have a duplicate
		return !categories.isEmpty() && !category.equals(categories.get(0));
	}

	@Override
	public void saveTask(Task task) {
		taskService.save(task);

		clearUserCaches(task.getUser());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveTasks(List<Task> tasks) {
		for(Task task : tasks)
			saveTask(task);
	}

	@Override
	public void deleteTask(Task task) {
		taskService.delete(task);

		clearUserCaches(task.getUser());
	}

	// getters and setters
	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}