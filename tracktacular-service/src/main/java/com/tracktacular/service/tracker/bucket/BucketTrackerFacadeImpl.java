package com.tracktacular.service.tracker.bucket;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.TrackerReport;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * BucketTrackerFacadeImpl
 *
 * The standard implementation of the BucketTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BucketTrackerFacadeImpl extends AbstractTrackerFacade
	implements BucketTrackerFacade {

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
		new ItemValidator("item", item).validate();
		itemService.save(item);
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
		return new BucketTrackerDemo(this);
	}

	@Override
	public TrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new BucketTrackerReport(findActiveItems(user));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active items
		Items items = findActiveItems(user);

		for(Item item : items.getItems())
		{
			if(item.isCompletedWithinInterval(interval))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, item.getCompletedDate());
				calendarEntry.setTracker(Tracker.BUCKET);
				calendarEntry.setName("Bucket list item completed");
				calendarEntry.setDescription(item.getName());
				calendarEntries.add(calendarEntry);
			}
		}

		return calendarEntries;
	}

	// getters and setters
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

}