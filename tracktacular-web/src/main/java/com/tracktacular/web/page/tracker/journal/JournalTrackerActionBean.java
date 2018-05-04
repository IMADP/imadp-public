package com.tracktacular.web.page.tracker.journal;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.journal.Entry;
import com.tracktacular.service.tracker.journal.Journal;
import com.tracktacular.service.tracker.journal.JournalTrackerFacade;
import com.tracktacular.service.tracker.journal.JournalTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * JournalTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class JournalTrackerActionBean extends TrackerActionBean<JournalTrackerFacade, JournalTrackerPreferences> {
	private Journal journal;
	private Entry entry;

	@Override
	public Tracker getTracker() {
		return Tracker.JOURNAL;
	}

	/**
	 * Save or updates a Journal.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveJournal() {
		Journal journal = getJournal();
		populatePersistableUser(journal);
		getTrackerFacade().saveJournal(journal);

		if(journal.isActiveState())
			getContext().addSuccessMessage("journal.saveJournal.success", journal.getName());

		else if(journal.isArchivedState())
			getContext().addSuccessMessage("journal.completeJournal.success", journal.getName());

		else if(journal.isDeletedState())
			getContext().addSuccessMessage("journal.deleteJournal.success", journal.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Journal.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteJournal() {
		Journal journal = getJournal();
		getTrackerFacade().deleteJournal(journal);
		getContext().addSuccessMessage("journal.deleteJournal.success", journal.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Entry.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveEntry() {
		Entry entry = getEntry();
		populatePersistableUser(entry);
		getTrackerFacade().saveEntry(entry);
		getContext().addSuccessMessage("journal.saveEntry.success");
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Entry.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteEntry() {
		Entry entry = getEntry();
		getTrackerFacade().deleteEntry(entry);
		getContext().addSuccessMessage("journal.deleteEntry.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public JournalTrackerFacade getJournalTrackerFacade() {
		return getTrackerFacade();
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}