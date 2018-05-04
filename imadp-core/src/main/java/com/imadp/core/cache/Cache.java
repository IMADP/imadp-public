package com.imadp.core.cache;

import java.io.Serializable;
import java.util.List;

/**
 * Cache
 *
 * An interface representing a type-safe cache implementation.
 *
 * @param <K>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface Cache<K extends Serializable, V> {

	/**
	 * Returns a value from cache.
	 *
	 * @param key
	 * @throws CreateValueException if getOrPut was used and the value could not be created.
	 * @return <V> value
	 */
	public V get(K key) throws CreateValueException;

	/**
	 * Puts a value in cache.
	 *
	 * @param  key
	 * @param value
	 */
	public void put(K key, V value);

	/**
	 * Returns a value from cache, or if no key exists, a value is created through the
	 * ICreateValue<V> implementation, put into cache, and then returned.
	 *
	 * @param key
	 * @param createValue
	 * @throws CreateValueException if unable to create the value
	 * @return <V> value
	 */
	public V getOrPut(K key, CreateValue<K, V> createValue);

	/**
	 * Removes a value from cache, if it exists.
	 *
	 * @param key
	 */
	public void remove(K key);

	/**
	 * Gets a List of all the keys in cache.
	 *
	 * @return List
	 */
	public List<K> getKeys();

	/**
	 * Returns true if a key is found in cache, false otherwise.
	 *
	 * @param key
	 * @return true if a key is found in cache, false otherwise.
	 */
	public boolean isKeyInCache(K key);

	/**
	 * Returns the size or number of keys in cache.

	 * @return int
	 */
	public int getSize();

	/**
	 * Clears the cache.
	 *
	 */
	public void clear();

}
