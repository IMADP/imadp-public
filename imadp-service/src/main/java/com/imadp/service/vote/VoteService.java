package com.imadp.service.vote;

import com.imadp.dao.Persistable;
import com.imadp.service.user.PersistableUserService;
import com.imadp.service.user.User;


/**
 * VoteService
 *
 * Provides common retrieval operations for Vote objects.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface VoteService<T extends AbstractVote<V>, V extends Persistable> extends PersistableUserService<T> {

	/**
	 * Finds the count of all Votes by objectId.
	 *
	 * @param votable
	 * @return long
	 */
	public long findCountBy(V votable);

	/**
	 * Finds the tally as the sum of ratings by object id.
	 *
	 * @param votable
	 * @return long
	 */
	public long findTallyBy(V votable);

	/**
	 * Returns true if a Vote is found by User and objectId, false otherwise.
	 *
	 * @param user
	 * @param votable
	 * @return boolean
	 */
	public boolean isFoundByUser(User user, V votable);

}