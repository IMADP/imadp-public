package com.imadp.dao;

import java.util.List;

import com.imadp.core.Property;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.criteria.find.FindCriteriaBuilder;
import com.imadp.dao.criteria.search.SearchCriteria;
import com.imadp.dao.criteria.search.SearchCriteriaBuilder;


/**
 * PersistableDao
 *
 * Provides a type-safe method of interacting with IPersistable objects in a repository.
 * Includes common operations such as get, save/update, and delete, as well as dynamic
 * find and search operations through the use of the FindCriteria and SearchCriteria objects.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface PersistableDao<T extends Persistable> {

	/**
	 * Get an object from the repository with a given id.
	 * The id represents the persistable object's primary key.
	 *
	 * @param id
	 * @return <T> object
	 */
	public T get(Long id);

	/**
	 * Save an object to the repository.
	 * If the object is transient, a new entry will inserted. Otherwise, an existing entry
	 * found by the object id will be updated.
	 *
	 * @param object
	 */
	public void save(T object);

	/**
	 * Delete an object from the repository.
	 *
	 * @param object
	 */
	public void delete(T object);

	/**
	 * Deletes all objects from the repository and returns the number of objects deleted.
	 *
	 * @return long
	 */
	public long deleteAll();

	/**
	 * Returns a List<T> of all objects in the repository.
	 * The default sort is determined by the underlying repository.
	 *
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findAll();

	/**
	 * Finds the row count of all objects in the repository.
	 *
	 * @return long
	 */
	public long findCount();

	/**
	 * Returns a List<T> of objects from a query constructed by FindCriteria.
	 *
	 * @param findCriteria
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> findBy(FindCriteria<? super T> findCriteria);

	/**
	 * Returns the row count from a query constructed by FindCriteria.
	 *
	 * @param findCriteria
	 * @return long
	 */
	public long findCountBy(FindCriteria<? super T> findCriteria);

	/**
	 * Returns a new IFindCriteriaBuilder instance.
	 *
	 * @param results
	 * @return IFindCriteriaBuilder
	 */
	public FindCriteriaBuilder<T> findCriteriaBuilder(Results results);

	/**
	 * Returns a List<T> of objects from a search query constructed by SearchCriteria.
	 *
	 * @param searchCriteria
	 * @throws DaoException if unable to parse the search criteria
	 * @return List<T> or an empty List if no results were found
	 */
	public List<T> searchBy(SearchCriteria<? super T> searchCriteria);

	/**
	 * Returns the row count from a search query constructed by SearchCriteria.
	 *
	 * @param searchCriteria
	 * @throws DaoException if unable to parse the search criteria
	 * @return long
	 */
	public long searchCountBy(SearchCriteria<? super T> searchCriteria);

	/**
	 * Returns a new ISearchCriteriaBuilder instance for a given query, including
	 * the properties to search on and the result set to return.
	 *
	 * @param query
	 * @param properties
	 * @param results
	 * @return ISearchCriteriaBuilder
	 */
	public SearchCriteriaBuilder<T> searchCriteriaBuilder(
			String query, List<Property<T, String>> properties, Results results);

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
	 * Returns the entityClass associated with this dao.
	 *
	 * @return Class<T>
	 */
	public Class<T> getEntityClass();

}
