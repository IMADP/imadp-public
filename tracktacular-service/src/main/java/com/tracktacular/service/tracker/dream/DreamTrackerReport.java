package com.tracktacular.service.tracker.dream;

import java.util.Collections;
import java.util.List;

import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * DreamTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class DreamTrackerReport extends AbstractTrackerReport {
	private final long dreamCount;
    private final List<Long> dreamsMonthList;
    private final List<Integer> allDreamsByMonthList;
    private final List<Integer> favoriteDreamsByMonthList;
    private final List<Integer> lucidDreamsByMonthList;

    // constructor
    public DreamTrackerReport(
            long dreamCount,
            List<Long> dreamsMonthList,
            List<Integer> allDreamsByMonthList,
            List<Integer> favoriteDreamsByMonthList,
            List<Integer> lucidDreamsByMonthList) {
        this.dreamCount = dreamCount;
        this.dreamsMonthList = Collections.unmodifiableList(dreamsMonthList);
        this.allDreamsByMonthList = Collections.unmodifiableList(allDreamsByMonthList);
        this.favoriteDreamsByMonthList = Collections.unmodifiableList(favoriteDreamsByMonthList);
        this.lucidDreamsByMonthList = Collections.unmodifiableList(lucidDreamsByMonthList);
    }

    @Override
	public boolean isEnabled() {
    	return dreamCount > 0;
    }

    // getters

	public long getDreamCount() {
		return dreamCount;
	}

	public List<Long> getDreamsMonthList() {
		return dreamsMonthList;
	}

	public List<Integer> getAllDreamsByMonthList() {
		return allDreamsByMonthList;
	}

	public List<Integer> getFavoriteDreamsByMonthList() {
		return favoriteDreamsByMonthList;
	}

	public List<Integer> getLucidDreamsByMonthList() {
		return lucidDreamsByMonthList;
	}

}