package com.tracktacular.service.tracker.budget;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.category.CategoryService;
import com.imadp.service.user.User;

/**
 * IItemCategoryService
 *
 * Provides common retrieval operations for ItemCategory objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ItemCategoryService extends CategoryService<ItemCategory> {

	/**
	 * Saves a item.
	 *
	 * @param item
	 */
	public void saveItem(Item item);

	/**
	 * Deletes a item.
	 *
	 * @param item
	 */
	public void deleteItem(Item item);

	/**
	 * Returns a List of ItemCategories found by user, budget, name, parentCategory and criteriaParams.
	 *
	 * @param user
	 * @param budget
	 * @param name
	 * @param parentCategory
	 * @param criteriaParams
	 * @return List<ItemCategory>
	 */
	public List<ItemCategory> findByUser(User user, Budget budget, String name,
			ItemCategory parentCategory, CriteriaParams<ItemCategory> criteriaParams);

}