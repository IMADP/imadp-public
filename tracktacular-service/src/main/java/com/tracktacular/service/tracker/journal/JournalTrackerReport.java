package com.tracktacular.service.tracker.journal;

import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * JournalTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class JournalTrackerReport extends AbstractTrackerReport {
	private final List<JournalDto> activeJournalsList;
	private final List<Journal> lateJournals;

    // constructor
    public JournalTrackerReport(List<JournalDto> activeJournalsList) {
        this.activeJournalsList = Collections.unmodifiableList(activeJournalsList);
    	this.lateJournals = Lists.newArrayList();

    	for(JournalDto journalDto : activeJournalsList)
		{
    		Journal journal = journalDto.getJournal();
			Entry lastEntry = journalDto.getLastEntry();

			// if there aren't any workouts or recurrence period, there are no alerts
			if(lastEntry == null || !journal.isAlertRecurring())
				continue;

			DateTime nextOccurrence = journal.getAlertRecurrence().getNextOccurrence(lastEntry.getDate());

			if(isDue(nextOccurrence))
				lateJournals.add(journal);
		}
    }

    @Override
    public boolean isEnabled() {
    	return !activeJournalsList.isEmpty();
    }

	@Override
	public int getAlertCount() {
		return lateJournals.size();
	}

    /**
     * Returns the count of all active journals.
     *
     * @return long
     */
    public long getActiveJournalsCount() {
		return activeJournalsList.size();
	}

	// getters
    public List<JournalDto> getActiveJournalsList() {
		return activeJournalsList;
	}

	public List<Journal> getLateJournals() {
		return lateJournals;
	}

}