package com.tracktacular.service.tracker.bucket;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * BucketTrackerFacade
 *
 * Provides functionality for item tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BucketTrackerFacade extends TrackerFacade {

	/**
	 * Gets a item by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Item
	 */
	public Item getItem(User user, String uid);

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
	 * Finds a List of active Items for a User.
	 *
	 * @param user
	 * @return Items
	 */
	public Items findActiveItems(User user);

	/**
	 * Finds a List of deleted Items for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Item>
	 */
	public List<Item> findDeletedItems(User user, CriteriaParams<Item> criteriaParams);

	/**
	 * Finds the count of all deleted Items for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedItemCount(User user);

}