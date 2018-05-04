package com.tracktacular.service.tracker.goal;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Objective
 *
 * Represents an objective to accomplish for a goal.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Objective extends AbstractPersistableUser {

	// static Properties
	public static final Property<Objective, String> NAME = Property.of("name");
	public static final Property<Objective, String> NOTES = Property.of("notes");
	public static final Property<Objective, DateTime> TARGET_DATE = Property.of("targetDate");
	public static final Property<Objective, Goal> GOAL = Property.of("goal");

	// properties
	private String name;
	private String notes;
	private DateTime startDate;
	private DateTime targetDate;
	private Goal goal;

	// constructor
	public Objective() {
		this(null, null);
	}

	// constructor
	public Objective(User user, Goal goal) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
		this.goal = goal;
		this.startDate = new DateTime();
	}

	/**
	 * Returns true if the objective is late, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isLate() {
		return isActiveState() && new DateTime().isAfter(getTargetDate().plusDays(1));
	}

	/**
	 * Returns true if the objective is completed, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isCompleted() {
		return isArchivedState();
	}

	// getters and setters
	public String getStartDateString() {
		return toDateString(startDate);
	}

	public void setStartDateString(String dateString) {
		this.startDate = fromDateString(dateString);
	}

	public String getTargetDateString() {
		return toDateString(targetDate);
	}

	public void setTargetDateString(String dateString) {
		this.targetDate = fromDateString(dateString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(DateTime targetDate) {
		this.targetDate = targetDate;
	}

}