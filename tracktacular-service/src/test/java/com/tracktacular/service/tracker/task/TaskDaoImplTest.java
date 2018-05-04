package com.tracktacular.service.tracker.task;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;
import com.tracktacular.service.tracker.task.Task.TaskPriority;


/**
 * TaskDaoImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Transactional
public class TaskDaoImplTest extends TracktacularServiceTestCase {
	Task task;
	User user;
	TaskCategory category;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();
		category = new TaskCategory();
		category.setUser(user);

		userService.save(user);
		task_taskCategoryService.save(category);
	}

	/**
     * Sample Data
     *
     * // active data, should be ignored
     * Task(ACTIVE,    LOW,    currentDate)
     * Task(ACTIVE,    MEDIUM, currentDate)
     * Task(ACTIVE,    HIGH,   currentDate)
     *
     * // older than one year, should be ignored
     * Task(ARCHIVED, LOW,    currentDate - 2 years)
     * Task(ARCHIVED, MEDIUM, currentDate - 2 years)
     * Task(ARCHIVED, HIGH,   currentDate - 2 years)
     *
     * // january completed tasks
     * Task(ARCHIVED, LOW,    jan)
     * Task(ARCHIVED, LOW,    jan)
     * Task(ARCHIVED, LOW,    jan)
     *
     * // march completed tasks
     * Task(ARCHIVED, LOW,    march)
     * Task(ARCHIVED, LOW,    march)
     * Task(ARCHIVED, MEDIUM, march)
     * Task(ARCHIVED, HIGH,   march)
     *
     * // april completed tasks
     * Task(ARCHIVED, HIGH,   march)
     *
     * // expected query resuts
     * priority  month  year count
     *  L           1   2012    3
     *  H           3   2012    1
     *  L           3   2012    2
     *  M           3   2012    1
     *  H           4   2012    1
     *
     * // expected outcome
     * completedTasksMonthList                   01/12, 03/12, 04/12
     * completedLowPriorityTasksByMonthList          3,     2,     0
     * completedMediumPriorityTasksByMonthList       0,     1,     0
     * completedHighPriorityTasksByMonthList         0,     1,     1
     *
     */
    @Test
    public void test_getTaskReportCompleted() {

        // active data, should be ignored
        saveTask("name", PersistableState.ACTIVE,    TaskPriority.LOW,     new DateTime().withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ACTIVE,    TaskPriority.MEDIUM,  new DateTime().withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ACTIVE,    TaskPriority.HIGH,    new DateTime().withDayOfMonth(new Random().nextInt(10) + 5));

        // older than one year, should be ignored
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.LOW,     new DateTime().minusYears(2).withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.MEDIUM,  new DateTime().minusYears(2).withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.HIGH,    new DateTime().minusYears(2).withDayOfMonth(new Random().nextInt(10) + 5));

        // january completed tasks
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.LOW,     new DateTime().withMonthOfYear(1).withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.LOW,     new DateTime().withMonthOfYear(1).withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.LOW,     new DateTime().withMonthOfYear(1).withDayOfMonth(new Random().nextInt(10) + 5));

        // march completed tasks
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.LOW,     new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.LOW,     new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.MEDIUM,  new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.HIGH,    new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));

        // april completed tasks
        saveTask("name", PersistableState.ARCHIVED, TaskPriority.HIGH,    new DateTime().withMonthOfYear(4).withDayOfMonth(new Random().nextInt(10) + 5));

        CompletedTaskStatistics report = task_taskDao.getTaskReportCompleted(user);
        DateTime currentDate = new DateTime();

        assertEquals(11, report.getCompletedTasksCount());

        assertEquals(3, report.getTasksMonthList().size());
        assertEquals(1, new DateTime(report.getTasksMonthList().get(0)).getMonthOfYear());
        assertEquals(currentDate.getYear(), new DateTime(report.getTasksMonthList().get(0)).getYear());
        assertEquals(3, new DateTime(report.getTasksMonthList().get(1)).getMonthOfYear());
        assertEquals(currentDate.getYear(), new DateTime(report.getTasksMonthList().get(1)).getYear());
        assertEquals(4, new DateTime(report.getTasksMonthList().get(2)).getMonthOfYear());
        assertEquals(currentDate.getYear(), new DateTime(report.getTasksMonthList().get(2)).getYear());

        assertEquals(3, report.getLowPriorityTasksByMonthList().size());
        assertEquals(Integer.valueOf(3), report.getLowPriorityTasksByMonthList().get(0));
        assertEquals(Integer.valueOf(2), report.getLowPriorityTasksByMonthList().get(1));
        assertEquals(Integer.valueOf(0), report.getLowPriorityTasksByMonthList().get(2));

        assertEquals(3, report.getMediumPriorityTasksByMonthList().size());
        assertEquals(Integer.valueOf(0), report.getMediumPriorityTasksByMonthList().get(0));
        assertEquals(Integer.valueOf(1), report.getMediumPriorityTasksByMonthList().get(1));
        assertEquals(Integer.valueOf(0), report.getMediumPriorityTasksByMonthList().get(2));

        assertEquals(3, report.getHighPriorityTasksByMonthList().size());
        assertEquals(Integer.valueOf(0), report.getHighPriorityTasksByMonthList().get(0));
        assertEquals(Integer.valueOf(1), report.getHighPriorityTasksByMonthList().get(1));
        assertEquals(Integer.valueOf(1), report.getHighPriorityTasksByMonthList().get(2));
    }

	/**
     * Saves a task with the given parameters.
     *
     * @param name
     * @param state
     * @param priority
     * @param completedDate
     */
    private void saveTask(String name, PersistableState state, TaskPriority priority, DateTime completedDate) {
        Task task = new Task(user, category);
        task.setName(name);
        task.setPersistableState(state);
        task.setPriority(priority);
        task.setPersistableStateDate(completedDate);

        task_taskService.save(task);
    }

}