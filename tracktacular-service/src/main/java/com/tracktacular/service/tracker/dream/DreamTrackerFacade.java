package com.tracktacular.service.tracker.dream;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.tag.TagCloud;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * DreamTrackerFacade
 *
 * Provides functionality for task tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface DreamTrackerFacade extends TrackerFacade {

	/**
	 * Gets a dream by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Dream
	 */
	public Dream getDream(User user, String uid);

	/**
	 * Saves a Dream.
	 *
	 * @param dream
	 */
	public void saveDream(Dream dream);

	/**
	 * Removes a Dream.
	 *
	 * @param dream
	 */
	public void deleteDream(Dream dream);

	/**
	 * Finds a List of active Dreams for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Dream>
	 */
	public List<Dream> findDreams(User user, CriteriaParams<Dream> criteriaParams);

	/**
	 * Finds the count of all active Dreams for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDreamCount(User user);

	/**
	 * Finds a List of active lucid Dreams for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Dream>
	 */
	public List<Dream> findLucidDreams(User user, CriteriaParams<Dream> criteriaParams);

	/**
	 * Finds the count of all active lucid Dreams for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findLucidDreamCount(User user);

	/**
	 * Finds a List of active favorite Dreams for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Dream>
	 */
	public List<Dream> findFavoriteDreams(User user, CriteriaParams<Dream> criteriaParams);

	/**
	 * Finds the count of all active favorite Dreams for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findFavoriteDreamCount(User user);

	/**
	 * Finds a List of Dreams for a dreamsign and user.
	 *
	 * @param user
	 * @param name
	 * @param criteriaParams
	 * @return List<Dreamsign>
	 */
	public List<Dreamsign> findDreamsigns(User user, String name, CriteriaParams<Dreamsign> criteriaParams);

	/**
	 * Finds the count of all active Dreams for a user
	 *
	 * @param user
	 * @param name
	 * @return long
	 */
	public long findDreamsignCount(User user, String name);

	/**
	 * Finds a List of deleted Dreams for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Dream>
	 */
	public List<Dream> findDeletedDreams(User user, CriteriaParams<Dream> criteriaParams);

	/**
	 * Finds the count of all deleted Dreams for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedDreamCount(User user);

	/**
	 * Finds a tagCloud for a user.
	 *
	 * @param user
	 * @param tagCount
	 * @param minWeight
	 * @param maxWeight
	 * @return TagCloud
	 */
	public TagCloud findDreamsignTagCloud(User user, int tagCount, double minWeight, double maxWeight);

}
