package com.tracktacular.service.tracker.exercise;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * ExerciseTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ExerciseTrackerDemo extends AbstractTrackerDemo {
	private ExerciseTrackerFacade trackerFacade;

	// constructor
	public ExerciseTrackerDemo(ExerciseTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Routine routine;

		routine = new Routine(user);
		routine.setName("Strength Routine");
		routine.setStartDate(new DateTime().withDayOfMonth(5));

		trackerFacade.saveRoutine(routine);

		insertSplitA(routine, true, -30, 0,  .1,  1,  0, 5);
		insertSplitB(routine, true, -27, 5,  .2,  2,  0, 10);
		insertSplitA(routine, true, -24, 10, .3,  3,  0, 15);
		insertSplitB(routine, false, -21, 15, .4,  4,  0, 20);
		insertSplitA(routine, false, -18, 20, .5,  5,  0, 25);
	}

	/**
	 * Inserts workout split A.
	 *
	 * @param routine
	 * @param collapsed
	 * @param dateOffset
	 * @param weightOffset
	 * @param caloriesOffset
	 * @param distanceOffset
	 * @param durationOffset
	 * @param repetitionsOffset
	 */
	private void insertSplitA(Routine routine, boolean collapsed, int dateOffset, double caloriesOffset, double distanceOffset,
			int durationOffset, int repetitionsOffset, double weightOffset) {
		Workout workout;
		Exercise exercise;
		Entry entry;
		User user = routine.getUser();

		// workout split A
		workout = new Workout(user, routine);
		workout.setName("Split A");
		workout.setDate(new DateTime().plusDays(dateOffset));
		workout.setCollapsed(collapsed);
		trackerFacade.saveWorkout(workout);

		// warmup
		exercise = new Exercise(user, workout);
		exercise.setName("Warmup");
		exercise.setTrackDuration(true);
		trackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setMinutes(10 + durationOffset);
		trackerFacade.saveEntry(entry);

		// squat
		exercise = new Exercise(user, workout);
		exercise.setName("Squat");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		trackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(45.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(85.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3 + repetitionsOffset);
		entry.setWeight(125.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		// bench
		exercise = new Exercise(user, workout);
		exercise.setName("Bench Press");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		trackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(45.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(85.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3 + repetitionsOffset);
		entry.setWeight(105.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(135.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(135.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(135.0 + weightOffset);
		trackerFacade.saveEntry(entry);
	}

	/**
	 * Inserts workout split B.
	 *
	 * @param routine
	 * @param collapsed
	 * @param dateOffset
	 * @param weightOffset
	 * @param caloriesOffset
	 * @param distanceOffset
	 * @param durationOffset
	 * @param repetitionsOffset
	 */
	private void insertSplitB(Routine routine, boolean collapsed, int dateOffset, double caloriesOffset, double distanceOffset,
			int durationOffset, int repetitionsOffset, double weightOffset) {
		Workout workout;
		Exercise exercise;
		Entry entry;
		User user = routine.getUser();

		// workout split A
		workout = new Workout(user, routine);
		workout.setName("Split B");
		workout.setDate(new DateTime().plusDays(dateOffset));
		workout.setCollapsed(collapsed);
		trackerFacade.saveWorkout(workout);

		// warmup
		exercise = new Exercise(user, workout);
		exercise.setName("Warmup");
		exercise.setTrackDuration(true);
		exercise.setTrackDistance(true);
		exercise.setTrackCalories(true);
		trackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setMinutes(10 + durationOffset);
		entry.setDistance(10 + distanceOffset);
		entry.setCalories(100 + caloriesOffset);
		trackerFacade.saveEntry(entry);

		// squat
		exercise = new Exercise(user, workout);
		exercise.setName("Squat");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		trackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(45.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(85.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3 + repetitionsOffset);
		entry.setWeight(125.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		// deadlift
		exercise = new Exercise(user, workout);
		exercise.setName("Deadlift");
		exercise.setTrackRepetitions(true);
		exercise.setTrackWeight(true);
		trackerFacade.saveExercise(exercise);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(85.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(125.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(3 + repetitionsOffset);
		entry.setWeight(145.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);

		entry = new Entry(user, exercise);
		entry.setRepetitions(5 + repetitionsOffset);
		entry.setWeight(175.0 + weightOffset);
		trackerFacade.saveEntry(entry);
	}

}
