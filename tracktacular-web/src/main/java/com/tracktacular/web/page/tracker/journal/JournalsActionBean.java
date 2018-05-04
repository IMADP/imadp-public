package com.tracktacular.web.page.tracker.journal;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.tracktacular.service.tracker.journal.Journal;
import com.tracktacular.service.tracker.journal.JournalDto;


/**
 * JournalsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-journal/journals")
public class JournalsActionBean extends JournalTrackerActionBean {
	private static final int RECENT_ENTRIES_PER_JOURNAL = 5;
	private List<JournalDto> activeJournals;

	/**
	 * Returns the count of active journals.
	 *
	 * @return long
	 */
	public long getJournalsEntryCount() {
		long count = 0;

		for(JournalDto journalDto : getJournals())
			count+= journalDto.getEntryCount();

		return count;
	}

	/**
	 * Returns a list of active journals.
	 *
	 * @return List<JournalDto>
	 */
	public List<JournalDto> getJournals() {
		if(activeJournals == null)
			activeJournals = getTrackerFacade().findActiveJournals(
					getTrackerUser(), RECENT_ENTRIES_PER_JOURNAL, CriteriaParams.<Journal>of(Results.ALL));

		return activeJournals;
	}

}