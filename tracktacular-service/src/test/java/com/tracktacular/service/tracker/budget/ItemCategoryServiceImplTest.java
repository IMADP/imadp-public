package com.tracktacular.service.tracker.budget;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * ItemCategoryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ItemCategoryServiceImplTest extends TracktacularServiceTestCase {
	Budget budget;
	ItemCategory itemCategory;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		budget = new Budget(user);
		budget.setName("name");

		itemCategory = new ItemCategory(user, budget);
		itemCategory.setUser(user);
		itemCategory.setName("name");

		userService.save(user);
		budget_budgetService.save(budget);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(itemCategory, budget_itemCategoryService);
	}

}