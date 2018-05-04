package com.tracktacular.service.tracker.wish;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.TrackerDemo;


/**
 * WishTrackerFacadeImpl
 *
 * The standard implementation of the WishTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class WishTrackerFacadeImpl extends AbstractTrackerFacade
	implements WishTrackerFacade {

	private ItemService itemService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(itemService);
	}

	@Override
	public Item getItem(User user, String uid) {
		return itemService.findFirstByUser(user, uid);
	}

	@Override
	public void saveItem(Item item) {
		validateItem(item);
		itemService.save(item);
	}

	@Override
	public void saveItems(List<Item> items) {
		for(Item item : items)
			validateItem(item);

		itemService.save(items);
	}

	/**
	 * Validates a Item.
	 *
	 * @param item
	 */
	private void validateItem(Item item) {
		new ItemValidator("item", item).validate();
	}

	@Override
	public void deleteItem(Item item) {
		itemService.delete(item);
	}

	@Override
	public Items findActiveItems(User user) {
		List<Item> activeItems = itemService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Item>of(Results.ALL));

		return new Items(activeItems);
	}

	@Override
	public List<Item> findCompletedItems(User user, CriteriaParams<Item> criteriaParams) {
		return itemService.findByUser(user, PersistableState.ARCHIVED, criteriaParams);
	}

	@Override
	public long findCompletedItemCount(User user) {
		return itemService.findCountByUser(user, PersistableState.ARCHIVED);
	}

	@Override
	public List<Item> findDeletedItems(User user, CriteriaParams<Item> criteriaParams) {
		return itemService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedItemCount(User user) {
		return itemService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Item> items = itemService.findByUser(user, CriteriaParams.<Item>of(Results.ALL));

		for(Item item : items)
			deleteItem(item);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new WishTrackerDemo(this);
	}

	@Override
	public WishTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Items items = findActiveItems(user);
		return new WishTrackerReport(items);
	}

	// getters and setters
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

}