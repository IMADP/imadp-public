package com.tracktacular.service.tracker.budget;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * ITracktacularBudgetTrackerFacade
 *
 * Provides functionality for budget tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BudgetTrackerFacade extends TrackerFacade {

	/**
	 * Gets a budget by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Budget
	 */
	public Budget getBudget(User user, String uid);

	/**
	 * Saves a budget.
	 *
	 * @param budget
	 */
	public void saveBudget(Budget budget);

	/**
	 * Deletes a budget.
	 *
	 * @param budget
	 */
	public void deleteBudget(Budget budget);

	/**
	 * Finds a List of active Budgets for a User.
	 *
	 * @param user
	 * @return List<Budget>
	 */
	public List<Budget> findActiveBudgets(User user);

	/**
	 * Finds the count of all active Budgets for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findActiveBudgetCount(User user);

	/**
	 * Finds a List of completed Budgets for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Budget>
	 */
	public List<Budget> findCompletedBudgets(User user, CriteriaParams<Budget> criteriaParams);

	/**
	 * Finds the count of all completed Budgets for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findCompletedBudgetCount(User user);

	/**
	 * Finds a List of deleted Budgets for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Budget>
	 */
	public List<Budget> findDeletedBudgets(User user, CriteriaParams<Budget> criteriaParams);

	/**
	 * Finds the count of all deleted Budgets for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedBudgetCount(User user);

	/**
	 * Gets a ItemCategory by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return ItemCategory
	 */
	public ItemCategory getItemCategory(User user, String uid);

	/**
	 * Saves an item category.
	 *
	 * @param category
	 */
	public void saveItemCategory(ItemCategory category);

	/**
	 * Saves a list of item categories.
	 *
	 * @param categories
	 */
	public void saveItemCategories(List<ItemCategory> categories);

	/**
	 * Deletes an item category.
	 *
	 * @param category
	 */
	public void deleteItemCategory(ItemCategory category);

	/**
	 * Gets a Item by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Item
	 */
	public Item getItem(User user, String uid);

	/**
	 * Saves an item.
	 *
	 * @param item
	 */
	public void saveItem(Item item);

	/**
	 * Saves a list of items.
	 *
	 * @param items
	 */
	public void saveItems(List<Item> items);

	/**
	 * Deletes an item.
	 *
	 * @param item
	 */
	public void deleteItem(Item item);

}