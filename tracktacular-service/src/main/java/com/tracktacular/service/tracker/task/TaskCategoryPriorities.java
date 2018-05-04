package com.tracktacular.service.tracker.task;

import java.io.Serializable;


/**
 * TaskPriorities
 *
 * A count of priorities for a given task.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskCategoryPriorities implements Serializable {
	protected int low;
	protected int medium;
	protected int high;

	// constructor
	public TaskCategoryPriorities() {

	}

	/**
	 * Adds the priority values from the given priorities to this one
	 * and returns a new object with the result.
	 *
	 * @param priorities
	 */
	public void add(TaskCategoryPriorities priorities) {
		this.low += priorities.low;
		this.medium += priorities.medium;
		this.high += priorities.high;
	}

	/**
	 * Returns the total count of all tasks.
	 *
	 * @return int
	 */
	public int getTotal() {
		return low + medium + high;
	}

	// getters and setters
	public int getLow() {
		return low;
	}

	public int getMedium() {
		return medium;
	}

	public int getHigh() {
		return high;
	}

}