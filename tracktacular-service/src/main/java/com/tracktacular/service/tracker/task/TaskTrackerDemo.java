package com.tracktacular.service.tracker.task;

import org.joda.time.DateTime;

import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;
import com.tracktacular.service.tracker.task.Task.TaskPriority;


/**
 * TaskTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskTrackerDemo extends AbstractTrackerDemo {
	private TaskTrackerFacade trackerFacade;

	// constructor
	public TaskTrackerDemo(TaskTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		// active tasks
		TaskCategory category = createPersonalTasks(user);
		createWorkTasks(user);
		createSchoolTasks(user);

		// completed tasks
		Task task = new Task();
		task.setUser(user);
		task.setCategory(category);
		task.setPriority(TaskPriority.MEDIUM);
		task.setPersistableState(PersistableState.ARCHIVED);
		task.setPersistableStateDate(new DateTime());
		task.setName("Take out the trash");
		trackerFacade.saveTask(task);

		// deleted tasks
		task = new Task();
		task.setUser(user);
		task.setCategory(category);
		task.setPriority(TaskPriority.MEDIUM);
		task.setPersistableState(PersistableState.DELETED);
		task.setPersistableStateDate(new DateTime());
		task.setName("Clean out the garage");
		trackerFacade.saveTask(task);
	}

	/**
	 * Creates personal tasks.
	 *
	 * @param user
	 * @return TaskCategory
	 */
	private TaskCategory createPersonalTasks(User user) {
		TaskCategory personal = new TaskCategory(user);
		personal.setName("Personal");
		personal.setSort(10);

		trackerFacade.saveTaskCategory(personal);

		Task task = new Task();
		task.setUser(user);
		task.setCategory(personal);
		task.setPriority(TaskPriority.HIGH);
		task.setName("Purchase movie tickets for tomorrow");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(personal);
		task.setPriority(TaskPriority.HIGH);
		task.setName("Clean the bed sheets");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(personal);
		task.setPriority(TaskPriority.MEDIUM);
		task.setName("Pickup groceries");
		task.setNotes("Milk\nEggs\nCereal\nCat food");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(personal);
		task.setPriority(TaskPriority.LOW);
		task.setName("Get car cleaned and inspected");

		trackerFacade.saveTask(task);

		return personal;
	}

	/**
	 * Creates work tasks.
	 *
	 * @param user
	 */
	private void createWorkTasks(User user) {
		TaskCategory work = new TaskCategory(user);
		work.setName("Work");
		work.setSort(20);

		trackerFacade.saveTaskCategory(work);

		Task task = new Task();
		task.setUser(user);
		task.setCategory(work);
		task.setPriority(TaskPriority.HIGH);
		task.setName("Create pretty yet intellectually devoid Powerpoint presentation");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(work);
		task.setPriority(TaskPriority.MEDIUM);
		task.setName("Fill out a useless TPS report");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(work);
		task.setPriority(TaskPriority.MEDIUM);
		task.setName("Fill out second TPS report");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(work);
		task.setPriority(TaskPriority.MEDIUM);
		task.setName("Fill out third TPS report");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(work);
		task.setPriority(TaskPriority.LOW);
		task.setName("Put resume on all possible job sites");

		trackerFacade.saveTask(task);
	}

	/**
	 * Creates school tasks.
	 *
	 * @param user
	 */
	private void createSchoolTasks(User user) {
		TaskCategory school = new TaskCategory(user);
		school.setName("School");
		school.setSort(30);

		trackerFacade.saveTaskCategory(school);

		TaskCategory worldLiterature = new TaskCategory(user);
		worldLiterature.setParentCategory(school);
		worldLiterature.setName("World Literature");
		worldLiterature.setSort(10);

		trackerFacade.saveTaskCategory(worldLiterature);

		TaskCategory quantumPhysics = new TaskCategory(user);
		quantumPhysics.setParentCategory(school);
		quantumPhysics.setName("Quantum Physics");
		quantumPhysics.setSort(20);

		trackerFacade.saveTaskCategory(quantumPhysics);

		TaskCategory calculus = new TaskCategory(user);
		calculus.setParentCategory(school);
		calculus.setName("Calculus");
		calculus.setSort(30);
		calculus.setCollapsed(true);

		trackerFacade.saveTaskCategory(calculus);

		Task task = new Task();
		task.setUser(user);
		task.setCategory(worldLiterature);
		task.setPriority(TaskPriority.HIGH);
		task.setName("Purchase books for next semester");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(worldLiterature);
		task.setPriority(TaskPriority.MEDIUM);
		task.setName("Finish writing the paper on Hamlet");
		task.setNotes("Outline:\n Intro\n Argument 1\n Argument 2\n Argument 3\n Outro");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(quantumPhysics);
		task.setPriority(TaskPriority.LOW);
		task.setName("Drop class and burn all textbooks");

		trackerFacade.saveTask(task);

		task = new Task();
		task.setUser(user);
		task.setCategory(calculus);
		task.setPriority(TaskPriority.LOW);
		task.setName("Setup exam study group");

		trackerFacade.saveTask(task);
	}

}
