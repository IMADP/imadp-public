package com.tracktacular.service.tracker.gift;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * GiftTrackerFacade
 *
 * Provides functionality for gift tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface GiftTrackerFacade extends TrackerFacade {

	/**
	 * Gets a gift by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Gift
	 */
	public Gift getGift(User user, String uid);

	/**
	 * Saves a gift.
	 *
	 * @param gift
	 */
	public void saveGift(Gift gift);

	/**
	 * Deletes a gift.
	 *
	 * @param gift
	 */
	public void deleteGift(Gift gift);

	/**
	 * Finds a List of active sent Gifts for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Gifts
	 */
	public Gifts findGivenGifts(User user, String sortProperty);

	/**
	 * Finds a List of active received Gifts for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Gifts
	 */
	public Gifts findReceivedGifts(User user, String sortProperty);

	/**
	 * Finds a List of deleted Gifts for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Gift>
	 */
	public List<Gift> findDeletedGifts(User user, CriteriaParams<Gift> criteriaParams);

	/**
	 * Finds the count of all deleted Gifts for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedGiftCount(User user);

}