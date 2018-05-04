package com.tracktacular.service.tracker.budget;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * BudgetServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BudgetServiceImplTest extends TracktacularServiceTestCase {
	Budget budget;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		budget = new Budget(user);
		budget.setName("name");
		budget.setStartDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(budget, budget_budgetService);
	}

	@Test
	public void categoryRetrieval() {
		budget_budgetService.save(budget);

		ItemCategory itemCategory = new ItemCategory(user, budget);
		itemCategory.setUser(user);
		itemCategory.setName("name");

		ItemCategory subItemCategory = new ItemCategory(user, budget, itemCategory);
		subItemCategory.setUser(user);
		subItemCategory.setName("subName");

		budget_budgetService.saveItemCategory(itemCategory);
		budget_budgetService.saveItemCategory(subItemCategory);

		budget = budget_budgetService.get(budget.getId());

		assertEquals(1, budget.getItemCategories().size());
	}

}