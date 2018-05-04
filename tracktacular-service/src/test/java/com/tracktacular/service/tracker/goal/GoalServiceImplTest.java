package com.tracktacular.service.tracker.goal;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;
import com.tracktacular.service.tracker.goal.Goal.ProgressTracker;


/**
 * GoalServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GoalServiceImplTest extends TracktacularServiceTestCase {
	Goal goal;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		goal = new Goal();
		goal.setName("name");
		goal.setDescription("description");
		goal.setStartDate(new DateTime());
		goal.setTargetDate(new DateTime());
		goal.setUser(user);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(goal, goal_goalService);
	}

	@Test
	public void goalProgress() {
		Goal goal = new Goal();
		DateTime date = new DateTime();

		goal.setStartDate(date.plusDays(10));
		goal.setTargetDate(date.plusDays(20));

		assertEquals(0, goal.getProgress());

		goal.setStartDate(date.minusDays(20));
		goal.setTargetDate(date.minusDays(10));

		assertEquals(100, goal.getProgress());

		goal.setStartDate(date.minusDays(100));
		goal.setTargetDate(date.plusDays(100));

		assertEquals(50, goal.getProgress());
	}

	@Test
	public void averageGoalProgress() {
		List<Goal> goals = new ArrayList<>();

		Goal goal = new Goal();
		goal.setProgress(25);
		goal.setProgressTracker(ProgressTracker.MANUAL);
		goals.add(goal);

		goal = new Goal();
		goal.setProgress(75);
		goal.setProgressTracker(ProgressTracker.MANUAL);
		goals.add(goal);

		goal = new Goal();
		goal.setProgress(50);
		goal.setProgressTracker(ProgressTracker.MANUAL);
		goals.add(goal);

		assertEquals(50, new Goals(goals).getAverageProgress());
	}

}