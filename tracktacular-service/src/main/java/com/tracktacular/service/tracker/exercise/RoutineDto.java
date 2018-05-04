package com.tracktacular.service.tracker.exercise;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import com.imadp.core.AbstractSerializable;

/**
 * RoutineDto
 *
 * A data transfer object containing a routine and corresponding data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RoutineDto extends AbstractSerializable {
	private final Routine routine;
    private final long workoutCount;
    private final Workout lastWorkout;
    private final Map<String, ExerciseStatistics> exerciseStatisticsMap;

    // constructor
	public RoutineDto(Routine routine, List<Workout> allWorkouts) {
		this.routine = routine;
		this.workoutCount = allWorkouts.size();
	    this.exerciseStatisticsMap = new HashMap<>();
	    this.lastWorkout = allWorkouts.isEmpty() ? null : allWorkouts.get(0);

	    allWorkouts = new ArrayList<>(allWorkouts);
	    Collections.reverse(allWorkouts);
	    buildExerciseStatistics(allWorkouts);
	}

	/**
     * Builds the exercise statistics.
     *
     * @param activeWorkoutsList
     */
    private void buildExerciseStatistics(List<Workout> activeWorkoutsList) {
    	List<Exercise> exercises = new ArrayList<>();

		for(Workout workout : activeWorkoutsList)
			exercises.addAll(workout.getExercises());

		Collections.sort(exercises);

		for(Exercise exercise: exercises)
			getExerciseStatistics(exercise.getNameSlug()).addExercise(exercise);
	}

    /**
     * Returns the ExerciseStatistics for the given exercise.
     *
     * @param exerciseName
     * @return ExerciseReportData
     */
    private ExerciseStatistics getExerciseStatistics(String exerciseName) {
    	ExerciseStatistics exerciseStatistics = exerciseStatisticsMap.get(exerciseName);

    	if(exerciseStatistics == null)
    	{
    		exerciseStatistics = new ExerciseStatistics(exerciseName);
    		exerciseStatisticsMap.put(exerciseName, exerciseStatistics);
    	}

    	return exerciseStatistics;
    }

    /**
     * Returns the number of exercises in the statistics collection.
     *
     * @return int
     */
    public int getExerciseStatisticsCount() {
    	return exerciseStatisticsMap.size();
    }

	// getters and setters
	public Routine getRoutine() {
		return routine;
	}

	public long getWorkoutCount() {
		return workoutCount;
	}

	public Collection<ExerciseStatistics> getExerciseStatisticsCollection() {
		return this.exerciseStatisticsMap.values();
	}

	public Workout getLastWorkout() {
		return lastWorkout;
	}

	/**
	 * ExerciseStatistics
	 *
	 * Contains exercise statistics.
	 *
	 * @author Anthony DePalma
	 * @version 1.0
	 */
	public class ExerciseStatistics extends AbstractSerializable {
		private String exerciseName;
		private int exerciseCount;
		private int entryCaloriesCount;
		private int entryDistanceCount;
		private int entryDurationCount;
		private int entryRepetitionCount;
		private int entryWeightCount;
		private List<ExerciseEntryStatistics> caloriesStatistics = new ArrayList<>();
		private List<ExerciseEntryStatistics> distanceStatistics = new ArrayList<>();
		private List<ExerciseEntryStatistics> durationStatistics = new ArrayList<>();
		private List<ExerciseEntryStatistics> repetitionsStatistics = new ArrayList<>();
		private List<ExerciseEntryStatistics> weightStatistics = new ArrayList<>();

		// constructor
		public ExerciseStatistics(String exerciseName) {
			this.exerciseName = exerciseName;
		}

		/**
		 * Adds an exercise to the report data.
		 *
		 * @param exercise
		 */
		public void addExercise(Exercise exercise) {
			exerciseCount++;

			// add entry counts
			int entriesCount = exercise.getEntries().size();

			SummaryStatistics entryCaloriesStatistics = new SummaryStatistics();
			SummaryStatistics entryDistanceStatistics = new SummaryStatistics();
			SummaryStatistics entryDurationStatistics = new SummaryStatistics();
			SummaryStatistics entryRepetitionsStatistics = new SummaryStatistics();
			SummaryStatistics entryWeightStatistics = new SummaryStatistics();

			// add entry statistics
			for(Entry entry : exercise.getEntries())
			{
				if(exercise.isTrackCalories())
					entryCaloriesStatistics.addValue(entry.getCalories() != null ? entry.getCalories() : 0);

				if(exercise.isTrackDistance())
					entryDistanceStatistics.addValue(entry.getDistance() != null ? entry.getDistance() : 0);

				if(exercise.isTrackDuration())
					entryDurationStatistics.addValue(entry.getDurationInMilliseconds());

				if(exercise.isTrackRepetitions())
					entryRepetitionsStatistics.addValue(entry.getRepetitions() != null ? entry.getRepetitions() : 0);

				if(exercise.isTrackWeight())
					entryWeightStatistics.addValue(entry.getWeight() != null ? entry.getWeight() : 0);
			}

			if(exercise.isTrackCalories())
			{
				entryCaloriesCount = entryCaloriesCount + entriesCount;
				ExerciseEntryStatistics exerciseEntryStatistics = new ExerciseEntryStatistics();
				exerciseEntryStatistics.workoutDate = exercise.getWorkout().getDate().getMillis();
				exerciseEntryStatistics.max = entryCaloriesStatistics.getMax();
				exerciseEntryStatistics.min = entryCaloriesStatistics.getMin();
				exerciseEntryStatistics.mean = entryCaloriesStatistics.getMean();
				caloriesStatistics.add(exerciseEntryStatistics);
			}

			if(exercise.isTrackDistance())
			{
				entryDistanceCount = entryDistanceCount + entriesCount;
				ExerciseEntryStatistics exerciseEntryStatistics = new ExerciseEntryStatistics();
				exerciseEntryStatistics.workoutDate = exercise.getWorkout().getDate().getMillis();
				exerciseEntryStatistics.max = entryDistanceStatistics.getMax();
				exerciseEntryStatistics.min = entryDistanceStatistics.getMin();
				exerciseEntryStatistics.mean = entryDistanceStatistics.getMean();
				distanceStatistics.add(exerciseEntryStatistics);
			}

			if(exercise.isTrackDuration())
			{
				entryDurationCount = entryDurationCount + entriesCount;
				ExerciseEntryStatistics exerciseEntryStatistics = new ExerciseEntryStatistics();
				exerciseEntryStatistics.workoutDate = exercise.getWorkout().getDate().getMillis();
				exerciseEntryStatistics.max = entryDurationStatistics.getMax();
				exerciseEntryStatistics.min = entryDurationStatistics.getMin();
				exerciseEntryStatistics.mean = entryDurationStatistics.getMean();
				durationStatistics.add(exerciseEntryStatistics);
			}

			if(exercise.isTrackRepetitions())
			{
				entryRepetitionCount = entryRepetitionCount + entriesCount;
				ExerciseEntryStatistics exerciseEntryStatistics = new ExerciseEntryStatistics();
				exerciseEntryStatistics.workoutDate = exercise.getWorkout().getDate().getMillis();
				exerciseEntryStatistics.max = entryRepetitionsStatistics.getMax();
				exerciseEntryStatistics.min = entryRepetitionsStatistics.getMin();
				exerciseEntryStatistics.mean = entryRepetitionsStatistics.getMean();
				repetitionsStatistics.add(exerciseEntryStatistics);
			}

			if(exercise.isTrackWeight())
			{
				entryWeightCount = entryWeightCount + entriesCount;
				ExerciseEntryStatistics exerciseEntryStatistics = new ExerciseEntryStatistics();
				exerciseEntryStatistics.workoutDate = exercise.getWorkout().getDate().getMillis();
				exerciseEntryStatistics.max = entryWeightStatistics.getMax();
				exerciseEntryStatistics.min = entryWeightStatistics.getMin();
				exerciseEntryStatistics.mean = entryWeightStatistics.getMean();
				weightStatistics.add(exerciseEntryStatistics);
			}

		}

		// getters and setters
		public String getExerciseName() {
			return exerciseName;
		}

		public int getExerciseCount() {
			return exerciseCount;
		}

		public int getEntryCaloriesCount() {
			return entryCaloriesCount;
		}

		public int getEntryDistanceCount() {
			return entryDistanceCount;
		}

		public int getEntryDurationCount() {
			return entryDurationCount;
		}

		public int getEntryRepetitionCount() {
			return entryRepetitionCount;
		}

		public int getEntryWeightCount() {
			return entryWeightCount;
		}

		public List<ExerciseEntryStatistics> getCaloriesStatistics() {
			return caloriesStatistics;
		}

		public List<ExerciseEntryStatistics> getDistanceStatistics() {
			return distanceStatistics;
		}

		public List<ExerciseEntryStatistics> getDurationStatistics() {
			return durationStatistics;
		}

		public List<ExerciseEntryStatistics> getRepetitionsStatistics() {
			return repetitionsStatistics;
		}

		public List<ExerciseEntryStatistics> getWeightStatistics() {
			return weightStatistics;
		}

		/**
		 * ExerciseEntryStatistics
		 *
		 * Contains exercise entry statistics.
		 *
		 * @author Anthony DePalma
		 * @version 1.0
		 */
		public class ExerciseEntryStatistics {
			private long workoutDate;
			private double min;
			private double max;
			private double mean;

			// getters and setters
			public long getWorkoutDate() {
				return workoutDate;
			}

			public double getMin() {
				return Double.isNaN(min) ? 0 : Double.valueOf(new DecimalFormat("#.##").format(min));
			}

			public double getMax() {
				return Double.isNaN(max) ? 0 : Double.valueOf(new DecimalFormat("#.##").format(max));
			}

			public double getMean() {
				return Double.isNaN(mean) ? 0 : Double.valueOf(new DecimalFormat("#.##").format(mean));
			}

		}

	}

}