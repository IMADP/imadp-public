package com.imadp.core.cache;

import java.io.Serializable;



/**
 * ICacheManager
 *
 * The ICacheManager manages and provides ICache instances.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CacheManager {

	/**
	 * Gets an ICache instance by cacheName.
	 *
	 * @param <K> key
	 * @param <V> value
	 * @param cacheName
	 * @return ICache
	 */
	public <K extends Serializable, V> Cache<K, V> getCache(String cacheName);

	/**
	 * Adds an ICache instance with a corresponding cacheName.
	 *
	 * @param <K> key
	 * @param <V> value
	 * @param cacheName
	 */
	public <K extends Serializable, V> void addCache(String cacheName);

	/**
	 * Removes an ICache instance by the cacheName.
	 *
	 * @param cacheName
	 */
	public void removeCache(String cacheName);

	/**
	 * Removes all ICache instances managed by this service.
	 *
	 */
	public void removeAll();

	/**
	 * Returns the number of current ICache instances managed by this service.
	 *
	 * @return int
	 */
	public int getSize();

	/**
	 * Returns true if a cache is found by cacheName, false otherwise.
	 *
	 * @param cacheName
	 * @return boolean
	 */
	public boolean hasCache(String cacheName);

	/**
	 * Clear all ICache instances managed by this service.
	 *
	 * @note This method will not remove any caches from the cache manager.
	 *
	 */
	public void clearAll();

}
