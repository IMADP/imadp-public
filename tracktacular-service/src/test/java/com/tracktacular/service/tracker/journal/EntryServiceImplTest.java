package com.tracktacular.service.tracker.journal;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * EntryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EntryServiceImplTest extends TracktacularServiceTestCase {
	Entry entry;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();
		Journal journal = new Journal();
		journal.setName("name");
		journal.setUser(user);

		entry = new Entry();
		entry.setTitle("title");
		entry.setContent("content");
		entry.setJournal(journal);
		entry.setDate(new DateTime());
		entry.setUser(user);

		userService.save(user);
		journal_journalService.save(journal);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(entry, journal_entryService);
	}

}
