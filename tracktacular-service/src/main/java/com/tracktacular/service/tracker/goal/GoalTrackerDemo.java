package com.tracktacular.service.tracker.goal;

import org.joda.time.DateTime;

import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;
import com.tracktacular.service.tracker.goal.Goal.ProgressTracker;


/**
 * GoalTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GoalTrackerDemo extends AbstractTrackerDemo {
	private GoalTrackerFacade trackerFacade;

	// constructor
	public GoalTrackerDemo(GoalTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		// lose 8 pounds
		Goal goal = new Goal();
		goal.setUser(user);
		goal.setName("Weight Loss");
		goal.setDescription("Lose 8 pounds by the end of the month");
		goal.setStartDate(new DateTime().minusWeeks(1));
		goal.setTargetDate(new DateTime().minusWeeks(1).plusMonths(1));
		goal.setProgressTracker(ProgressTracker.OBJECTIVE);

		trackerFacade.saveGoal(goal);

		Objective objective = new Objective();
		objective.setName("Lose 2 pounds");
		objective.setStartDate(new DateTime().minusWeeks(1));
		objective.setTargetDate(new DateTime());
		objective.setPersistableState(PersistableState.ARCHIVED);
		objective.setUser(user);
		objective.setGoal(goal);

		trackerFacade.saveObjective(objective);

		objective = new Objective();
		objective.setName("Lose 2 pounds");
		objective.setStartDate(new DateTime());
		objective.setTargetDate(new DateTime().plusWeeks(1));
		objective.setUser(user);
		objective.setGoal(goal);

		trackerFacade.saveObjective(objective);

		objective = new Objective();
		objective.setName("Lose 2 pounds");
		objective.setStartDate(new DateTime().plusWeeks(1));
		objective.setTargetDate(new DateTime().plusWeeks(2));
		objective.setUser(user);
		objective.setGoal(goal);

		trackerFacade.saveObjective(objective);

		objective = new Objective();
		objective.setName("Lose 2 pounds");
		objective.setStartDate(new DateTime().plusWeeks(2));
		objective.setTargetDate(new DateTime().plusWeeks(3));
		objective.setUser(user);
		objective.setGoal(goal);

		trackerFacade.saveObjective(objective);

		goal = new Goal();
		goal.setUser(user);
		goal.setName("Vacation");
		goal.setDescription("Save $1000 for a 1 week vacation");
		goal.setStartDate(new DateTime().plusMonths(1));
		goal.setTargetDate(new DateTime().plusMonths(3));
		goal.setProgressTracker(ProgressTracker.MANUAL);
		goal.setProgress(10);

		trackerFacade.saveGoal(goal);

		objective = new Objective();
		objective.setName("Make lunch instead of ordering out");
		objective.setStartDate(new DateTime().plusMonths(1));
		objective.setTargetDate(new DateTime().plusMonths(1).plusWeeks(1));
		objective.setUser(user);
		objective.setGoal(goal);

		trackerFacade.saveObjective(objective);

		goal = new Goal();
		goal.setUser(user);
		goal.setName("College");
		goal.setDescription("Finish college and get my bachelor's degree");
		goal.setStartDate(new DateTime().minusMonths(2));
		goal.setTargetDate(new DateTime().plusMonths(6));
		goal.setProgressTracker(ProgressTracker.TIME);

		trackerFacade.saveGoal(goal);

		goal = new Goal();
		goal.setUser(user);
		goal.setName("Run a 5K");
		goal.setDescription("Enter and complete a 5K run");
		goal.setStartDate(new DateTime().minusYears(1).minusMonths(2));
		goal.setTargetDate(new DateTime().minusYears(1).plusMonths(6));
		goal.setProgressTracker(ProgressTracker.TIME);
		goal.setPersistableState(PersistableState.ARCHIVED);

		trackerFacade.saveGoal(goal);
	}

}
