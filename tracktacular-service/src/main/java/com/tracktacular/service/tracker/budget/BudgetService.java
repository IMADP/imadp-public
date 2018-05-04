package com.tracktacular.service.tracker.budget;

import java.util.List;

import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;

/**
 * BudgetService
 *
 * Provides common retrieval operations for Budget objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BudgetService extends PersistableUserService<Budget> {

	/**
	 * Gets a ItemCategory by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return ItemCategory
	 */
	public ItemCategory getItemCategory(User user, String uid);

	/**
	 * Returns true if the category name is already in use, false otherwise.
	 *
	 * @param category
	 * @return boolean
	 */
	public boolean isItemCategoryNameInUse(ItemCategory category);

	/**
	 * Saves an item category.
	 *
	 * @param category
	 */
	public void saveItemCategory(ItemCategory category);

	/**
	 * Saves a list of items.
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