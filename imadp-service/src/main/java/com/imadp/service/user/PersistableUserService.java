package com.imadp.service.user;

import java.util.List;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.service.PersistableService;

/**
 * PersistableUserService
 *
 * An extention of the PersistableService to offer common functionality for objects with User references.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface PersistableUserService<T extends AbstractPersistableUser> extends PersistableService<T> {

	/**
	 * Return an object by id and user.
	 *
	 * @param user
	 * @param id
	 * @return <T> object or <code>null</code> if no result was found
	 */
	public T findFirstByUser(User user, Long id);

	/**
	 * Return an object by uid and user.
	 *
	 * @param user
	 * @param uid
	 * @return <T> object or <code>null</code> if no result was found
	 */
	public T findFirstByUser(User user, String uid);

	/**
	 * Finds the first result by user and orders.
	 *
	 * @param user
	 * @param order
	 * @return <T> object or <code>null</code> if no result was found
	 */
	public T findFirstByUser(User user, Order<T> order);

	/**
	 * Finds the first result by user.
	 *
	 * @param user
	 * @return <T> object or <code>null</code> if no result was found
	 */
	public T findFirstByUser(User user);

	/**
	 * Returns true if an object was found by the given user, false otherwise.
	 *
	 * @param user
	 * @return boolean
	 */
	public boolean isFoundByUser(User user);

	/**
	 * Finds a List<T> of results by user and criteriaParams.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findByUser(User user, CriteriaParams<T> criteriaParams);

	/**
	 * Finds a List<T> of results by user, persistableState and criteriaParams.
	 *
	 * @param user
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findByUser(User user, PersistableState persistableState, CriteriaParams<T> criteriaParams);

	/**
	 * Finds a List<T> of results by user within the inclusive timeCreated range and by criteriaParams.
	 *
	 * @param user
	 * @param timeCreatedStart
	 * @param timeCreatedEnd
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findByUserWithinTimeCreated(User user, Long timeCreatedStart, Long timeCreatedEnd,
			CriteriaParams<T> criteriaParams);

	/**
	 * Finds a List<T> of results by user within the inclusive timeModified range and by criteriaParams.
	 *
	 * @param user
	 * @param timeModifiedStart
	 * @param timeModifiedEnd
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findByUserWithinTimeModified(User user, Long timeModifiedStart, Long timeModifiedEnd,
			CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of all rows by user.
	 *
	 * @param user
	 * @return long
	 */
	public long findCountByUser(User user);

	/**
	 * Finds the count of all rows by user and persistableState.
	 *
	 * @param user
	 * @param persistableState
	 * @return long
	 */
	public long findCountByUser(User user, PersistableState persistableState);

	/**
	 * Finds the count of all objects by user within the inclusive timeCreated range.
	 *
	 * @param user
	 * @param timeCreatedStart
	 * @param timeCreatedEnd
	 * @return long
	 */
	public long findCountByUserWithinTimeCreated(User user, Long timeCreatedStart, Long timeCreatedEnd);

	/**
	 * Finds the count of all objects by user within the inclusive timeModified range.
	 *
	 * @param user
	 * @param timeModifiedStart
	 * @param timeModifiedEnd
	 * @return long
	 */
	public long findCountByUserWithinTimeModified(User user, Long timeModifiedStart, Long timeModifiedEnd);

}