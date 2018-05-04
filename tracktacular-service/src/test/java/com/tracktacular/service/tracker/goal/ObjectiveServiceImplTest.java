package com.tracktacular.service.tracker.goal;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * ObjectiveServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ObjectiveServiceImplTest extends TracktacularServiceTestCase {
	Objective objective;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		Goal goal = new Goal();
		goal.setName("name");
		goal.setDescription("description");
		goal.setStartDate(new DateTime());
		goal.setTargetDate(new DateTime());
		goal.setUser(user);

		objective = new Objective();
		objective.setStartDate(new DateTime());
		objective.setTargetDate(new DateTime());
		objective.setName("name");
		objective.setUser(user);
		objective.setGoal(goal);

		userService.save(user);
		goal_goalService.save(goal);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(objective, goal_objectiveService);
	}

}