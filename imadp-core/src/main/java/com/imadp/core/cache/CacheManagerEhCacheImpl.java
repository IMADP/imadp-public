package com.imadp.core.cache;

import java.io.Serializable;

import org.apache.commons.lang.Validate;

import com.imadp.core.CoreComponent;


/**
 * CacheManagerEhCacheImpl
 *
 * The EhCache implementation of the CacheManager.
 *
 * @note This component is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CacheManagerEhCacheImpl extends CoreComponent implements CacheManager {
	private net.sf.ehcache.CacheManager ehCacheManager;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(ehCacheManager);
	}

	@Override
	public <K extends Serializable, V> Cache<K, V> getCache(String cacheName) {
		net.sf.ehcache.Cache cache = ehCacheManager.getCache(cacheName);

		if(cache == null)
			throw new IllegalArgumentException("There is no cache configured for cacheName ["+cacheName+"]");

		return new CacheEhCacheImpl<>(cache);
	}

	@Override
	public void addCache(String cacheName) {
		ehCacheManager.addCache(cacheName);
	}

	@Override
	public int getSize() {
		return ehCacheManager.getCacheNames().length;
	}

	@Override
	public void removeAll() {
		logger.info("Removing all caches");

		ehCacheManager.removalAll();
	}

	@Override
	public void removeCache(String cacheName) {
		logger.info("Removing cache ["+cacheName+"]");

		ehCacheManager.removeCache(cacheName);
	}

	@Override
	public boolean hasCache(String cacheName) {
		return ehCacheManager.cacheExists(cacheName);
	}

	@Override
	public void clearAll() {
		logger.info("Clearing all caches");

		ehCacheManager.clearAll();
	}

	// getters and setters
	public net.sf.ehcache.CacheManager getEhCacheManager() {
		return ehCacheManager;
	}

	public void setEhCacheManager(net.sf.ehcache.CacheManager ehCacheManager) {
		this.ehCacheManager = ehCacheManager;
	}

}
