package com.tracktacular.service.tracker.exercise;

import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.imadp.core.Property;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Workout
 *
 * A representation of a workout.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Workout extends AbstractPersistableUser {

	// static Properties
	public static final Property<Workout, String> NAME = Property.of("name");
	public static final Property<Workout, String> NOTES = Property.of("notes");
	public static final Property<Workout, DateTime> DATE = Property.of("date");
	public static final Property<Workout, Duration> DURATION = Property.of("duration");
	public static final Property<Workout, Routine> ROUTINE = Property.of("routine");
	public static final Property<Workout, Boolean> COLLAPSED = Property.of("collapsed");

	// properties
	private String name;
	private String notes;
	private Routine routine;
	private DateTime date;
	private Duration duration;
	private Set<Exercise> exercises;
	private boolean collapsed;

	// constructor
	public Workout() {
		this.date = new DateTime();
	}

	// constructor
	public Workout(User user, Routine routine) {
		super(user);
		this.routine = routine;
		this.date = new DateTime();
	}

	// constructor
	public Workout(User user, Routine routine, Workout workout) {
		super(user);
		this.routine = routine;
		this.date = new DateTime();
		this.name = workout.name;
		this.notes = workout.notes;
		this.duration = workout.duration;
	}

	/**
	 * Returns true if exercises are sortable.
	 *
	 * @return boolean
	 */
	public boolean isExercisesSortable() {
		return exercises != null && exercises.size() > 1;
	}

	// getters and setters
	public String getNameSlug() {
		return toSlug(getName());
	}

	public String getDateTimeString() {
		return toDateTimeString(date);
	}

	public void setDateTimeString(String dateTimeString) {
		this.date = fromDateTimeString(dateTimeString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		this.routine = routine;
	}

	public Set<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

}