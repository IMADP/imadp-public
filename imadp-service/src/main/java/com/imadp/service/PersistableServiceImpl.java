package com.imadp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.imadp.core.Property;
import com.imadp.core.PropertyValue;
import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.dao.AbstractPersistable;
import com.imadp.dao.PersistableDao;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.criteria.find.FindCriteriaBuilder;
import com.imadp.dao.criteria.search.SearchCriteria;
import com.imadp.dao.criteria.search.SearchCriteriaBuilder;

/**
 * PersistableServiceImpl
 *
 * Provides a way of retrieving data from an IPersistableDao, while maintaining
 * optional and configurable caching of the most frequently used retrieval operations.
 *
 * Due to the generic nature of this class, cache is cleared more aggressively on save
 * and delete operations. Subclasses are encouraged to use more intelligent caching
 * and/or provide simplified find and search operations for common use-cases.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableServiceImpl<T extends AbstractPersistable> extends ServiceComponent
	implements PersistableService<T> {

	// the persistable dao utilized by this service
	protected PersistableDao<T> dao;

	// optional cache instances
	private Cache<Serializable, T> getCache;
	private Cache<Boolean, Long> findCountCache;
	private Cache<FindCriteria<? super T>, List<T>> findByFindCriteriaCache;
	private Cache<FindCriteria<? super T>, Long> findCountByFindCriteriaCache;
	private Cache<SearchCriteria<? super T>, List<T>> searchBySearchCriteriaCache;
	private Cache<SearchCriteria<? super T>, Long> searchCountBySearchCriteriaCache;

	/*
	 * These control booleans help when determining the logic flow for the service.
	 * These are generally unnecessary for all but the most re-usable services as the
	 * performance boost is minimal and may introduce some code confusion.
	 *
	 */
	private boolean hasGetCache;
	private boolean hasFindByFindCriteriaCache;
	private boolean hasFindCountCache;
	private boolean hasFindCountByFindCriteriaCache;
	private boolean hasSearchBySearchCriteriaCache;
	private boolean hasSearchCountBySearchCriteriaCache;

	// these booleans ensure that the save and delete hooks call super if they are overridden
	private boolean onBeforeSaveHook;
	private boolean onAfterSaveHook;
	private boolean onBeforeDeleteHook;
	private boolean onAfterDeleteHook;

	@Override
	protected void onInit() {
		super.onInit();

		// get the persistable dao
		dao = getPersistableDao();

		// validate dao
		Validate.notNull(dao);

		// initialize control booleans
		hasGetCache = getCache != null;
		hasFindByFindCriteriaCache = findByFindCriteriaCache != null;
		hasFindCountCache = findCountCache != null;
		hasFindCountByFindCriteriaCache = findCountByFindCriteriaCache != null;
		hasSearchBySearchCriteriaCache = searchBySearchCriteriaCache != null;
		hasSearchCountBySearchCriteriaCache = searchCountBySearchCriteriaCache != null;
	}

	/**
	 * Returns the Dao used by this service.
	 *
	 * @return IPersistableDao<T>
	 */
	protected PersistableDao<T> getPersistableDao() {
		return dao;
	}

	/**
	 * Return an object by id, or null if none was found by the dao.
	 * If hasGetCache is true, the object is checked in cache before using the dao.
	 *
	 * @param id
	 * @return <T> object
	 */
	@Override
	public final T get(Long id) {
		return !hasGetCache ? doGet(id) : getCache.getOrPut(id, createValueGet);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private T doGet(Long id) {
		return dao.get(id);
	}

	/**
	 * Return an object by uid.
	 * If hasGetCache is true, the object is checked in cache before using the dao.
	 *
	 * @param uid
	 * @return <T> object or <code>null</code> if no result was found
	 */
	@Override
	public T get(String uid) {
		return !hasGetCache ? doGet(uid) : getCache.getOrPut(uid, createValueGet);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private T doGet(String uid) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ONE))
				.whereEqualTo(AbstractPersistable.UID, uid).build();

		return getFirst(dao.findBy(findCriteria));
	}

	private CreateValue<Serializable, T> createValueGet = new CreateValue<Serializable, T>() {
		@Override
		public T create(Serializable id) {
			if(id instanceof Long)
				return doGet((Long)id);

			return doGet((String)id);
		}
	};

	/**
	 * Saves an object to the database through the dao.
	 * If hasGetCache is true, we either put in cache or clear, depending on putInGetCacheOnSave.
	 * If hasFindCountCache is true and the object was transient, we clear the count.
	 * All other caches must be cleared if they are present.
	 *
	 * @param persistable
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void save(T persistable) {
		boolean initialSave = persistable.isUnsaved();

		// onBeforeSave hook
		onBeforeSave(persistable, initialSave);

		// ensure overriding methods called super
		if(!onBeforeSaveHook)
			throw new UnsupportedOperationException("Methods overriding onBeforeSave must call super()");

		// save
		dao.save(persistable);

		// onAfterSave hook
		onAfterSave(persistable, initialSave);

		// ensure overriding methods called super
		if(!onAfterSaveHook)
			throw new UnsupportedOperationException("Methods overriding onAfterSave must call super()");
	}

	/**
	 * Saves a List of objects to the database through the dao.
	 * Delegates to the save method to allow hooks into the save lifecycle.
	 *
	 * @param persistables
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void save(Collection<T> persistables) {
		if(!CollectionUtils.isEmpty(persistables))
			for(T t : persistables)
				save(t);
	}

	/**
	 * Hook into before the object is saved to the database through the dao.
	 * Passes a boolean to indicate whether the object is being saved initially or updated.
	 *
	 * @param persistable
	 * @param initialSave
	 */
	protected void onBeforeSave(T persistable, boolean initialSave) {
		onBeforeSaveHook = true;
	}

	/**
	 * Hook into after the object is saved to the database through the dao.
	 * Passes a boolean to indicate whether the object is being saved initially or updated.
	 *
	 * @param persistable
	 * @param initialSave
	 */
	protected void onAfterSave(T persistable, boolean initialSave) {
		onAfterSaveHook = true;

		// if hasGetCache is true, remove the object from the getCache
		if(hasGetCache)
		{
			getCache.remove(persistable.getId());
			getCache.remove(persistable.getUid());
		}

		// if the object was previously unsaved, clear the findCountCache
		if(initialSave)
			clearFindCountCache();

		clearFindByFindCriteriaCache();
		clearFindCountByFindCriteriaCache();
		clearSearchBySearchCriteriaCache();
		clearSearchCountBySearchCriteriaCache();
	}

	/**
	 * Deletes an object from the database through the dao.
	 * All other caches must be cleared if they are present.
	 *
	 * @param persistable
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void delete(T persistable) {

		// onBeforeDelete hook
		onBeforeDelete(persistable);

		// ensure overriding methods called super
		if(!onBeforeDeleteHook)
			throw new UnsupportedOperationException("Methods overriding onBeforeDelete must call super()");

		dao.delete(persistable);

		// onAfterDelete hook
		onAfterDelete(persistable);

		// ensure overriding methods called super
		if(!onAfterDeleteHook)
			throw new UnsupportedOperationException("Methods overriding onAfterDelete must call super()");
	}

	/**
	 * Deletes a List of objects from the database through the dao.
	 * Delegates to the delete method to allow hooks into the delete lifecycle.
	 *
	 * @param persistables
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void delete(Collection<T> persistables) {
		if(!CollectionUtils.isEmpty(persistables))
			for(T t : persistables)
				delete(t);
	}

	/**
	 * Hook into before the object is deleted from the database through the dao.
	 *
	 * @param persistable
	 */
	protected void onBeforeDelete(T persistable) {
		onBeforeDeleteHook = true;
	}

	/**
	 * Hook into after the object is deleted from the database through the dao.
	 *
	 * @param persistable
	 */
	protected void onAfterDelete(T persistable) {
		onAfterDeleteHook = true;

		// if hasGetCache is true, remove the object from the getCache
		if(hasGetCache)
		{
			getCache.remove(persistable.getId());
			getCache.remove(persistable.getUid());
		}

		clearFindCountCache();
		clearFindByFindCriteriaCache();
		clearFindCountByFindCriteriaCache();
		clearSearchBySearchCriteriaCache();
		clearSearchCountBySearchCriteriaCache();
	}

	/**
	 * Finds a List<T> objects found by criteriaParams, or the empty List if no results were found.
	 *
	 * @param criteriaParams
	 * @return List<T>
	 */
	@Override
	public final List<T> findBy(CriteriaParams<T> criteriaParams) {
		return findBy(findCriteriaBuilder(criteriaParams).build());
	}

	@Override
	public List<T> findBy(PersistableState persistableState, CriteriaParams<T> criteriaParams) {
		return findBy(AbstractPersistable.PERSISTABLE_STATE, persistableState, criteriaParams);
	}


	/**
	 * Finds a List<T> of results by criteriaParams and property values.
	 *
	 * @param criteriaParams
	 * @param propertyValues
	 * @return T
	 */
	@SafeVarargs
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected final List<T> findBy(CriteriaParams<T> criteriaParams, PropertyValue<? super T, ?>... propertyValues) {
		FindCriteriaBuilder<T> findCriteriaBuilder = findCriteriaBuilder(criteriaParams);

		for(PropertyValue propertyValue : propertyValues)
		{
			Property property = propertyValue.getProperty();
			Object value = propertyValue.getValue();
			findCriteriaBuilder.whereEqualTo(property, value);
		}

		return findBy(findCriteriaBuilder.build());
	}

	/**
	 * Finds a List<T> of results within the inclusive timeCreated range and by criteriaParams.
	 *
	 * @param timeCreatedStart
	 * @param timeCreatedEnd
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	@Override
	public final List<T> findWithinTimeCreated(Long timeCreatedStart, Long timeCreatedEnd, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereGreaterThanOrEqualTo(AbstractPersistable.TIME_CREATED, timeCreatedStart)
				.whereLessThanOrEqualTo(AbstractPersistable.TIME_CREATED, timeCreatedEnd).build();

		return findBy(findCriteria);
	}

	/**
	 * Finds a List<T> of results within the inclusive timeModified range and by criteriaParams.
	 *
	 * @param timeModifiedStart
	 * @param timeModifiedEnd
	 * @param criteriaParams
	 * @return List<T> or an empty List if no results were found
	 */
	@Override
	public final List<T> findWithinTimeModified(Long timeModifiedStart, Long timeModifiedEnd, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereGreaterThanOrEqualTo(AbstractPersistable.TIME_MODIFIED, timeModifiedStart)
				.whereLessThanOrEqualTo(AbstractPersistable.TIME_MODIFIED, timeModifiedEnd).build();

		return findBy(findCriteria);
	}

	/**
	 * Returns a list of recently created entities for the previous 24 hour period, limitted by maxResults.
	 *
	 * @param maxResults
	 * @return List<T>
	 */
	@Override
	public final List<T> findRecentlyCreated(int maxResults) {
		DateTime date = new DateTime();
		long startTime = date.minusDays(1).getMillis();
		long endTime = date.getMillis();

		CriteriaParams<T> criteriaParams = CriteriaParams.of(
				new Results(0, maxResults), Order.desc(AbstractPersistable.TIME_CREATED));

		return findWithinTimeCreated(startTime, endTime, criteriaParams);
	}

	/**
	 * Returns a list of recently modified entities for the previous 24 hour period, limitted by maxResults.
	 *
	 * @param maxResults
	 * @return List<T>
	 */
	@Override
	public final List<T> findRecentlyModified(int maxResults) {
		DateTime date = new DateTime();
		long startTime = date.minusDays(1).getMillis();
		long endTime = date.getMillis();

		CriteriaParams<T> criteriaParams = CriteriaParams.of(
				new Results(0, maxResults), Order.desc(AbstractPersistable.TIME_MODIFIED));

		return findWithinTimeModified(startTime, endTime, criteriaParams);
	}

	/**
	 * Finds the first result from the database through the dao.
	 *
	 * @param findCriteria
	 * @return <T> object
	 */
	protected final T findFirstBy(FindCriteria<T> findCriteria) {
		return getFirst(findBy(findCriteria));
	}

	/**
	 * Finds the first result for a property and value through the dao.
	 *
	 * @param property
	 * @param value
	 * @return <T> object
	 */
	protected final <V> T findFirstBy(Property<? super T, V> property, V value) {
		return getFirst(findBy(property, value, CriteriaParams.<T>of(Results.ONE)));
	}

	/**
	 * Finds a list of results matching the given property and criteria params.
	 *
	 * @param property
	 * @param value
	 * @param criteriaParams
	 * @return List<T>
	 */
	protected final <V> List<T> findBy(Property<? super T, V> property, V value, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(property, value).build();

		return findBy(findCriteria);
	}

	/**
	 * Finds a List<T> of objects from the database through the dao.
	 * If hasFindByFindCriteriaCache is true, the List is checked in cache before using the dao.

	 * @param findCriteria
	 * @return List<T>
	 */
	protected final List<T> findBy(FindCriteria<T> findCriteria) {
		return !hasFindByFindCriteriaCache ? doFindBy(findCriteria) :
			findByFindCriteriaCache.getOrPut(findCriteria, createValueFindBy);
	}

	private CreateValue<FindCriteria<? super T>, List<T>> createValueFindBy =
		new CreateValue<FindCriteria<? super T>, List<T>>() {
		@Override
		public List<T> create(FindCriteria<? super T> findCriteria) {
			return doFindBy(findCriteria);
		}
	};

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private List<T> doFindBy(FindCriteria<? super T> findCriteria) {
		return dao.findBy(findCriteria);
	}

	/**
	 * Returns the count of all rows returned by the dao.
	 * If hasFindCountCache is true, the value is looked up in cache before using the dao.
	 *
	 * @return long
	 */
	@Override
	public final long findCount() {
		return !hasFindCountCache ? doFindCount() : findCountCache.getOrPut(Boolean.TRUE, createValueFindCount);
	}

	private CreateValue<Boolean, Long> createValueFindCount = new CreateValue<Boolean, Long>() {
		@Override
		public Long create(Boolean key) {
			return doFindCount();
		}
	};

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private long doFindCount() {
		return dao.findCount();
	}

	@Override
	public long findCountBy(PersistableState persistableState) {
		return findCountBy(AbstractPersistable.PERSISTABLE_STATE, persistableState);
	}

	/**
	 * Finds the count of all objects within the inclusive timeCreated range.
	 *
	 * @param timeCreatedStart
	 * @param timeCreatedEnd
	 * @return long
	 */
	@Override
	public final long findCountWithinTimeCreated(Long timeCreatedStart, Long timeCreatedEnd) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
				.whereGreaterThanOrEqualTo(AbstractPersistable.TIME_CREATED, timeCreatedStart)
				.whereLessThanOrEqualTo(AbstractPersistable.TIME_CREATED, timeCreatedEnd).build();

		return findCountBy(findCriteria);
	}

	/**
	 * Finds the count of all objects within the inclusive timeModified range.
	 *
	 * @param timeModifiedStart
	 * @param timeModifiedEnd
	 * @return long
	 */
	@Override
	public final long findCountWithinTimeModified(Long timeModifiedStart, Long timeModifiedEnd) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
				.whereGreaterThanOrEqualTo(AbstractPersistable.TIME_MODIFIED, timeModifiedStart)
				.whereLessThanOrEqualTo(AbstractPersistable.TIME_MODIFIED, timeModifiedEnd).build();

		return findCountBy(findCriteria);
	}

	/**
	 * Returns a the count of recently created entities for the previous 24 hour period.
	 *
	 * @return long
	 */
	@Override
	public final long findRecentlyCreatedCount() {
		DateTime date = new DateTime();
		long startTime = date.minusDays(1).getMillis();
		long endTime = date.getMillis();

		return findCountWithinTimeCreated(startTime, endTime);
	}

	/**
	 * Returns a the count of recently modified entities for the previous 24 hour period.
	 *
	 * @return long
	 */
	@Override
	public final long findRecentlyModifiedCount() {
		DateTime date = new DateTime();
		long startTime = date.minusDays(1).getMillis();
		long endTime = date.getMillis();

		return findCountWithinTimeModified(startTime, endTime);
	}

	/**
	 * Finds the count of results found by the given property and value.
	 *
	 * @param property
	 * @param value
	 * @return long
	 */
	protected final <V> long findCountBy(Property<? super T, V> property, V value) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ONE)
				.whereEqualTo(property, value).build();

		return findCountBy(findCriteria);
	}

	/**
	 * Returns the count of all rows found by the dao.
	 * If hasFindCountByFindCriteriaCache is true, the value is looked up in cache before using the dao.
	 *
	 * @param findCriteria
	 * @return long
	 */
	protected final long findCountBy(FindCriteria<T> findCriteria) {
		return !hasFindCountByFindCriteriaCache ? doFindCountBy(findCriteria) :
			findCountByFindCriteriaCache.getOrPut(findCriteria, createValueFindCountBy);
	}

	private CreateValue<FindCriteria<? super T>, Long> createValueFindCountBy =
		new CreateValue<FindCriteria<? super T>, Long>() {
		@Override
		public Long create(FindCriteria<? super T> findCriteria) {
			return doFindCountBy(findCriteria);
		}
	};

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private long doFindCountBy(FindCriteria<? super T> findCriteria) {
		return dao.findCountBy(findCriteria);
	}

	/**
	 * Returns a new IFindCriteriaBuilder instance from CriteriaParams.
	 *
	 * @param criteriaParams
	 * @return IFindCriteriaBuilder
	 */
	protected final FindCriteriaBuilder<T> findCriteriaBuilder(CriteriaParams<T> criteriaParams) {
		FindCriteriaBuilder<T> findCriteriaBuilder = dao.findCriteriaBuilder(criteriaParams.getResults());

		for(Order<? super T> order : criteriaParams.getOrders())
			if(order.isDescending())
				findCriteriaBuilder.orderByDescending(order.getProperty());
			else
				findCriteriaBuilder.orderByAscending(order.getProperty());

		return findCriteriaBuilder;
	}

	@Override
	public List<T> searchBy(String query, List<Property<T, String>> properties, Results results) {
		return searchBy(dao.searchCriteriaBuilder(query, properties, results).build());
	}

	/**
	 * Finds a List<T> of search results from the database through the dao.
	 * If hasSearchBySearchCriteriaCache is true, the List is checked in cache before using the dao.
	 *
	 * @note The entity must be tagged with Lucene Search annotations to work properly.
	 *
	 * @param searchCriteria
	 * @return List<T>
	 */
	protected final List<T> searchBy(SearchCriteria<T> searchCriteria) {
		return !hasSearchBySearchCriteriaCache ? doSearchBy(searchCriteria) :
			searchBySearchCriteriaCache.getOrPut(searchCriteria, createValueSearch);
	}

	private CreateValue<SearchCriteria<? super T>, List<T>> createValueSearch =
		new CreateValue<SearchCriteria<? super T>, List<T>>() {
		@Override
		public List<T> create(SearchCriteria<? super T> searchCriteria) {
			return doSearchBy(searchCriteria);
		}
	};

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private List<T> doSearchBy(SearchCriteria<? super T> searchCriteria) {
		return dao.searchBy(searchCriteria);
	}

	@Override
	public long searchCountBy(String query, List<Property<T, String>> properties) {
		return searchCountBy(dao.searchCriteriaBuilder(query, properties, Results.ALL).build());
	}

	/**
	 * Returns the count of all search results found by the dao.
	 * If hasSearchCountBySearchCriteriaCache is true, the value is looked up in cache before using the dao.
	 *
	 * @param searchCriteria
	 * @return long
	 */
	protected final long searchCountBy(SearchCriteria<T> searchCriteria) {
		return !hasSearchCountBySearchCriteriaCache ? doSearchCountBy(searchCriteria) :
			searchCountBySearchCriteriaCache.getOrPut(searchCriteria, createValueSearchCountBy);
	}

	private CreateValue<SearchCriteria<? super T>, Long> createValueSearchCountBy =
		new CreateValue<SearchCriteria<? super T>, Long>() {
		@Override
		public Long create(SearchCriteria<? super T> searchCriteria) {
			return doSearchCountBy(searchCriteria);
		}
	};

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private long doSearchCountBy(SearchCriteria<? super T> searchCriteria) {
		return dao.searchCountBy(searchCriteria);
	}

	/**
	 * Returns a new ISearchCriteriaBuilder instance from CriteriaParams.
	 *
	 * @param query
	 * @param criteriaParams
	 * @return ISearchCriteriaBuilder
	 */
	protected final SearchCriteriaBuilder<T> searchCriteriaBuilder(
			String query, List<Property<T, String>> properties, CriteriaParams<T> criteriaParams) {
		return dao.searchCriteriaBuilder(query, properties, criteriaParams.getResults());
	}

	/**
	 * Indexes all entities. This is done in batches to prevent memory issues on larger repositories.
	 *
	 * @note This is necessary if data is added or removed by an external process.
	 *
	 * @param batchSize
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void searchIndexAll(int batchSize) {
		dao.searchIndexAll(batchSize);
	}

	/**
	 * Purges all search indexes.
	 *
	 * @note This is necessary if data is added or removed by an external process.
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void searchIndexPurge() {
		dao.searchIndexPurge();
	}

	/**
	 * Optimizes all search indexes.
	 *
	 * @note This is used to defragment and optimize all existing indexes.
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void searchIndexOptimize() {
		dao.searchIndexOptimize();
	}

	/**
	 * Performs a custom find for an optionally cached find operation, providing transaction and prepare support.
	 * If the cache is not null, the results are retrieved from cache using getOrPut via the ICreateValue implementation.
	 * If the cache is null, the results are retrieved through the ICreateValue implementation.
	 *
	 * @param <K> cache key type
	 * @param cache
	 * @param key
	 * @param createValue
	 * @return List<T>
	 */
	protected final <K extends Serializable, V> List<V> customFind(
			Cache<K, List<V>> cache, K key, final CreateValue<K, List<V>> createValue) {
		return cache == null ? doCustomFind(createValue, key) :
			cache.getOrPut(key, new CreateValue<K, List<V>>() {
				@Override
				public List<V> create(K key) {
					return doCustomFind(createValue, key);
				}
			});
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private <K extends Serializable, V> List<V> doCustomFind(CreateValue<K, List<V>> createValue, K key) {
		return createValue.create(key);
	}

	/**
	 * Performs a custom find for an optionally cached find first operation, providing transaction and prepare support.
	 * If the cache is not null, the result is retrieved from cache using getOrPut via the ICreateValue implementation.
	 * If the cache is null, the result is retrieved through the ICreateValue implementation.
	 *
	 * @param <K> cache key type
	 * @param cache
	 * @param key
	 * @param createValue
	 * @return T
	 */
	protected final <K extends Serializable, V> V customFindFirst(
			Cache<K, V> cache, K key, final CreateValue<K, List<V>> createValue) {
		return cache == null ? doCustomFindFirst(createValue, key) : cache.getOrPut(key, new CreateValue<K, V>() {
			@Override
			public V create(K key) {
				return doCustomFindFirst(createValue, key);
			}
		});
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private <K extends Serializable, V> V doCustomFindFirst(CreateValue<K, List<V>> createValue, K key) {
		return getFirst(createValue.create(key));
	}

	/**
	 * Performs a custom findCount for an optionally cached findCount operation, providing transaction support.
	 * If the countCache is not null, the count is retrieved from cache using getOrPut via the ICreateValue implementation.
	 * If the countCache is null, the count is retrieved through the ICreateValue implementation.
	 *
	 * @param <K> countCache key type
	 * @param countCache
	 * @param key
	 * @param createCount
	 * @return int
	 */
	protected final <K extends Serializable> long customFindCount(
			Cache<K, Long> countCache, K key, final CreateValue<K, Long> createCount) {
		return countCache == null ? doCustomFindCount(createCount, key) : countCache.getOrPut(key, new CreateValue<K, Long>() {
			@Override
			public Long create(K key) {
				return doCustomFindCount(createCount, key);
			}
		});
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private <K extends Serializable> long doCustomFindCount(CreateValue<K, Long> createCount, K key) {
		return createCount.create(key);
	}

	/**
	 * Clears all caches.
	 *
	 */
	protected final void clearAllCaches() {
		clearFindByFindCriteriaCache();
		clearFindCountCache();
		clearFindCountByFindCriteriaCache();
		clearSearchBySearchCriteriaCache();
		clearSearchCountBySearchCriteriaCache();

		onClearAllCaches();
	}

	/**
	 * Hook called when caches are cleared.
	 *
	 */
	protected void onClearAllCaches() {

	}

	/**
	 * Clear the findByFindCriteriaCache, if hasFindByFindCriteriaCache is true.
	 *
	 */
	private final void clearFindByFindCriteriaCache() {
		if(hasFindByFindCriteriaCache)
			findByFindCriteriaCache.clear();
	}

	/**
	 * Clear the findCountCache, if hasFindCountCache is true.
	 *
	 */
	private final void clearFindCountCache() {
		if(hasFindCountCache)
			findCountCache.clear();
	}

	/**
	 * Clear the findCountByFindCriteriaCache, if hasFindCountByFindCriteriaCache is true.
	 *
	 */
	private final void clearFindCountByFindCriteriaCache() {
		if(hasFindCountByFindCriteriaCache)
			findCountByFindCriteriaCache.clear();
	}

	/**
	 * Clear the searchBySearchCriteriaCache, if hasSearchBySearchCriteriaCache is true.
	 *
	 */
	private final void clearSearchBySearchCriteriaCache() {
		if(hasSearchBySearchCriteriaCache)
			searchBySearchCriteriaCache.clear();
	}

	/**
	 * Clear the searchCountBySearchCriteriaCache, if hasSearchCountBySearchCriteriaCache is true.
	 *
	 */
	private final void clearSearchCountBySearchCriteriaCache() {
		if(hasSearchCountBySearchCriteriaCache)
			searchCountBySearchCriteriaCache.clear();
	}

	@Override
	public Class<T> getEntityClass() {
		return dao.getEntityClass();
	}

	// getters and setters
	public final PersistableDao<T> getDao() {
		return dao;
	}

	public final void setDao(PersistableDao<T> persistableDao) {
		this.dao = persistableDao;
	}

	public Cache<Serializable, T> getGetCache() {
		return getCache;
	}

	public void setGetCache(Cache<Serializable, T> getCache) {
		this.getCache = getCache;
	}

	public Cache<Boolean, Long> getFindCountCache() {
		return findCountCache;
	}

	public void setFindCountCache(Cache<Boolean, Long> findCountCache) {
		this.findCountCache = findCountCache;
	}

	public Cache<FindCriteria<? super T>, List<T>> getFindByFindCriteriaCache() {
		return findByFindCriteriaCache;
	}

	public void setFindByFindCriteriaCache(Cache<FindCriteria<? super T>, List<T>> findByFindCriteriaCache) {
		this.findByFindCriteriaCache = findByFindCriteriaCache;
	}

	public Cache<FindCriteria<? super T>, Long> getFindCountByFindCriteriaCache() {
		return findCountByFindCriteriaCache;
	}

	public void setFindCountByFindCriteriaCache(Cache<FindCriteria<? super T>, Long> findCountByFindCriteriaCache) {
		this.findCountByFindCriteriaCache = findCountByFindCriteriaCache;
	}

	public Cache<SearchCriteria<? super T>, List<T>> getSearchBySearchCriteriaCache() {
		return searchBySearchCriteriaCache;
	}

	public void setSearchBySearchCriteriaCache(
			Cache<SearchCriteria<? super T>, List<T>> searchBySearchCriteriaCache) {
		this.searchBySearchCriteriaCache = searchBySearchCriteriaCache;
	}

	public Cache<SearchCriteria<? super T>, Long> getSearchCountBySearchCriteriaCache() {
		return searchCountBySearchCriteriaCache;
	}

	public void setSearchCountBySearchCriteriaCache(
			Cache<SearchCriteria<? super T>, Long> searchCountBySearchCriteriaCache) {
		this.searchCountBySearchCriteriaCache = searchCountBySearchCriteriaCache;
	}

}