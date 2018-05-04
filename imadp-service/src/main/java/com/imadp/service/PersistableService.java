package com.imadp.service;

import java.util.Collection;
import java.util.List;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;

/**
 * PersistableService
 *
 * Provides a way of retrieving data from a PersistableDao through common retrieval operations.
 * Services should extend this interface to provide simple and specific find queries as needed.
 *
 * @note It is extremely important to avoid automatic cascade operations for referenced objects,
 *       as the object service cannot maintain cache state without knowing it should be saved.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface PersistableService<T extends Persistable> {

	/**
	 * Return an object by id.
	 *
	 * @param id
	 * @return <T> object or <code>null</code> if no result was found
	 */
	public T get(Long id);

	/**
	 * Return an object by uid.
	 *
	 * @param uid
	 * @return <T> object or <code>null</code> if no result was found
	 */
	public T get(String uid);

	/**
	 * Saves an object to the database through the dao.
	 *
	 * @param persistable
	 */
	public void save(T persistable);

	/**
	 * Saves a Collection of objects to the database.
	 *
	 * @param persistables
	 */
	public void save(Collection<T> persistables);

	/**
	 * Deletes an object from the database.
	 *
	 * @param persistable
	 */
	public void delete(T persistable);

	/**
	 * Deletes a Collection of objects from the database.
	 *
	 * @param persistables
	 */
	public void delete(Collection<T> persistables);

	/**
	 * Finds a List<T> of results by criteriaParams.
	 *
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findBy(CriteriaParams<T> criteriaParams);

	/**
	 * Finds a List<T> of results by persistable state and criteriaParams.
	 *
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findBy(PersistableState persistableState, CriteriaParams<T> criteriaParams);

	/**
	 * Finds a List<T> of results within the inclusive timeCreated range and by criteriaParams.
	 *
	 * @param timeCreatedStart
	 * @param timeCreatedEnd
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findWithinTimeCreated(Long timeCreatedStart, Long timeCreatedEnd, CriteriaParams<T> criteriaParams);

	/**
	 * Finds a List<T> of results within the inclusive timeModified range and by criteriaParams.
	 *
	 * @param timeModifiedStart
	 * @param timeModifiedEnd
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findWithinTimeModified(Long timeModifiedStart, Long timeModifiedEnd, CriteriaParams<T> criteriaParams);

	/**
	 * Returns a list of recently created entities for the previous 24 hour period, limitted by maxResults.
	 *
	 * @param maxResults
	 * @return List<T>
	 */
	public List<T> findRecentlyCreated(int maxResults);

	/**
	 * Returns a list of recently modified entities for the previous 24 hour period, limitted by maxResults.
	 *
	 * @param maxResults
	 * @return List<T>
	 */
	public List<T> findRecentlyModified(int maxResults);

	/**
	 * Finds the count of all objects.
	 *
	 * @return long
	 */
	public long findCount();

	/**
	 * Finds the count of all objects by persistableState.
	 *
	 * @param persistableState
	 * @return long
	 */
	public long findCountBy(PersistableState persistableState);

	/**
	 * Finds the count of all objects within the inclusive timeCreated range.
	 *
	 * @param timeCreatedStart
	 * @param timeCreatedEnd
	 * @return long
	 */
	public long findCountWithinTimeCreated(Long timeCreatedStart, Long timeCreatedEnd);

	/**
	 * Finds the count of all objects within the inclusive timeModified range.
	 *
	 * @param timeModifiedStart
	 * @param timeModifiedEnd
	 * @return long
	 */
	public long findCountWithinTimeModified(Long timeModifiedStart, Long timeModifiedEnd);

	/**
	 * Returns the count of recently created entities for the previous 24 hour period.
	 *
	 * @return long
	 */
	public long findRecentlyCreatedCount();

	/**
	 * Returns the count of recently modified entities for the previous 24 hour period.
	 *
	 * @return long
	 */
	public long findRecentlyModifiedCount();

	/**
	 * Searches for a List<T> by query, properties, and results.
	 *
	 * @param query
	 * @param properties
	 * @param results
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> searchBy(String query, List<Property<T, String>> properties, Results results);

	/**
	 * Returns the count all of all results found by searching for a query and a list of properties.
	 *
	 * @param query
	 * @param properties
	 * @return long
	 */
	public long searchCountBy(String query, List<Property<T, String>> properties);

	/**
	 * Indexes all entities. This is done in batches to prevent memory issues on larger repositories.
	 *
	 * @note This is necessary if data is added or removed by an external process.
	 *
	 * @param batchSize
	 */
	public void searchIndexAll(int batchSize);

	/**
	 * Purges all search indexes.
	 *
	 * @note This is necessary if data is added or removed by an external process.
	 *
	 */
	public void searchIndexPurge();

	/**
	 * Optimizes all search indexes.
	 *
	 * @note This is used to defragment and optimize all existing indexes.
	 *
	 */
	public void searchIndexOptimize();

	/**
	 * Returns the entityClass associated with this service.
	 *
	 * @return Class<T>
	 */
	public Class<T> getEntityClass();

}
