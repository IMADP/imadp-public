package com.tracktacular.service.tracker.budget;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * BudgetTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BudgetTrackerDemo extends AbstractTrackerDemo {
	private BudgetTrackerFacade trackerFacade;

	// constructor
	public BudgetTrackerDemo(BudgetTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		insertPersonalBudget(user);
		insertComputerBudget(user);
	}

	/**
	 * Insert the personal budget data.
	 *
	 * @param user
	 */
	private void insertPersonalBudget(User user) {
		Budget budget = new Budget(user, "Personal Budget", "Monthly Finances Breakdown");
		budget.setStartDate(new DateTime().withDayOfMonth(23));

		trackerFacade.saveBudget(budget);

		ItemCategory incomeCategory = new ItemCategory(user, budget, "Income");
		incomeCategory.setSort(1);

		trackerFacade.saveItemCategory(incomeCategory);
		trackerFacade.saveItem(new Item(user, incomeCategory, "Work", new BigDecimal("1400")));
		trackerFacade.saveItem(new Item(user, incomeCategory, "Side Jobs", new BigDecimal("200")));

		ItemCategory expenseCategory = new ItemCategory(user, budget, "Expenses");
		expenseCategory.setSort(2);

		trackerFacade.saveItemCategory(expenseCategory);

		ItemCategory category = new ItemCategory(user, budget, expenseCategory, "Bills");

		trackerFacade.saveItemCategory(category);
		trackerFacade.saveItem(new Item(user, category, "Electricity", new BigDecimal("-130.45")));
		trackerFacade.saveItem(new Item(user, category, "Water", new BigDecimal("-89.34")));
		trackerFacade.saveItem(new Item(user, category, "Phone", new BigDecimal("-47.76")));

		category = new ItemCategory(user, budget, expenseCategory, "Car");

		trackerFacade.saveItemCategory(category);
		trackerFacade.saveItem(new Item(user, category, "Gas", new BigDecimal("-200")));
		trackerFacade.saveItem(new Item(user, category, "Insurance", new BigDecimal("-145")));
		trackerFacade.saveItem(new Item(user, category, "Tolls", new BigDecimal("-35")));

		category = new ItemCategory(user, budget, expenseCategory, "Groceries");

		trackerFacade.saveItemCategory(category);
		trackerFacade.saveItem(new Item(user, category, "Food", new BigDecimal("-300")));
		trackerFacade.saveItem(new Item(user, category, "Cats", new BigDecimal("-75")));

		category = new ItemCategory(user, budget, expenseCategory, "Entertainment");

		trackerFacade.saveItemCategory(category);
		trackerFacade.saveItem(new Item(user, category, "Dinners", new BigDecimal("-250")));
		trackerFacade.saveItem(new Item(user, category, "Movies", new BigDecimal("-40")));
	}

	/**
	 * Insert the computer budget data.
	 *
	 * @param user
	 */
	private void insertComputerBudget(User user) {
		Budget budget = new Budget(user, "Desktop Budget", "Building a new home PC");
		budget.setPersistableState(PersistableState.ARCHIVED);

		trackerFacade.saveBudget(budget);

		ItemCategory displayCategory = new ItemCategory(user, budget, "Display");
		displayCategory.setSort(1);

		trackerFacade.saveItemCategory(displayCategory);
		trackerFacade.saveItem(new Item(user, displayCategory, "Monitor", new BigDecimal("-300")));
		trackerFacade.saveItem(new Item(user, displayCategory, "Graphics Card", new BigDecimal("-250")));

		ItemCategory inputCategory = new ItemCategory(user, budget, "Input");
		inputCategory.setSort(1);

		trackerFacade.saveItemCategory(inputCategory);
		trackerFacade.saveItem(new Item(user, inputCategory, "Mouse", new BigDecimal("-50")));
		trackerFacade.saveItem(new Item(user, inputCategory, "Keyboard", new BigDecimal("-100")));

		ItemCategory storageCategory = new ItemCategory(user, budget, "Storage");
		storageCategory.setSort(1);

		trackerFacade.saveItemCategory(storageCategory);
		trackerFacade.saveItem(new Item(user, storageCategory, "Hard Drive", new BigDecimal("-200")));
		trackerFacade.saveItem(new Item(user, storageCategory, "Thumb Drive", new BigDecimal("-20")));

		ItemCategory coreCategory = new ItemCategory(user, budget, "Core");
		coreCategory.setSort(1);

		trackerFacade.saveItemCategory(coreCategory);
		trackerFacade.saveItem(new Item(user, coreCategory, "CPU", new BigDecimal("-230")));
		trackerFacade.saveItem(new Item(user, coreCategory, "Power Supply", new BigDecimal("-75")));
		trackerFacade.saveItem(new Item(user, coreCategory, "Motherboard", new BigDecimal("-160")));
		trackerFacade.saveItem(new Item(user, coreCategory, "Ram", new BigDecimal("-130")));
	}

}
