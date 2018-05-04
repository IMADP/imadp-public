package com.imadp.service.tag;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.core.cache.DoubleCacheKey;
import com.imadp.core.cache.TripleCacheKey;
import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableDao;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.PersistableUserServiceImpl;
import com.imadp.service.user.User;


/**
 * TagServiceImpl
 *
 * The standard implementation of the TagService.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public class TagServiceImpl<T extends AbstractTag<V>, V extends Persistable> extends PersistableUserServiceImpl<T>
		implements TagService<T, V> {

	private TagDao<T> tagDao;

	// optional caches
	private Cache<User, Cache<TripleCacheKey<User, PersistableState, CriteriaParams<TagFrequency>>, List<TagFrequency>>> findTagFrequenciesByUserCache;
	private Cache<User, Cache<DoubleCacheKey<User, PersistableState>, Long>> findTagFrequencyCountByUserCache;

	@Override
	protected PersistableDao<T> getPersistableDao() {
		return tagDao;
	}

	@Override
	protected void onAfterSave(T tag, boolean initialSave) {
		super.onAfterSave(tag, initialSave);

		// remove the findTagFrequenciesByUserCache
		if(findTagFrequenciesByUserCache != null)
			findTagFrequenciesByUserCache.remove(tag.getUser());

		// remove the findTagFrequencyCountByUserCache
		if(findTagFrequencyCountByUserCache != null)
			findTagFrequencyCountByUserCache.remove(tag.getUser());
	}

	@Override
	protected void onAfterDelete(T tag) {
		super.onAfterDelete(tag);

		// remove the findTagFrequenciesByUserCache
		if(findTagFrequenciesByUserCache != null)
			findTagFrequenciesByUserCache.remove(tag.getUser());

		// remove the findTagFrequencyCountByUserCache
		if(findTagFrequencyCountByUserCache != null)
			findTagFrequencyCountByUserCache.remove(tag.getUser());
	}

	@Override
	public List<T> findBy(V taggable, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(T.TAGGABLE, taggable).build();

		return findBy(findCriteria);
	}

	@Override
	public long findCountBy(V taggable) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ONE))
				.whereEqualTo(T.TAGGABLE, taggable).build();

		return findCountBy(findCriteria);
	}

	@Override
	public List<T> findByUser(User user, String nameSlug, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(T.USER, user)
				.whereEqualTo(T.NAME_SLUG, nameSlug).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findCountByUser(User user, String nameSlug) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ONE))
				.whereEqualTo(T.USER, user)
				.whereEqualTo(T.NAME_SLUG, nameSlug).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	public List<T> findByUser(User user, String nameSlug, PersistableState persistableState, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(T.USER, user)
				.whereEqualTo(T.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(T.NAME_SLUG, nameSlug).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findCountByUser(User user, String nameSlug, PersistableState persistableState) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ONE))
				.whereEqualTo(T.USER, user)
				.whereEqualTo(T.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(T.NAME_SLUG, nameSlug).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	public List<TagFrequency> findTagFrequencies(User user, CriteriaParams<TagFrequency> criteriaParams) {
		return findTagFrequencies(user, null, criteriaParams);
	}

	@Override
	public long findTagFrequencyCount(User user) {
		return findTagFrequencyCount(user, null);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TagFrequency> findTagFrequencies(
			User user, PersistableState persistableState, CriteriaParams<TagFrequency> criteriaParams) {

		TripleCacheKey<User, PersistableState, CriteriaParams<TagFrequency>> key = TripleCacheKey.of(
				user, persistableState, criteriaParams);

		return customFind(getSubCache(findTagFrequenciesByUserCache, user),
				key, createValueFindTagFrequenciesByUser);
	}

	private CreateValue<TripleCacheKey<User, PersistableState, CriteriaParams<TagFrequency>>, List<TagFrequency>> createValueFindTagFrequenciesByUser =
		new CreateValue<TripleCacheKey<User, PersistableState, CriteriaParams<TagFrequency>>, List<TagFrequency>>() {
		@Override
		public List<TagFrequency> create(TripleCacheKey<User, PersistableState, CriteriaParams<TagFrequency>> key) {
			User user = key.getKeyOne();
			PersistableState persistableState = key.getKeyTwo();
			CriteriaParams<TagFrequency> criteriaParams = key.getKeyThree();
			return tagDao.findTagFrequencies(user, persistableState, criteriaParams);
		}
	};

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long findTagFrequencyCount(User user, PersistableState persistableState) {
		DoubleCacheKey<User, PersistableState> key = DoubleCacheKey.of(user, persistableState);

		return customFindCount(getSubCache(findTagFrequencyCountByUserCache, user),
				key, createValueFindTagFrequencyCountByUser);
	}

	private CreateValue<DoubleCacheKey<User, PersistableState>, Long> createValueFindTagFrequencyCountByUser =
		new CreateValue<DoubleCacheKey<User, PersistableState>, Long>() {
		@Override
		public Long create(DoubleCacheKey<User, PersistableState> key) {
			User user = key.getKeyOne();
			PersistableState persistableState = key.getKeyTwo();
			return tagDao.findTagFrequencyCount(user, persistableState);
		}
	};

	// getters and setters
	public TagDao<T> getTagDao() {
		return tagDao;
	}

	public void setTagDao(TagDao<T> tagDao) {
		this.tagDao = tagDao;
	}

	public Cache<User, Cache<TripleCacheKey<User, PersistableState, CriteriaParams<TagFrequency>>, List<TagFrequency>>>
		getFindTagFrequenciesByUserCache() {
		return findTagFrequenciesByUserCache;
	}

	public void setFindTagFrequenciesByUserCache(Cache<User, Cache<TripleCacheKey<User, PersistableState,
			CriteriaParams<TagFrequency>>, List<TagFrequency>>>	findTagFrequenciesByUserCache) {
		this.findTagFrequenciesByUserCache = findTagFrequenciesByUserCache;
	}

	public Cache<User, Cache<DoubleCacheKey<User, PersistableState>, Long>> getFindTagFrequencyCountByUserCache() {
		return findTagFrequencyCountByUserCache;
	}

	public void setFindTagFrequencyCountByUserCache(Cache<User, Cache<DoubleCacheKey<User, PersistableState>, Long>>
			findTagFrequencyCountByUserCache) {
		this.findTagFrequencyCountByUserCache = findTagFrequencyCountByUserCache;
	}

}
