package com.imadp.service.tag;

import java.util.List;

import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableDao;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;


/**
 * ITagDao
 *
 * An extention of the PersistableDao which provides additional Tag queries.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TagDao<T extends AbstractTag<? extends Persistable>> extends PersistableDao<T> {

	/**
	 * Finds a List of TagFrequencies for a user, unique according to name and persistable state.
	 * If persistableState is null, all frequencies are found.
	 *
	 * @param user
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<TagFrequency> or an empty List if no results were found
	 */
	public List<TagFrequency> findTagFrequencies(
			User user, PersistableState persistableState, CriteriaParams<TagFrequency> criteriaParams);

	/**
	 * Returns the count of all TagFrequencies found for a User and persistable state.
	 * If persistableState is null, all frequencies are counted.
	 *
	 * @param user
	 * @param persistableState
	 * @return long
	 */
	public long findTagFrequencyCount(User user, PersistableState persistableState);

}
