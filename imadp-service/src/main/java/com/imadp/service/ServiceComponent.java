package com.imadp.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.imadp.core.CoreComponent;
import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CacheConcurrentImpl;
import com.imadp.core.cache.CreateValue;

/**
 * ServiceComponent
 *
 * ServiceComponent is an extention of CoreComponent that provides a base class for all
 * custom service implementations.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class ServiceComponent extends CoreComponent {

	/**
	 * Returns the first element in a list, or null if the list is null or empty.
	 *
	 * @param <T> the type of the object to be returned
	 * @param list
	 * @return T
	 */
	protected final <T> T getFirst(List<T> list) {
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	/**
	 * Returns a sub cache from a parent cache with the provided key, or creates one if one was not found.
	 * The provided sub cache implementation is backed by a CacheConcurrentImpl, which provides a memory safe
	 * cache by utilizing SoftReference values.
	 *
	 * Sub caches are useful when a cache can benefit from isolated divisions that can all be cleared uniformly
	 * through the parent cache. For example, imagine a query that finds results by a specific user id, but with
	 * non-specific SortParams and/or ResultParams. The variations of results can be stored within a sub-cache,
	 * while the parent cache can maintain a single key of the user id and a single value of the
	 * subcache. This allows for smarter cache management, as the results from different user ids
     * be modified independently of others.
	 *
	 * @param <P> the parentCache key type
	 * @param <K> the subCache key type
	 * @param <V> the subCache value type
	 * @param parentCache
	 * @param key
	 * @return Cache or <code>null</code> if no parent cache exists
	 */
	protected final <P extends Serializable, K extends Serializable, V> Cache<K, V> getSubCache(
			Cache<P, Cache<K, V>> parentCache, P key) {

		// if no key or parent cache exists, return null
		if(key == null || parentCache == null)
			return null;

		return parentCache.getOrPut(key, new CreateValue<P, Cache<K, V>>() {
			@Override
			public Cache<K, V> create(P key) {
				return new CacheConcurrentImpl<>();
			}
		});
	}

}