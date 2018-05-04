package com.tracktacular.service.tracker.task;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;
import com.tracktacular.service.tracker.task.Task.TaskPriority;


/**
 * TracktacularTaskTrackerFacadeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularTaskTrackerFacadeImplTest extends TracktacularServiceTestCase {
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		TaskCategory taskCategory = new TaskCategory();
		taskCategory.setUser(user);

		Task task = new Task();
		task.setName("name");
		task.setNotes("notes");
		task.setPriority(TaskPriority.HIGH);
		task.setCategory(taskCategory);
		task.setTargetDate(new DateTime());
		task.setUser(user);

		userService.save(user);
		task_taskCategoryService.save(taskCategory);
		task_taskService.save(task);
	}

	@Test
	public void deleteAll() {
		assertEquals(1, taskTrackerFacade.getTaskCategories(user).getRootCategories().size());

		taskTrackerFacade.deleteAll(user);

		assertEquals(0, taskTrackerFacade.getTaskCategories(user).getRootCategories().size());
	}

}
