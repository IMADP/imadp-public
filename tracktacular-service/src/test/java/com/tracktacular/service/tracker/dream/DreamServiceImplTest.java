package com.tracktacular.service.tracker.dream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.dao.PersistableState;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * DreamServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DreamServiceImplTest extends TracktacularServiceTestCase {
	Dream dream;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		dream = new Dream();
		dream.setTitle("title");
		dream.setLucid(true);
		dream.setContent("content");
		dream.setUser(user);
		dream.setDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(dream, dream_dreamService);
	}

	@Test
	public void dreamsigns() {
		dream.addDreamsign("1");
		dream.addDreamsign("2");
		dream.addDreamsign("3");

		assertEquals(3, dream.getDreamsigns().size());

		dream_dreamService.save(dream);

		assertEquals(3, dream.getDreamsigns().size());

		dream = dream_dreamService.get(dream.getId());

		assertEquals(3, dream.getDreamsigns().size());

		dream.setPersistableState(PersistableState.DELETED);

		dream_dreamService.save(dream);

		dream = dream_dreamService.get(dream.getId());

		assertEquals(3, dream.getDreamsigns().size());
		assertEquals(PersistableState.DELETED, dream.getDreamsigns().iterator().next().getPersistableState());

		dream = dream_dreamService.get(dream.getId());

		dream_dreamService.delete(dream);

		dream = dream_dreamService.get(dream.getId());

		assertNull(dream);
	}

}