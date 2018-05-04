package com.tracktacular.web.page.tracker.wish;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.wish.Item;
import com.tracktacular.service.tracker.wish.WishTrackerFacade;
import com.tracktacular.service.tracker.wish.WishTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * WishTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class WishTrackerActionBean extends TrackerActionBean<WishTrackerFacade, WishTrackerPreferences> {
	private Item item;

	@Override
	public Tracker getTracker() {
		return Tracker.WISH;
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

		if(item.isActiveState())
			getContext().addSuccessMessage("wish.saveItem.success", item.getName());

		else if(item.isArchivedState())
			getContext().addSuccessMessage("wish.completeItem.success", item.getName());

		else if(item.isDeletedState())
			getContext().addSuccessMessage("wish.deleteItem.success", item.getName());

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
		getContext().addSuccessMessage("wish.deleteItem.success", item.getName());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

}