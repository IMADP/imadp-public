package com.tracktacular.service.tracker.task;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;
import com.tracktacular.service.tracker.task.Task.TaskPriority;


/**
 * TaskServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskServiceImplTest extends TracktacularServiceTestCase {
	Task task;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		TaskCategory taskCategory = new TaskCategory();
		taskCategory.setUser(user);

		task = new Task();
		task.setName("name");
		task.setNotes("notes");
		task.setPriority(TaskPriority.HIGH);
		task.setCategory(taskCategory);
		task.setTargetDate(new DateTime());
		task.setUser(user);

		userService.save(user);
		task_taskCategoryService.save(taskCategory);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(task, task_taskService);
	}

}