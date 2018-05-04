package com.imadp.service.vote;

import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableDao;

/**
 * VoteDao
 *
 * An extention of the PersistableDao which provides additional Vote queries.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface VoteDao<T extends AbstractVote<? extends Persistable>> extends PersistableDao<T> {

	/**
	 * Finds the sum of ratings for a votable object.
	 *
	 * @param votable
	 * @return long
	 */
	public long findRatingSumBy(Persistable votable);

}
