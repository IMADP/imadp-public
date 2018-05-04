package com.tracktacular.service.tracker.goal;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * TracktacularGoalTrackerFacadeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularGoalTrackerFacadeImplTest extends TracktacularServiceTestCase {
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		Goal goal = new Goal();
		goal.setName("name");
		goal.setDescription("description");
		goal.setStartDate(new DateTime());
		goal.setTargetDate(new DateTime());
		goal.setUser(user);

		Objective objective = new Objective();
		objective.setStartDate(new DateTime());
		objective.setTargetDate(new DateTime());
		objective.setName("name");
		objective.setUser(user);
		objective.setGoal(goal);

		userService.save(user);
		goal_goalService.save(goal);
		goal_objectiveService.save(objective);
	}

	@Test
	public void deleteAll() {
		assertEquals(1, goalTrackerFacade.findActiveGoals(user).getGoalCount());

		goalTrackerFacade.deleteAll(user);

		assertEquals(0, goalTrackerFacade.findActiveGoals(user).getGoalCount());
		assertEquals(0, goalTrackerFacade.findCompletedGoalCount(user));
		assertEquals(0, goalTrackerFacade.findDeletedGoalCount(user));
	}

}
