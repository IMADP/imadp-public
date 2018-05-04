package com.tracktacular.web.page.tracker.budget;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.budget.Item;
import com.tracktacular.service.tracker.budget.ItemCategory;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;


/**
 * BudgetActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-budget/budget-{budget=null}/{nameSlug=null}")
public class BudgetActionBean extends BudgetTrackerActionBean {
	private String sortedCategories;
	private String sortedItems;

	@Override
	public Resolution index() {
		if(getBudget() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return super.index();
	}

	@Override
	public String getTrackerPageTitle() {
		return getBudget().getName();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

	/**
	 * Sorts Categories.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortItemCategories() {
		List<ItemCategory> sortedCategoriesList = convertObjectList(sortedCategories, ItemCategory.class);
		List<ItemCategory> itemCategories = getResortedList(sortedCategoriesList);
		getTrackerFacade().saveItemCategories(itemCategories);
		getContext().addSuccessMessage("budget.sortItemCategories.success");
		resetBudget();
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Items.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortItems() {
		List<Item> sortedItemsList = convertObjectList(sortedItems, Item.class);
		List<Item> items = getResortedList(sortedItemsList);
		getTrackerFacade().saveItems(items);
		getContext().addSuccessMessage("budget.sortItems.success");
		resetBudget();
		return getAjaxSourceResolution();
	}

	// getters and setters
	public String getSortedCategories() {
		return sortedCategories;
	}

	public void setSortedCategories(String sortedCategories) {
		this.sortedCategories = sortedCategories;
	}

	public String getSortedItems() {
		return sortedItems;
	}

	public void setSortedItems(String sortedItems) {
		this.sortedItems = sortedItems;
	}

}