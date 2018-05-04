package com.tracktacular.service.tracker.budget;

import java.math.BigDecimal;

import org.junit.Test;

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
	Budget budget;
	ItemCategory itemCategory;
	Item item;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		budget = new Budget(user);
		budget.setName("name");

		itemCategory = new ItemCategory(user, budget);
		itemCategory.setUser(user);
		itemCategory.setName("name");

		item = new Item(user, itemCategory);
		item.setName("name");
		item.setAmount(BigDecimal.TEN);

		userService.save(user);
		budget_budgetService.save(budget);
		budget_budgetService.saveItemCategory(itemCategory);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(item, budget_itemService);
	}

}