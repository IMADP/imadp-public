package com.tracktacular.web.page.tracker.budget;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.budget.Budget;
import com.tracktacular.service.tracker.budget.BudgetTrackerFacade;
import com.tracktacular.service.tracker.budget.BudgetTrackerPreferences;
import com.tracktacular.service.tracker.budget.Item;
import com.tracktacular.service.tracker.budget.ItemCategory;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * BudgetTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class BudgetTrackerActionBean extends TrackerActionBean<BudgetTrackerFacade, BudgetTrackerPreferences> {
	private Budget budget;
	private Item item;
	private ItemCategory itemCategory;

	@Override
	public Tracker getTracker() {
		return Tracker.BUDGET;
	}

	/**
	 * Save or updates a Budget.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveBudget() {
		Budget budget = getBudget();
		populatePersistableUser(budget);
		getTrackerFacade().saveBudget(budget);

		if(budget.isActiveState())
			getContext().addSuccessMessage("budget.saveBudget.success", budget.getName());

		else if(budget.isArchivedState())
			getContext().addSuccessMessage("budget.completeBudget.success", budget.getName());

		else if(budget.isDeletedState())
			getContext().addSuccessMessage("budget.deleteBudget.success", budget.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Budget.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteBudget() {
		Budget budget = getBudget();
		getTrackerFacade().deleteBudget(budget);
		getContext().addSuccessMessage("budget.deleteBudget.success", budget.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the Category collapsed property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleItemCategoryCollapse() {
		ItemCategory itemCategory = getItemCategory();
		itemCategory.setCollapsed(!itemCategory.isCollapsed());
		getTrackerFacade().saveItemCategory(itemCategory);
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Category.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveItemCategory() {
		ItemCategory itemCategory = getItemCategory();
		populatePersistableUser(itemCategory);
		getTrackerFacade().saveItemCategory(itemCategory);
		getContext().addSuccessMessage("budget.saveItemCategory.success", itemCategory.getName());
		resetBudget();
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Category.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteItemCategory() {
		ItemCategory itemCategory = getItemCategory();
		getTrackerFacade().deleteItemCategory(itemCategory);
		getContext().addSuccessMessage("budget.deleteItemCategory.success", itemCategory.getName());
		resetBudget();
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Item.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveItem() {
		Item item = getItem();
		populatePersistableUser(item);
		getTrackerFacade().saveItem(item);
		getContext().addSuccessMessage("budget.saveItem.success", item.getName());
		resetBudget();
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Item.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteItem() {
		Item item = getItem();
		getTrackerFacade().deleteItem(item);
		getContext().addSuccessMessage("budget.deleteItem.success", item.getName());
		resetBudget();
		return getAjaxSourceResolution();
	}

	/**
	 * Resets the budget by retrieving it from the facade.
	 *
	 */
	protected final void resetBudget() {
		setBudget(getTrackerFacade().getBudget(getContext().getUser(), budget.getUid()));
	}

	// getters and setters
	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Budget getBudget() {
		return budget;
	}

	public Item getItem() {
		return item;
	}

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

}