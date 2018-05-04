package com.tracktacular.service.tracker.exercise;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * ExerciseTrackerFacadeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ExerciseTrackerFacadeImplTest extends TracktacularServiceTestCase {
	Routine routine;
	Workout workout;
	Exercise exercise;
	Entry entry;
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

		// workout split A
		workout = new Workout(user, routine);
		workout.setName("Split A");
		workout.setDate(new DateTime());
		exerciseTrackerFacade.saveWorkout(workout);

		// squat stretch
		exercise = new Exercise(user, workout);
		exercise.setName("Stretch");
		exercise.setTrackDuration(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setMinutes(10);
		exerciseTrackerFacade.saveEntry(entry);

		// squat
		exercise = new Exercise(user, workout);
		exercise.setName("Squat");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(45.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(85.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3);
		entry.setWeight(125.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(175.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(175.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(175.0);
		exerciseTrackerFacade.saveEntry(entry);

		// bench
		exercise = new Exercise(user, workout);
		exercise.setName("Bench Press");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(45.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(85.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3);
		entry.setWeight(105.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(135.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(135.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(135.0);
		exerciseTrackerFacade.saveEntry(entry);

		// cardio
		exercise = new Exercise(user, workout);
		exercise.setName("Run");
		exercise.setTrackDuration(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setMinutes(15);
		entry.setDistance(1.0);
		exerciseTrackerFacade.saveEntry(entry);

		// workout split B
		workout = new Workout(user, routine);
		workout.setName("Split B");
		workout.setDate(new DateTime());
		exerciseTrackerFacade.saveWorkout(workout);

		// squat stretch
		exercise = new Exercise(user, workout);
		exercise.setName("Stretch");
		exercise.setTrackDuration(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setMinutes(10);
		exerciseTrackerFacade.saveEntry(entry);

		// squat
		exercise = new Exercise(user, workout);
		exercise.setName("Squat");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(45.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(85.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3);
		entry.setWeight(125.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(175.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(175.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(175.0);
		exerciseTrackerFacade.saveEntry(entry);

		// deadlift
		exercise = new Exercise(user, workout);
		exercise.setName("Deadlift");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(45.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(85.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3);
		entry.setWeight(105.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(155.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(155.0);
		exerciseTrackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5);
		entry.setWeight(155.0);
		exerciseTrackerFacade.saveEntry(entry);

		// cardio
		exercise = new Exercise(user, workout);
		exercise.setName("Exercise Bike");
		exercise.setTrackDuration(true);
		exercise.setTrackCalories(true);
		exerciseTrackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setMinutes(15);
		entry.setCalories(200.0);
		exerciseTrackerFacade.saveEntry(entry);
	}

	@Test
	public void deleteAll() {
		assertEquals(1, exerciseTrackerFacade.findActiveRoutineCount(user));

		exerciseTrackerFacade.deleteAll(user);

		assertEquals(0, exerciseTrackerFacade.findActiveRoutineCount(user));
		assertEquals(0, exerciseTrackerFacade.findCompletedRoutineCount(user));
		assertEquals(0, exerciseTrackerFacade.findDeletedRoutineCount(user));
	}

}
