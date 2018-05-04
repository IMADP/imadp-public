package com.imadp.core.cache;

/**
 * CreateValue
 *
 * An interface used by the ICache getOrPut method to create a cache value.
 *
 * @param <K>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CreateValue<K, V> {

	/**
	 * Creates a value to be stored in cache.
	 *
	 * @param key
	 * @return <V> value
	 */
	public V create(K key);

}