package com.tracktacular.service.tracker.exercise;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * WorkoutServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class WorkoutServiceImplTest extends TracktacularServiceTestCase {
	Routine routine;
	Workout workout;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		routine = new Routine();
		routine.setUser(user);
		routine.setName("name");

		workout = new Workout();
		workout.setName("name");
		workout.setDate(new DateTime());
		workout.setUser(user);
		workout.setRoutine(routine);

		userService.save(user);
		exercise_routineService.save(routine);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(workout, exercise_workoutService);
	}

	@Test
	public void deletingAssociations() {
		Workout workout = new Workout(user, routine);
		workout.setName("Starting Strength A");
		workout.setDate(new DateTime());

		exercise_workoutService.save(workout);

		Exercise exercise = new Exercise(user, workout);
		exercise.setName("Squat");

		exercise_workoutService.saveExercise(exercise);
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));

		exercise = new Exercise(user, workout);
		exercise.setName("Bench Press");

		exercise_workoutService.saveExercise(exercise);
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));

		exercise = new Exercise(user, workout);
		exercise.setName("Deadlift");

		exercise_workoutService.saveExercise(exercise);
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));
		exercise_workoutService.saveEntry(new Entry(user,exercise));

		workout = exercise_workoutService.get(workout.getId());

		exercise_workoutService.delete(workout);
	}

}
