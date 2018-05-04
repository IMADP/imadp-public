package com.tracktacular.service.tracker.exercise;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * EntryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EntryServiceImplTest extends TracktacularServiceTestCase {
	Entry entry;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		Routine routine = new Routine();
		routine.setUser(user);
		routine.setName("name");

		Workout workout = new Workout();
		workout.setName("name");
		workout.setDate(new DateTime());
		workout.setUser(user);
		workout.setRoutine(routine);

		Exercise exercise = new Exercise();
		exercise.setName("name");
		exercise.setUser(user);
		exercise.setWorkout(workout);

		entry = new Entry();
		entry.setExercise(exercise);
		entry.setUser(user);

		userService.save(user);
		exercise_routineService.save(routine);
		exercise_workoutService.save(workout);
		exercise_exerciseService.save(exercise);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(entry, exercise_entryService);
	}

}
