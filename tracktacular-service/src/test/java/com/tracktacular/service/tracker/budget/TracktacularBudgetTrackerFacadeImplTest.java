package com.tracktacular.service.tracker.budget;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * TracktacularBudgetTrackerFacadeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularBudgetTrackerFacadeImplTest extends TracktacularServiceTestCase {
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		Budget budget = new Budget(user);
		budget.setName("name");

		ItemCategory itemCategory = new ItemCategory(user, budget);
		itemCategory.setUser(user);
		itemCategory.setName("name");

		Item item = new Item(user, itemCategory);
		item.setName("name");
		item.setAmount(BigDecimal.TEN);

		userService.save(user);
		budget_budgetService.save(budget);
		budget_budgetService.saveItemCategory(itemCategory);
		budget_budgetService.saveItem(item);
	}

	@Test
	public void deleteAll() {
		assertEquals(1, budgetTrackerFacade.findActiveBudgetCount(user));

		budgetTrackerFacade.deleteAll(user);

		assertEquals(0, budgetTrackerFacade.findActiveBudgetCount(user));
		assertEquals(0, budgetTrackerFacade.findCompletedBudgetCount(user));
		assertEquals(0, budgetTrackerFacade.findDeletedBudgetCount(user));
	}

}
