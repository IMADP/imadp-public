package com.imadp.service.tag;

import java.util.List;

import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.PersistableUserService;
import com.imadp.service.user.User;

/**
 * ITagService
 *
 * Provides common retrieval operations for Tag objects.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TagService<T extends AbstractTag<V>, V extends Persistable> extends PersistableUserService<T> {

	/**
	 * Finds a list of tags by taggable and criteria params.
	 *
	 * @param taggable
	 * @param criteriaParams
	 * @return List<T>
	 */
	public List<T> findBy(V taggable, CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of tags by taggable.
	 *
	 * @param taggable
	 * @return long
	 */
	public long findCountBy(V taggable);

	/**
	 * Finds a list of tags by user, nameSlug and criteria params.
	 *
	 * @param user
	 * @param nameSlug
	 * @param criteriaParams
	 * @return List<T>
	 */
	public List<T> findByUser(User user, String nameSlug, CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of tags by user and nameSlug.
     *
	 * @param user
	 * @param nameSlug
	 * @return long
	 */
	public long findCountByUser(User user, String nameSlug);

	/**
	 * Finds a list of tags by user, nameSlug, persistable state and criteria params.
	 *
	 * @param user
	 * @param nameSlug
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<T>
	 */
	public List<T> findByUser(User user, String nameSlug, PersistableState persistableState, CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of tags by user, nameSlug and persistable state.
     *
	 * @param user
	 * @param nameSlug
	 * @param persistableState
	 * @return long
	 */
	public long findCountByUser(User user, String nameSlug, PersistableState persistableState);

	/**
	 * Finds a List of TagFrequencies for a user, unique according to name.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<TagFrequency> or an empty List if no results were found
	 */
	public List<TagFrequency> findTagFrequencies(User user, CriteriaParams<TagFrequency> criteriaParams);

	/**
	 * Returns the count of all TagFrequencies found for a User.
	 *
	 * @param user
	 * @return long
	 */
	public long findTagFrequencyCount(User user);

	/**
	 * Finds a List of TagFrequencies for a user, unique according to name and persistableState.
	 *
	 * @param user
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<TagFrequency> or an empty List if no results were found
	 */
	public List<TagFrequency> findTagFrequencies(
			User user, PersistableState persistableState, CriteriaParams<TagFrequency> criteriaParams);

	/**
	 * Returns the count of all TagFrequencies found for a User and persistableState.
	 *
	 * @param user
	 * @param persistableState
	 * @return long
	 */
	public long findTagFrequencyCount(User user, PersistableState persistableState);

}
