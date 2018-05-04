package com.tracktacular.service.tracker.journal;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;

/**
 * IEntryService
 *
 * Provides common retrieval operations for Entry objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface EntryService extends PersistableUserService<Entry> {

	/**
	 * Finds a List of Entries by user, journal, and criteriaParams.
	 *
	 * @param user
	 * @param journal
	 * @param criteriaParams
	 * @return List<Entry>
	 */
	public List<Entry> findByUser(User user, Journal journal, CriteriaParams<Entry> criteriaParams);

	/**
	 * Finds the count of Entries by user, and journal.
	 *
	 * @param user
	 * @param journal
	 * @return long
	 */
	public long findCountByUser(User user, Journal journal);

}