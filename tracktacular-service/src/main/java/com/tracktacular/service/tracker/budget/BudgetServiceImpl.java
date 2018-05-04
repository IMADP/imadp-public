package com.tracktacular.service.tracker.budget;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * BudgetServiceImpl
 *
 * The standard implementation of the BudgetService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BudgetServiceImpl extends PersistableUserServiceImpl<Budget> implements BudgetService {
	private ItemCategoryService itemCategoryService;
	private ItemService itemService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(itemCategoryService);
		Validate.notNull(itemService);
	}

	@Override
	protected void onBeforeDelete(Budget budget) {
		super.onBeforeDelete(budget);

		if(budget.hasItemCategories())
			for(ItemCategory itemCategory : budget.getItemCategories())
				deleteItemCategory(itemCategory);
	}

	@Override
	public ItemCategory getItemCategory(User user, String uid) {
		return itemCategoryService.findFirstByUser(user, uid);
	}

	@Override
	public boolean isItemCategoryNameInUse(ItemCategory category) {
		if(!category.hasUser())
			throw new IllegalArgumentException("ItemCategory must have a user to determine if category name is in use");

		// find all categories with the same budget, name, parent, and user
		List<ItemCategory> categories = itemCategoryService.findByUser(category.getUser(), category.getBudget(),
				category.getName(),	category.getParentCategory(), CriteriaParams.<ItemCategory>of(Results.ONE));

		// if a category was found that does not match the one we are validating, we have a duplicate
		return !categories.isEmpty() && !category.equals(categories.get(0));
	}

	@Override
	public void saveItemCategory(ItemCategory category) {
		itemCategoryService.save(category);

		clearUserCaches(category.getUser());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveItemCategories(List<ItemCategory> categories) {
		for(ItemCategory itemCategory : categories)
			saveItemCategory(itemCategory);
	}

	@Override
	public void deleteItemCategory(ItemCategory category) {
		itemCategoryService.delete(category);

		clearUserCaches(category.getUser());
	}

	@Override
	public Item getItem(User user, String uid) {
		return itemService.findFirstByUser(user, uid);
	}

	@Override
	public void saveItem(Item item) {
		itemCategoryService.saveItem(item);

		clearUserCaches(item.getUser());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveItems(List<Item> items) {
		for(Item item : items)
			saveItem(item);
	}

	@Override
	public void deleteItem(Item item) {
		itemCategoryService.deleteItem(item);

		clearUserCaches(item.getUser());
	}

	// getters and setters
	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

}