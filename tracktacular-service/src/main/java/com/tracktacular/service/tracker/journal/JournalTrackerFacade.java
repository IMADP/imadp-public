package com.tracktacular.service.tracker.journal;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * ITracktacularJournalTrackerFacade
 *
 * Provides functionality for task tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface JournalTrackerFacade extends TrackerFacade {

	/**
	 * Gets a journal by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Journal
	 */
	public Journal getJournal(User user, String uid);

	/**
	 * Finds a List of active JournalDtos for a User.
	 *
	 * @param user
	 * @param recentEntries
	 * @param criteriaParams
	 * @return List<JournalDto>
	 */
	public List<JournalDto> findActiveJournals(User user, int recentEntries, CriteriaParams<Journal> criteriaParams);

	/**
	 * Finds the count of all active Journals for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findActiveJournalCount(User user);

	/**
	 * Finds a List of completed JournalDtos for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<JournalDto>
	 */
	public List<JournalDto> findCompletedJournals(User user, CriteriaParams<Journal> criteriaParams);

	/**
	 * Finds the count of all completed Journals for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findCompletedJournalCount(User user);

	/**
	 * Finds a List of deleted JournalDtos for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<JournalDto>
	 */
	public List<JournalDto> findDeletedJournals(User user, CriteriaParams<Journal> criteriaParams);

	/**
	 * Finds the count of all deleted Journals for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedJournalCount(User user);

	/**
	 * Saves a Journal.
	 *
	 * @param journal
	 */
	public void saveJournal(Journal journal);

	/**
	 * Removes a Journal.
	 *
	 * @param journal
	 */
	public void deleteJournal(Journal journal);

	/**
	 * Gets a entry by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Entry
	 */
	public Entry getEntry(User user, String uid);

	/**
	 * Gets a list a Entries by user, Journal, and criteriaParams.
	 *
	 * @param user
	 * @param journal
	 * @param criteriaParams
	 * @return List<Entry>
	 */
	public List<Entry> findEntries(User user, Journal journal, CriteriaParams<Entry> criteriaParams);

	/**
	 * Finds the count of Entries by user, and journal.
	 *
	 * @param user
	 * @param journal
	 * @return long
	 */
	public long findEntriesCount(User user, Journal journal);

	/**
	 * Saves an Entry.
	 *
	 * @param entry
	 */
	public void saveEntry(Entry entry);

	/**
	 * Deletes an Entry.
	 *
	 * @param entry
	 */
	public void deleteEntry(Entry entry);

}