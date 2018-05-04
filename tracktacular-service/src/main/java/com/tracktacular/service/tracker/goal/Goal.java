package com.tracktacular.service.tracker.goal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Goal
 *
 * A representation of a pending goal.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Goal extends AbstractPersistableUser {

	// static Properties
	public static final Property<Goal, String> NAME = Property.of("name");
	public static final Property<Goal, String> NOTES = Property.of("notes");
	public static final Property<Goal, String> DESCRIPTION = Property.of("description");
	public static final Property<Goal, DateTime> START_DATE = Property.of("startDate");
	public static final Property<Goal, DateTime> TARGET_DATE = Property.of("targetDate");
	public static final Property<Goal, Boolean> PROGRESS = Property.of("progress");
	public static final Property<Goal, Boolean> PROGRESS_TRACKER = Property.of("progressTracker");
	public static final Property<Goal, Boolean> COLLAPSED = Property.of("collapsed");

	// properties
	private String name;
	private String notes;
	private String description;
	private DateTime startDate;
	private DateTime targetDate;
	private Set<Objective> objectives;
	private ProgressTracker progressTracker;
	private int progress;
	private boolean collapsed;

	// constructor
	public Goal() {
		this(null);
	}

	// constructor
	public Goal(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
		this.progressTracker = ProgressTracker.TIME;
		this.startDate = new DateTime();
	}

	/**
	 * Returns a new objective for this goal.
	 *
	 * @return Objective
	 */
	public Objective getNewObjective() {
		return new Objective(user, this);
	}

	/**
	 * Returns a whole number percentage from 0 to 100 determined by chosen progress tracker.
	 *
	 * @return int
	 */
	public int getProgress() {
		switch(progressTracker)
		{
			case MANUAL:    return progress;
			case OBJECTIVE: return getObjectiveProgress();
			case TIME:      return getTimeProgress();
		}

		throw new AssertionError();
	}

	/**
	 * Returns the progress as a whole number percentage from 0 to 100 determined by the amount
	 * of passed time vs remaining time.
	 *
	 * @return int
	 */
	private int getTimeProgress() {
		if(startDate == null || targetDate == null)
			return 0;

		DateTime date = new DateTime();

		if(date.isBefore(startDate))
			return 0;

		if(date.isAfter(targetDate))
			return 100;

		double totalDays = Days.daysBetween(startDate, targetDate).getDays();
		double passedDays = Days.daysBetween(startDate, date).getDays();

		return Double.valueOf(passedDays / totalDays * 100).intValue();
	}

	/**
	 * Returns the progress as a whole number percentage from 0 to 100 determined by the amount
	 * of completed objectives vs remaining objectives.
	 *
	 * @return int
	 */
	private int getObjectiveProgress() {
		double totalObjectives = getObjectivesCount();
		double completedObjectives = getCompletedObjectivesCount();

		if(totalObjectives == 0 || completedObjectives == 0)
			return 0;

		return Double.valueOf(completedObjectives / totalObjectives * 100).intValue();
	}

	/**
	 * Returns the count of objectives.
	 *
	 * @return int
	 */
	public int getObjectivesCount() {
		return objectives == null ? 0 : objectives.size();
	}

	/**
	 * Returns the count of completed objectives.
	 *
	 * @return int
	 */
	public int getCompletedObjectivesCount() {
		int i = 0;

		if(objectives != null)
			for(Objective objective : objectives)
				if(objective.isArchivedState())
					i++;

		return i;
	}

	/**
	 * Returns the time completed of the goal in milliseconds.
	 *
	 * @return long
	 */
	public long getTimeRemainingInMillis() {
		long totalTime = getTotalTimeInMillis();
		return totalTime - getTimeCompletedInMillis();
	}

	/**
	 * Returns the time completed of the goal in milliseconds.
	 *
	 * @return long
	 */
	public long getTimeCompletedInMillis() {
		long progress = getProgress();

		if(progress == 0)
			return 0;

		long totalTime = getTotalTimeInMillis();
		double percentage = Double.valueOf(progress) / Double.valueOf(100);
		return Double.valueOf(percentage * totalTime).longValue();
	}

	/**
	 * Returns the total time of the goal in milliseconds.
	 *
	 * @return long
	 */
	public long getTotalTimeInMillis() {
		if(targetDate.getMillis() == startDate.getMillis())
			return targetDate.plusDays(1).getMillis();

		return targetDate.getMillis() - startDate.getMillis();
	}

	/**
	 * Returns a list of upcoming objectives for the goal.
	 * The list will consist of any late objectives, and the first objective (or others with the same targetDate)
	 * in the list that have not been completed yet.
	 *
	 * @return List<Objective>
	 */
	public List<Objective> getUpcomingObjectives() {
		List<Objective> upcomingObjectives = new ArrayList<>();
		DateTime targetDate = null;

		for(Objective objective : getObjectives())
		{
			// add all of the late objectives first
			if(objective.isLate())
			{
				upcomingObjectives.add(objective);
				continue;
			}

			// ignore completed objectives
			if(objective.isCompleted())
				continue;

			// now we are looping through non-late objectives

			// if this is the first upcoming objective, mark the time and add it to the list
			if(targetDate == null)
			{
				targetDate = objective.getTargetDate();
				upcomingObjectives.add(objective);
				continue;
			}

			// if there are more objectives with the same date, add them
			if(targetDate.equals(objective.getTargetDate()))
				upcomingObjectives.add(objective);

			// otherwise finish looking for objectives.
			else
				break;
		}

		return upcomingObjectives;
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

	public DateTime getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(DateTime targetDate) {
		this.targetDate = targetDate;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public Set<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(Set<Objective> objectives) {
		this.objectives = objectives;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public ProgressTracker getProgressTracker() {
		return progressTracker;
	}

	public void setProgressTracker(ProgressTracker progressTracker) {
		this.progressTracker = progressTracker;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * PersistableEmailStatus
	 *
	 * Enumerated values of the ways to track goal progress.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public enum ProgressTracker {
		TIME, OBJECTIVE, MANUAL
	}

}