package com.imadp.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import com.imadp.core.Property;
import com.imadp.dao.DaoException;
import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableDao;
import com.imadp.dao.criteria.Criterion;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.criteria.find.FindCriteriaBuilder;
import com.imadp.dao.criteria.search.SearchCriteria;
import com.imadp.dao.criteria.search.SearchCriteriaBuilder;
import com.imadp.dao.hibernate.criteria.find.FindCriteriaBuilderHibernateImpl;
import com.imadp.dao.hibernate.criteria.find.FindCriteriaExecutor;
import com.imadp.dao.hibernate.criteria.search.SearchCriteriaBuilderHibernateImpl;
import com.imadp.dao.hibernate.criteria.search.SearchCriteriaExecutor;
import com.imadp.dao.hibernate.criteria.search.SearchMappingConfiguration;

/**
 * PersistableDaoImpl
 *
 * Hibernate implementation of the PersistableDao interface via Spring's HibernateTemplate.
 *
 * EntityClass is required for all entities. This essentially ensures that all objects map directly to a class,
 * rather than allowing one class to map to multiple tables via entityNames. The reason for this is simply because
 * hibernate cannot correctly identify foreign key constraints on entity names, which leads to a brittle database
 * schema. Additionally, Hibernate Search does not support entity names. Using entity classes solves these problems,
 * as well as provides better code clarity as it is immediately apparent how classes are mapped to tables.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableDaoImpl<T extends Persistable> extends AbstractHibernateDaoImpl
		implements PersistableDao<T> {

	// persistable entity
	protected Class<T> entityClass;
	protected String entityName;

	// search configuration
	private SearchMappingConfiguration searchMappingConfiguration;

	// control booleans
	private boolean searchEnabled;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(entityClass);

		this.entityName = entityClass.getName();

		if(searchMappingConfiguration != null)
			searchEnabled = searchMappingConfiguration.hasSearchClass(entityClass);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(Long id) {
		logger.debug("[Dao] Getting [{}] by id [{}]", entityName, id);

		try
		{
			return (T) getSession().get(entityClass, id);
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not get ["+entityName+"] by id ["+id+"] ", exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void save(T object) {
		logger.debug("[Dao] {} [{}]", object.isUnsaved() ? "Saving" : "Updating", entityName);

		try
		{
			try
			{
				getSession().saveOrUpdate(object);
			}
			catch(NonUniqueObjectException exception)
			{
				logger.debug("[Dao] Merging [{}]", entityName);
				object = (T) getSession().merge(object);
				getSession().saveOrUpdate(object);
			}
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not save or update ["+entityName+"]", exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void delete(T object) {
		logger.debug("[Dao] Deleting [{}]", entityName);

		try
		{
			try
			{
				getSession().delete(object);
			}
			catch(NonUniqueObjectException exception)
			{
				logger.debug("[Dao] Merging [{}]", entityName);
				object = (T) getSession().merge(object);
				getSession().delete(object);
			}
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not delete ["+entityName+"] with id ["+object.getId()+"]", exception);
		}
	}

	@Override
	public long deleteAll() {
		return deleteBy("delete " + entityName);
	}

	/**
	 * Deletes by an hql query, additionally purging and optimizing all
	 * Lucene search indexes, if indexing is available for the searchClass.
	 *
	 * @param hql
	 * @return long - the number of rows deleted
	 */
	protected final long deleteBy(final String hql) {
		logger.debug("[Dao] Deleting [{}] by hql [{}]", entityName, hql);

		try
		{
			Session session = getSession();

			int deleteCount = Integer.valueOf(session.createQuery(hql).executeUpdate());

			// if search is enabled, purge the search indexes
			if(searchEnabled)
				Search.getFullTextSession(session).purgeAll(entityClass);

			return deleteCount;
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not delete by hql ["+hql+"]", exception);
		}
	}

	@Override
	public List<T> findAll() {
		logger.debug("[Dao] Finding all [{}]", entityName);

		try
		{
			return findByCriteria(DetachedCriteria.forClass(entityClass), Results.ALL);
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not find all ["+entityName+"]", exception);
		}
	}

	@Override
	public long findCount() {
		logger.debug("[Dao] Finding [{}] row count", entityName);

		try
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
			criteria.setProjection(Projections.rowCount());
			List<Long> results =  findByCriteria(criteria, Results.ALL);
			return results.isEmpty() ? 0 : results.get(0);
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not find the count of all ["+entityName+"]", exception);
		}
	}

	@Override
	public List<T> findBy(FindCriteria<? super T> findCriteria) {
		logger.debug("[Dao] Finding [{}] by [{}] ", entityName, findCriteria);

		try
		{
			return prepareFindCriteriaExecutor(findCriteria).find();
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not find ["+entityName+"] by ["+findCriteria+"]", exception);
		}
	}

	@Override
	public long findCountBy(FindCriteria<? super T> findCriteria) {
		logger.debug("[Dao] Finding [{}] row count by [{}]", entityName, findCriteria);

		try
		{
			return prepareFindCriteriaExecutor(findCriteria).findCount();
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not find the count of all ["+entityName+"] by ["+findCriteria+"]", exception);
		}
	}

	/**
	 * Prepares and returns a FindCriteriaExecutor for a FindCriteria.
	 *
	 * @param findCriteria
	 * @return FindCriteriaExecutor
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private FindCriteriaExecutor<T> prepareFindCriteriaExecutor(FindCriteria<? super T> findCriteria) {
		FindCriteriaExecutor<T> findCriteriaExecutor = new FindCriteriaExecutor<>(entityName, getSession());

		for(Criterion criterion : findCriteria.getCriteria())
			criterion.applyCriterion(findCriteriaExecutor);

		return findCriteriaExecutor;
	}

	@Override
	public FindCriteriaBuilder<T> findCriteriaBuilder(Results results) {
		return new FindCriteriaBuilderHibernateImpl<>(results);
	}

	@Override
	public List<T> searchBy(final SearchCriteria<? super T> searchCriteria) {
		logger.debug("[Dao] Searching [{}] by [{}]", entityName, searchCriteria);
		validateSearch();

		try
		{
			return prepareSearchCriteriaExecutor(searchCriteria).search();
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not search by ["+searchCriteria+"]", exception);
		}
	}

	@Override
	public long searchCountBy(final SearchCriteria<? super T> searchCriteria) {
		logger.debug("[Dao] Searching [{}] row count by [{}]", entityName, searchCriteria);
		validateSearch();

		try
		{
			return prepareSearchCriteriaExecutor(searchCriteria).searchCount();
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not search count by ["+searchCriteria+"]", exception);
		}
	}

	/**
	 * Prepares and returns a SearchCriteriaExecutor for a SearchCriteria.
	 *
	 * @param searchCriteria
	 * @return SearchCriteriaExecutor
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private SearchCriteriaExecutor<T> prepareSearchCriteriaExecutor(SearchCriteria<? super T> searchCriteria) {
		SearchCriteriaExecutor<T> searchCriteriaExecutor = new SearchCriteriaExecutor<>(entityClass, getSession());

		for(Criterion criterion : searchCriteria.getCriteria())
			criterion.applyCriterion(searchCriteriaExecutor);

		return searchCriteriaExecutor;
	}

	@Override
	public SearchCriteriaBuilder<T> searchCriteriaBuilder(
			String query, List<Property<T, String>> properties, Results results) {
		return new SearchCriteriaBuilderHibernateImpl<>(query, properties, results);
	}

	@Override
	public void searchIndexAll(final int batchSize) {
		validateSearch();

		logger.debug("[Dao] Indexing all search entities [{}]", entityClass);

		FullTextSession fullTextSession = Search.getFullTextSession(getSession());

		fullTextSession.setFlushMode(FlushMode.MANUAL);
		fullTextSession.setCacheMode(CacheMode.IGNORE);

		// scrollable results will avoid loading too many objects in memory
		ScrollableResults results = fullTextSession.createCriteria(entityClass)
		    .setFetchSize(batchSize)
		    .scroll(ScrollMode.FORWARD_ONLY);

		int index = 0;

		while(results.next())
		{
		    index++;

		    //index each element
		    fullTextSession.index(results.get(0));

		    if (index % batchSize == 0) {
		    	//apply changes to indexes and clear memory
		        fullTextSession.flushToIndexes();
		        fullTextSession.clear();
		    }
		}

	}

	@Override
	public void searchIndexPurge() {
		validateSearch();

		logger.debug("[Dao] Purging all search indexes [{}]", entityClass);

		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		fullTextSession.purgeAll(entityClass);
	}

	@Override
	public void searchIndexOptimize() {
		validateSearch();

		logger.debug("[Dao] Optimizing all search indexes [{}]", entityClass);

		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		fullTextSession.getSearchFactory().optimize(entityClass);
	}

	/**
	 * Validates that search is enabled before performing some search operation.
	 *
	 */
	private void validateSearch() {
		Validate.isTrue(searchEnabled, "Search is not enabled in the SearchMappingConfiguration for ["+entityName+"]");
	}

	/**
	 * Returns a named query from the fully qualified query name.
	 *
	 * @param name
	 * @return String
	 */
	protected final Query getNamedQuery(String name) {
		return getSession().getNamedQuery(StringUtils.join(new Object[] {entityName, name}, '.'));
	}

	// getters and setters
	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public SearchMappingConfiguration getSearchMappingConfiguration() {
		return searchMappingConfiguration;
	}

	public void setSearchMappingConfiguration(SearchMappingConfiguration searchMappingConfiguration) {
		this.searchMappingConfiguration = searchMappingConfiguration;
	}

	@Override
	public String getSimpleToString() {
		return super.getSimpleToString() + "[" + entityName + "]";
	}

}