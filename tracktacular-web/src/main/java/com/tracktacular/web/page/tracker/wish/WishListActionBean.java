package com.tracktacular.web.page.tracker.wish;

import java.util.List;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.wish.Item;
import com.tracktacular.service.tracker.wish.Items;
import com.tracktacular.web.IgnoreInPublicMode;


/**
 * WishListActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-wish/wish-list")
public class WishListActionBean extends WishTrackerActionBean {
	private Items items;
	private String sortedItems;

	/**
	 * Returns a list of items.
	 *
	 * @return Items
	 */
	public Items getItems() {
		if(items == null)
			items = getTrackerFacade().findActiveItems(getTrackerUser());

		return items;
	}

	/**
	 * Sorts Items.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortItems() {
		List<Item> sortedCategoriesList = convertObjectList(sortedItems, Item.class);
		List<Item> sortedList = getResortedList(sortedCategoriesList);
		getTrackerFacade().saveItems(sortedList);
		getContext().addSuccessMessage("wish.sortItems.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public String getSortedItems() {
		return sortedItems;
	}

	public void setSortedItems(String sortedItems) {
		this.sortedItems = sortedItems;
	}

}