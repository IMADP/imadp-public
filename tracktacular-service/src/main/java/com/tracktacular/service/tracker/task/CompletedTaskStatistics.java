package com.tracktacular.service.tracker.task;

import java.util.Collections;
import java.util.List;

import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * TaskTrackerReportCompleted
 *
 * Contains report segment information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class CompletedTaskStatistics extends AbstractTrackerReport {
	private final long completedTasksCount;
    private final List<Long> tasksMonthList;
    private final List<Integer> lowPriorityTasksByMonthList;
    private final List<Integer> mediumPriorityTasksByMonthList;
    private final List<Integer> highPriorityTasksByMonthList;

    // constructor
    public CompletedTaskStatistics(
            long completedTasksCount,
            List<Long> tasksMonthList,
            List<Integer> lowPriorityTasksByMonthList,
            List<Integer> mediumPriorityTasksByMonthList,
            List<Integer> highPriorityTasksByMonthList) {
        this.completedTasksCount = completedTasksCount;
        this.tasksMonthList = Collections.unmodifiableList(tasksMonthList);
        this.lowPriorityTasksByMonthList = Collections.unmodifiableList(lowPriorityTasksByMonthList);
        this.mediumPriorityTasksByMonthList = Collections.unmodifiableList(mediumPriorityTasksByMonthList);
        this.highPriorityTasksByMonthList = Collections.unmodifiableList(highPriorityTasksByMonthList);
    }

    @Override
	public boolean isEnabled() {
    	return completedTasksCount > 0;
    }

    // getters
	public long getCompletedTasksCount() {
		return completedTasksCount;
	}

	public List<Long> getTasksMonthList() {
		return tasksMonthList;
	}

	public List<Integer> getLowPriorityTasksByMonthList() {
		return lowPriorityTasksByMonthList;
	}

	public List<Integer> getMediumPriorityTasksByMonthList() {
		return mediumPriorityTasksByMonthList;
	}

	public List<Integer> getHighPriorityTasksByMonthList() {
		return highPriorityTasksByMonthList;
	}

}