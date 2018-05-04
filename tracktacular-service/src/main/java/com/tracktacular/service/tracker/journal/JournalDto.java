package com.tracktacular.service.tracker.journal;

import java.util.List;

import com.imadp.core.AbstractSerializable;

/**
 * JournalDto
 *
 * A data transfer object containing a journal and corresponding data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class JournalDto extends AbstractSerializable {
	private final Journal journal;
	private final List<Entry> recentEntries;
	private final Entry lastEntry;
    private final int recentEntryCount;
    private final long entryCount;

    // constructor
	public JournalDto(Journal journal, List<Entry> recentEntries, long entryCount) {
		this.journal = journal;
		this.recentEntries = recentEntries;
	    this.recentEntryCount = recentEntries.size();
	    this.entryCount = entryCount;
	    this.lastEntry = recentEntries.isEmpty() ? null : recentEntries.get(0);
	}

	// getters and setters
	public Journal getJournal() {
		return journal;
	}

	public long getEntryCount() {
		return entryCount;
	}

	public List<Entry> getRecentEntries() {
		return recentEntries;
	}

	public int getRecentEntryCount() {
		return recentEntryCount;
	}

	public Entry getLastEntry() {
		return lastEntry;
	}

}