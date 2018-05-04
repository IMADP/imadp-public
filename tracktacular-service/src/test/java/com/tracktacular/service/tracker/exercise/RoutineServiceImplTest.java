package com.tracktacular.service.tracker.exercise;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * RoutineServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RoutineServiceImplTest extends TracktacularServiceTestCase {
	Routine routine;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		routine = new Routine();
		routine.setUser(user);
		routine.setName("name");

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(routine, exercise_routineService);
	}

}
