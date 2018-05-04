package com.tracktacular.service.tracker.journal;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * JournalServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class JournalServiceImplTest extends TracktacularServiceTestCase {
	Journal journal;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		User user = new User();
		
		journal = new Journal();
		journal.setName("name");
		journal.setUser(user);
		
		userService.save(user);
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(journal, journal_journalService);
	}	

}
