package com.tracktacular.service.tracker.exercise;

import java.util.Set;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Exercise
 *
 * A representation of a workout exercise.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Exercise extends AbstractPersistableUser implements Sortable, Comparable<Exercise> {

	// static Properties
	public static final Property<Exercise, String> NAME = Property.of("name");
	public static final Property<Exercise, String> NOTES = Property.of("notes");
	public static final Property<Exercise, Workout> WORKOUT = Property.of("workout");
	public static final Property<Exercise, Boolean> TRACK_CALORIES = Property.of("trackCalories");
	public static final Property<Exercise, Boolean> TRACK_DISTANCE = Property.of("trackDistance");
	public static final Property<Exercise, Boolean> TRACK_DURATION = Property.of("trackDuration");
	public static final Property<Exercise, Boolean> TRACK_REPETITIONS = Property.of("trackRepetitions");
	public static final Property<Exercise, Boolean> TRACK_WEIGHT = Property.of("trackWeight");
	public static final Property<Exercise, Integer> SORT = Property.of("sort");

	// properties
	private String name;
	private String notes;
	private Workout workout;
	private Set<Entry> entries;
	private boolean trackCalories;
	private boolean trackDistance;
	private boolean trackDuration;
	private boolean trackRepetitions;
	private boolean trackWeight;
	private int sort;

	// constructor
	public Exercise() {

	}

	// constructor
	public Exercise(User user, Workout workout) {
		super(user);
		this.workout = workout;
	}

	// constructor
	public Exercise(User user, Workout workout, Exercise exercise) {
		super(user);
		this.workout = workout;
		this.name = exercise.name;
		this.notes = exercise.notes;
		this.trackCalories = exercise.trackCalories;
		this.trackDistance = exercise.trackDistance;
		this.trackDuration = exercise.trackDuration;
		this.trackRepetitions = exercise.trackRepetitions;
		this.trackWeight = exercise.trackWeight;
		this.sort = exercise.sort;
	}

	/**
	 * Returns true if entries are sortable.
	 *
	 * @return boolean
	 */
	public boolean isEntriesSortable() {
		return entries != null && entries.size() > 1;
	}

	/**
	 * Returns the number of things are tracked in this exercise.
	 *
	 * @return int
	 */
	public int getTrackCount() {
		int i = 0;

		if(trackCalories)
			i++;

		if(trackDistance)
			i++;

		if(trackDuration)
			i++;

		if(trackRepetitions)
			i++;

		if(trackWeight)
			i++;

		return i;
	}

	@Override
	public int compareTo(Exercise o) {
		return getWorkout().getDate().compareTo(o.getWorkout().getDate());
	}

	/**
	 * Returns the name slug of the exercise name.
	 *
	 * @return String
	 */
	public String getNameSlug() {
		return toSlug(name);
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Entry> getEntries() {
		return entries;
	}

	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isTrackCalories() {
		return trackCalories;
	}

	public void setTrackCalories(boolean trackCalories) {
		this.trackCalories = trackCalories;
	}

	public boolean isTrackDistance() {
		return trackDistance;
	}

	public void setTrackDistance(boolean trackDistance) {
		this.trackDistance = trackDistance;
	}

	public boolean isTrackDuration() {
		return trackDuration;
	}

	public void setTrackDuration(boolean trackDuration) {
		this.trackDuration = trackDuration;
	}

	public boolean isTrackRepetitions() {
		return trackRepetitions;
	}

	public void setTrackRepetitions(boolean trackRepetitions) {
		this.trackRepetitions = trackRepetitions;
	}

	public boolean isTrackWeight() {
		return trackWeight;
	}

	public void setTrackWeight(boolean trackWeight) {
		this.trackWeight = trackWeight;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}