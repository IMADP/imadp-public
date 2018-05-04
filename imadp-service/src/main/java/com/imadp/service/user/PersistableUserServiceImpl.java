package com.imadp.service.user;

import java.util.List;

import com.imadp.core.Property;
import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.PersistableServiceImpl;

/**
 * PersistableServiceUserImpl
 *
 * An extention of the PersistableService to offer commonly used functionality for objects
 * with User references.
 *
 * This class provides two important methods, findBy(user, findCriteria)
 * and findCountBy(user, findCriteria). These protected methods provide an advantage for
 * AbstractPersistableUser objects by using a subcache, with the user as the key. This allows
 * caches to be cleared on a per user basis, rather than for any arbitrary IFindCriteria. In other
 * words it means that saving objects belonging to one user will not remove cached data for
 * a different user.
 *
 * @note This class makes the important assumption that a User reference itself will remain
 * 		 unmodifiable. In other words, a User reference will never swap from being null to non null,
 * 		 or swap from one User to another User on any update.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableUserServiceImpl<T extends AbstractPersistableUser>
	extends PersistableServiceImpl<T> implements PersistableUserService<T> {

	// optional cache instances
	private Cache<User, Cache<FindCriteria<T>, List<T>>> findByUserFindCriteriaCache;
	private Cache<User, Cache<FindCriteria<T>, Long>> findCountByUserFindCriteriaCache;

	// control booleans
	private boolean hasFindByUserFindCriteriaCache;
	private boolean hasFindCountByUserFindCriteriaCache;

	@Override
	protected void onInit() {
		super.onInit();

		// initialize control booleans
		hasFindByUserFindCriteriaCache = findByUserFindCriteriaCache != null;
		hasFindCountByUserFindCriteriaCache = findCountByUserFindCriteriaCache != null;
	}

	/**
	 * Returns the first result found by user and a given property, or null if none was found.
	 *
	 * @param <V>
	 * @param user
	 * @param property
	 * @param value
	 * @return T
	 */
	protected final <V> T findFirstByUser(User user, Property<? super T, V> property, V value) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ONE)
				.whereEqualTo(AbstractPersistableUser.USER, user)
				.whereEqualTo(property, value).build();

		return getFirst(findByUser(user, findCriteria));
	}

	@Override
	public T findFirstByUser(User user, Long id) {
		return findFirstByUser(user, AbstractPersistableUser.ID, id);
	}

	@Override
	public T findFirstByUser(User user, String uid) {
		return findFirstByUser(user, AbstractPersistableUser.UID, uid);
	}

	/**
	 * Finds a List<T> of objects found by user and findCriteria.
	 * This method is provided to allow automatic subcaching on any findCriteria.
	 *
	 * The results of a query are stored into a subcache of the findByUserFindCritieriaCache,
	 * if available, by using the user as the key to the subcache. This is encouraged because it
	 * allows more intelligent cache clearing on saves and deletes.
	 *
	 * @note If the user reference is null, nothing will be cached.
	 *
	 * @param user
	 * @param findCriteria
	 * @return List<T>
	 */
	protected final List<T> findByUser(User user, final FindCriteria<T> findCriteria) {
		return customFind(getSubCache(findByUserFindCriteriaCache, user),
				findCriteria, createValueFindByUser);
	}

	private CreateValue<FindCriteria<T>, List<T>> createValueFindByUser =
		new CreateValue<FindCriteria<T>, List<T>>() {
		@Override
		public List<T> create(FindCriteria<T> findCriteria) {
			return dao.findBy(findCriteria);
		}
	};

	/**
	 * Finds a count of objects found by user and findCriteria.
	 * This method is provided to allow automatic subcaching on any findCriteria.
	 *
	 * The results of a query are stored into a subcache of the findCountByUserFindCritieriaCache,
	 * if available, by using the user as the key to the subcache. This is encouraged because it
	 * allows more intelligent cache clearing on saves and deletes.
	 *
	 * @param user
	 * @param findCriteria
	 * @return int
	 */
	protected final long findCountByUser(User user, final FindCriteria<T> findCriteria) {
		return customFindCount(getSubCache(findCountByUserFindCriteriaCache, user),
				findCriteria, createValueFindCountByUser);
	}

	private CreateValue<FindCriteria<T>, Long> createValueFindCountByUser =
		new CreateValue<FindCriteria<T>, Long>() {
		@Override
		public Long create(FindCriteria<T> findCriteria) {
			return dao.findCountBy(findCriteria);
		}
	};

	@Override
	public final T findFirstByUser(User user, Order<T> order) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.of(Results.ONE, order))
				.whereEqualTo(AbstractPersistableUser.USER, user).build();

		return getFirst(findByUser(user, findCriteria));
	}

	@Override
	public final T findFirstByUser(User user) {
		return findFirstByUser(user, ((Order<T>)null));
	}

	@Override
	public boolean isFoundByUser(User user) {
		return findFirstByUser(user) != null;
	}

	@Override
	public final List<T> findByUser(User user, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(AbstractPersistableUser.USER, user).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public List<T> findByUser(User user, PersistableState persistableState, CriteriaParams<T> criteriaParams) {
		return findByUser(user, AbstractPersistableUser.PERSISTABLE_STATE,  persistableState, criteriaParams);
	}

	/**
	 * Finds a list of results found by the given user, property and value, and criteriaParams.
	 *
	 * @param user
	 * @param property
	 * @param value
	 * @param criteriaParams
	 * @return long
	 */
	protected final <V> List<T> findByUser(User user, Property<? super T, V> property, V value,
			CriteriaParams<T> criteriaParams) {

		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(AbstractPersistableUser.USER, user)
				.whereEqualTo(property, value).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public final List<T> findByUserWithinTimeCreated(User user, Long timeCreatedStart, Long timeCreatedEnd,
			CriteriaParams<T> criteriaParams) {

		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(AbstractPersistableUser.USER, user)
			.whereGreaterThanOrEqualTo(AbstractPersistableUser.TIME_CREATED, timeCreatedStart)
			.whereLessThanOrEqualTo(AbstractPersistableUser.TIME_CREATED, timeCreatedEnd).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public final List<T> findByUserWithinTimeModified(User user, Long timeModifiedStart, Long timeModifiedEnd,
			CriteriaParams<T> criteriaParams) {

		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(AbstractPersistableUser.USER, user)
			.whereGreaterThanOrEqualTo(AbstractPersistableUser.TIME_MODIFIED, timeModifiedStart)
			.whereLessThanOrEqualTo(AbstractPersistableUser.TIME_MODIFIED, timeModifiedEnd).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public final long findCountByUser(User user) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
				.whereEqualTo(AbstractPersistableUser.USER, user).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	public long findCountByUser(User user, PersistableState persistableState) {
		return findCountByUser(user, AbstractPersistableUser.PERSISTABLE_STATE, persistableState);
	}

	/**
	 * Finds the count of results found by the given user, and property and value.
	 *
	 * @param user
	 * @param property
	 * @param value
	 * @return long
	 */
	protected final <V> long findCountByUser(User user, Property<? super T, V> property, V value) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ONE)
			.whereEqualTo(AbstractPersistableUser.USER, user)
			.whereEqualTo(property, value).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	public final long findCountByUserWithinTimeCreated(User user, Long timeCreatedStart, Long timeCreatedEnd) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
			.whereEqualTo(AbstractPersistableUser.USER, user)
			.whereGreaterThanOrEqualTo(AbstractPersistableUser.TIME_CREATED, timeCreatedStart)
			.whereLessThanOrEqualTo(AbstractPersistableUser.TIME_CREATED, timeCreatedEnd).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	public final long findCountByUserWithinTimeModified(User user, Long timeModifiedStart, Long timeModifiedEnd) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
			.whereEqualTo(AbstractPersistableUser.USER, user)
			.whereGreaterThanOrEqualTo(AbstractPersistableUser.TIME_MODIFIED, timeModifiedStart)
			.whereLessThanOrEqualTo(AbstractPersistableUser.TIME_MODIFIED, timeModifiedEnd).build();

		return findCountByUser(user, findCriteria);
	}

	/**
	 * When a PersistableUser object is saved, we clear the caches for a specific user.
	 *
	 */
	@Override
	protected void onAfterSave(T persistable, boolean initialSave) {
		super.onAfterSave(persistable, initialSave);

		clearUserCaches(persistable.getUser());
	}

	/**
	 * When a PersistableUser object is saved, we clear the caches for a specific user.
	 * If no user is present, no caches will need to be cleared as there will be no values.
	 *
	 */
	@Override
	protected void onAfterDelete(T persistable) {
		super.onAfterDelete(persistable);

		clearUserCaches(persistable.getUser());
	}

	/**
	 * Clears the user specific caches for a given user.
	 * This method is often necessary when reference data from other services has been modified.
	 * In those scenarios, the values stored in cache for a particular user may become outdated.
	 *
	 * @param user
	 */
	protected final void clearUserCaches(User user) {
		if(user != null)
		{
			if(hasFindByUserFindCriteriaCache)
				findByUserFindCriteriaCache.remove(user);

			if(hasFindCountByUserFindCriteriaCache)
				findCountByUserFindCriteriaCache.remove(user);
		}
	}

	@Override
	protected void onClearAllCaches() {
		if(hasFindByUserFindCriteriaCache)
			findByUserFindCriteriaCache.clear();

		if(hasFindCountByUserFindCriteriaCache)
			findCountByUserFindCriteriaCache.clear();
	}

	// getters and setters
	public Cache<User, Cache<FindCriteria<T>, List<T>>> getFindByUserFindCriteriaCache() {
		return findByUserFindCriteriaCache;
	}

	public void setFindByUserFindCriteriaCache(Cache<User,
			Cache<FindCriteria<T>, List<T>>> findByUserFindCriteriaCache) {
		this.findByUserFindCriteriaCache = findByUserFindCriteriaCache;
	}

	public Cache<User, Cache<FindCriteria<T>, Long>> getFindCountByUserFindCriteriaCache() {
		return findCountByUserFindCriteriaCache;
	}

	public void setFindCountByUserFindCriteriaCache(
			Cache<User, Cache<FindCriteria<T>, Long>> findCountByUserFindCriteriaCache) {
		this.findCountByUserFindCriteriaCache = findCountByUserFindCriteriaCache;
	}

}