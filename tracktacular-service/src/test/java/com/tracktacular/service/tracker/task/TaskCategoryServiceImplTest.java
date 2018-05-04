package com.tracktacular.service.tracker.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;
import com.tracktacular.service.tracker.task.Task.TaskPriority;


/**
 * TaskCategoryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskCategoryServiceImplTest extends TracktacularServiceTestCase {
	TaskCategory taskCategory;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		taskCategory = new TaskCategory();
		taskCategory.setName("name");
		taskCategory.setSort(1);
		taskCategory.setUser(user);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(taskCategory, task_taskCategoryService);
	}

	@Test
	public void taskOperations() {
		task_taskCategoryService.save(taskCategory);

		Task task1 = new Task(user, taskCategory);
		task1.setName("task1");

		Task task2 = new Task(user, taskCategory);
		task2.setName("task2");

		Task task3 = new Task(user, taskCategory);
		task3.setName("task3");

		taskCategory = task_taskCategoryService.get(taskCategory.getId());

		assertNotNull(taskCategory);
		assertEquals(0, taskCategory.getTasks().size());

		task_taskCategoryService.saveTask(task1);
		task_taskCategoryService.saveTask(task2);
		task_taskCategoryService.saveTask(task3);

		taskCategory = task_taskCategoryService.get(taskCategory.getId());

		assertNotNull(taskCategory);
		assertEquals(3, taskCategory.getTasks().size());
	}

	@Test
	public void multipleTaskAssociations() throws Exception {
		TaskCategory one = new TaskCategory(user);
		one.setName("Category 1");
		one.setSort(3);

		TaskCategory two = new TaskCategory(user);
		one.setName("Category 2");
		one.setSort(2);

		TaskCategory three = new TaskCategory(user);
		one.setName("Category 3");
		one.setSort(1);

		task_taskCategoryService.save(one);
		task_taskCategoryService.save(two);
		task_taskCategoryService.save(three);

		List<TaskCategory> categories = new ArrayList<>(3);
		categories.add(one);
		categories.add(two);
		categories.add(three);

		for(int i=0; i<15; i++)
			task_taskService.save(createTask(user, categories));

		assertEquals(3, task_taskCategoryService.findCount());

		categories = task_taskCategoryService.findByUser(user, CriteriaParams.<TaskCategory>of(
				Results.ALL, Order.asc(TaskCategory.SORT)));

		assertEquals(3, categories.size());
	}

	/**
	 * Returns a mock object.
	 *
	 * @param user
	 * @return Task
	 */
	private Task createTask(User user, List<TaskCategory> categories) {
		Task task = new Task();
		task.setUser(user);
		task.setName("name");
		task.setNotes("notes");
		task.setPriority(TaskPriority.values()[new Random().nextInt(TaskPriority.values().length)]);
		task.setCategory(categories.get(new Random().nextInt(categories.size())));

		if(new Random().nextBoolean())
			task.setTargetDate(new DateTime());

		return task;
	}


	@Test
	public void categoryHierarchy() {
		task_taskCategoryService.save(taskCategory);
		TaskCategory child;

		child = addChild(taskCategory, "child1");
		addChild(child, "subChild1");
		addChild(child, "subChild2");
		addChild(child, "subChild3");

		child = addChild(taskCategory, "child2");
		addChild(child, "subChild1");
		addChild(child, "subChild2");
		addChild(child, "subChild3");

		child = addChild(taskCategory, "child3");
		addChild(child, "subChild1");
		addChild(child, "subChild2");
		addChild(child, "subChild3");

		taskCategory = task_taskCategoryService.get(taskCategory.getId());

		assertEquals(13, taskCategory.getTotalCategoryCount());
	}

	/**
	 * Adds and saves a child taskCategory.
	 *
	 * @param parent
	 * @param name
	 * @return TaskCategory
	 */
	private TaskCategory addChild(TaskCategory parent, String name) {
		TaskCategory taskCategory = new TaskCategory(user);
		taskCategory.setParentCategory(parent);
		task_taskCategoryService.save(taskCategory);
		return taskCategory;
	}

}