package com.tracktacular.service.tracker.wish;

import org.junit.Test;

import com.imadp.core.money.Money;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * ItemServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ItemServiceImplTest extends TracktacularServiceTestCase {
	Item item;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		item = new Item(user);
		item.setName("name");
		item.setNotes("notes");
		item.setUrl("url");
		item.setPrice(Money.ONE);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(item, wish_itemService);
	}

}