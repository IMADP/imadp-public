package com.tracktacular.service.tracker.dream;

import java.util.List;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;

/**
 * DreamService
 *
 * Provides common retrieval operations for Dream objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface DreamService extends PersistableUserService<Dream> {

	/**
	 * Finds a list of all lucid dreams by user, persistableState, and criteriaParams.
	 *
	 * @param user
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<Dream>
	 */
	public List<Dream> findLucidByUser(User user, PersistableState persistableState, CriteriaParams<Dream> criteriaParams);

	/**
	 * Finds the count of all lucid dreams by user and persistableState.
	 *
	 * @param user
	 * @param persistableState
	 * @return long
	 */
	public long findLucidCountByUser(User user, PersistableState persistableState);

	/**
	 * Finds a list of all favorite dreams by user, persistableState, and criteriaParams.
	 *
	 * @param user
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<Dream>
	 */
	public List<Dream> findFavoriteByUser(User user, PersistableState persistableState, CriteriaParams<Dream> criteriaParams);

	/**
	 * Finds the count of all favorite dreams by user and persistableState.
	 *
	 * @param user
	 * @param persistableState
	 * @return long
	 */
	public long findFavoriteCountByUser(User user, PersistableState persistableState);

	/**
     * Gets the DreamTrackerReport for the given user.
     *
     * @param user
	 * @return DreamTrackerReport
     */
    public DreamTrackerReport getDreamTrackerReport(User user);

}
