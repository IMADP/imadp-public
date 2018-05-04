package com.tracktacular.service.tracker.budget;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.category.CategoryServiceImpl;
import com.imadp.service.user.User;


/**
 * ItemCategoryServiceImpl
 *
 * The standard implementation of the ItemCategoryService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ItemCategoryServiceImpl extends CategoryServiceImpl<ItemCategory>
	implements ItemCategoryService {

	private ItemService itemService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(itemService);
	}

	@Override
	protected void onBeforeDelete(ItemCategory category) {
		super.onBeforeDelete(category);

		// delete all items
		if(category.hasItems())
			for(Item item : category.getItems())
				deleteItem(item);

		// delete all sub categories
		if(category.hasChildCategories())
			for(ItemCategory itemCategory : category.getChildCategories())
				delete(itemCategory);
	}

	@Override
	public List<ItemCategory> findByUser(User user, Budget budget, String name, ItemCategory parentCategory,
			CriteriaParams<ItemCategory> criteriaParams) {

		FindCriteria<ItemCategory> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(ItemCategory.BUDGET, budget)
			.whereEqualTo(ItemCategory.NAME, name)
			.whereEqualTo(ItemCategory.USER, user)
			.whereEqualTo(ItemCategory.PARENT_CATEGORY, parentCategory).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public void saveItem(Item item) {
		itemService.save(item);

		clearUserCaches(item.getUser());
	}

	@Override
	public void deleteItem(Item item) {
		itemService.delete(item);

		clearUserCaches(item.getUser());
	}

	// getters and setters
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

}