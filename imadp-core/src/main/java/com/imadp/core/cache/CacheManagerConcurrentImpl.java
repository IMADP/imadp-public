package com.imadp.core.cache;

import java.io.Serializable;

import com.imadp.core.CoreComponent;


/**
 * CacheManagerConcurrentImpl
 *
 * The CacheConcurrentImpl implementation of the CacheManager.
 *
 * @note This component is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CacheManagerConcurrentImpl extends CoreComponent implements CacheManager {
	private Cache<String, Cache<?,?>> cacheManager = new CacheConcurrentImpl<>();

	@Override
	@SuppressWarnings("unchecked")
	public <K extends Serializable, V> Cache<K, V> getCache(String cacheName) {
		if(!hasCache(cacheName))
			addCache(cacheName);

		return (Cache<K, V>) cacheManager.get(cacheName);
	}

	@Override
	public void addCache(String cacheName) {
		cacheManager.put(cacheName, new CacheConcurrentImpl<String, Cache<?,?>>());
	}

	@Override
	public int getSize() {
		return cacheManager.getSize();
	}

	@Override
	public boolean hasCache(String cacheName) {
		return cacheManager.isKeyInCache(cacheName);
	}

	@Override
	public void removeAll() {
		logger.info("Removing all caches");

		cacheManager.clear();
	}

	@Override
	public void removeCache(String cacheName) {
		logger.info("Removing cache ["+cacheName+"]");

		cacheManager.remove(cacheName);
	}

	@Override
	public void clearAll() {
		logger.info("Clearing all caches");

		for(String key : cacheManager.getKeys())
			cacheManager.get(key).clear();
	}

}
