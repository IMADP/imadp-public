package com.tracktacular.service.tracker.exercise;

import org.joda.time.Period;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Entry
 *
 * A representation of an exercise set.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Entry extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<Entry, Exercise> EXERCISE = Property.of("exercise");
	public static final Property<Entry, Double> CALORIES = Property.of("calories");
	public static final Property<Entry, Double> DISTANCE = Property.of("distance");
	public static final Property<Entry, Double> WEIGHT = Property.of("weight");
	public static final Property<Entry, Integer> REPETITIONS = Property.of("repetitions");
	public static final Property<Entry, Integer> HOURS = Property.of("hours");
	public static final Property<Entry, Integer> MINUTES = Property.of("minutes");
	public static final Property<Entry, Integer> SECONDS = Property.of("seconds");
	public static final Property<Entry, Integer> SORT = Property.of("sort");

	// properties
	private Exercise exercise;
	private Double calories;
	private Double distance;
	private Double weight;
	private Integer repetitions;
	private Integer hours;
	private Integer minutes;
	private Integer seconds;
	private int sort;

	// constructor
	public Entry() {

	}

	// constructor
	public Entry(User user, Exercise exercise) {
		super(user);
		this.exercise = exercise;
	}

	// constructor
	public Entry(User user, Exercise exercise, Entry entry) {
		super(user);
		this.exercise = exercise;
		this.repetitions = entry.repetitions;
		this.weight = entry.weight;
		this.hours = entry.hours;
		this.minutes = entry.minutes;
		this.seconds = entry.seconds;
		this.distance = entry.distance;
		this.calories = entry.calories;
	}

	/**
	 * Gets the duration in milliseconds.
	 *
	 * @return int
	 */
	public long getDurationInMilliseconds() {
		int hours = this.hours == null ? 0 : this.hours;
		int minutes = this.minutes == null ? 0 : this.minutes;
		int seconds = this.seconds == null ? 0 : this.seconds;
		return new Period(hours, minutes, seconds, 0).toStandardDuration().getMillis();
	}

	// getters and setters
	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(Integer repetitions) {
		this.repetitions = repetitions;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

}