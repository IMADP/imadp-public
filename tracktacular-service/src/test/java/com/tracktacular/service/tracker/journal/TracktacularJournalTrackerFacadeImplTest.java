package com.tracktacular.service.tracker.journal;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * TracktacularGoalTrackerFacadeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularJournalTrackerFacadeImplTest extends TracktacularServiceTestCase {
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();
		Journal journal = new Journal();
		journal.setName("name");
		journal.setUser(user);

		Entry entry = new Entry();
		entry.setTitle("title");
		entry.setContent("content");
		entry.setJournal(journal);
		entry.setDate(new DateTime());
		entry.setUser(user);

		userService.save(user);
		journal_journalService.save(journal);
		journal_entryService.save(entry);
	}

	@Test
	public void deleteAll() {
		assertEquals(1, journalTrackerFacade.findActiveJournalCount(user));

		journalTrackerFacade.deleteAll(user);

		assertEquals(0, journalTrackerFacade.findActiveJournalCount(user));
		assertEquals(0, journalTrackerFacade.findCompletedJournalCount(user));
		assertEquals(0, journalTrackerFacade.findDeletedJournalCount(user));
	}

}
