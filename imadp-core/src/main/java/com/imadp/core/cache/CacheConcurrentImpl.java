package com.imadp.core.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.collect.MapMaker;

/**
 * CacheConcurrentImpl
 *
 * A simple ICache implementation, backed by a ConcurrentMap for thread-safe modification.
 * The map implementation is provided by Google Collections, and allows the use of SoftReferences for
 * all stored values. This essentially provides automatic cache clearing when memory is low.
 *
 * @param <K>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public class CacheConcurrentImpl <K extends Serializable, V> implements Cache<K, V> {

	// properties
	protected ConcurrentMap<K, CacheValue<V>> cache;

	// constructor
	public CacheConcurrentImpl() {
		this.cache = new MapMaker().softValues().makeMap();
	}

	@Override
	public V get(K key) throws CreateValueException {
		CacheValue<V> element = cache.get(key);
		return element == null ? null : element.getValue();
	}

	@Override
	public void put(K key, V value) {
		cache.put(key, new CacheValue<>(value));
	}

	/**
	 * Returns a value from cache, or if no key exists, a value is created through the
	 * ICreateValue<V> implementation, put into cache, and then returned.
	 *
	 * @note As an optimization we call cache.get(key) before using putIfAbsent.
	 * 		 Since all values are wrapped with CacheValue objects (and ConcurrentHashMap does
	 * 		 not allow nulls), a null check is sufficient to see if the value exists in cache.
	 *
	 * @note In the event of a CreateValueException, we remove the object from cache.
	 *
	 * @param key
	 * @param createValue
	 * @throws CreateValueException if unable to create the value
	 * @return <V> value
	 */
	@Override
	public V getOrPut(K key, CreateValue<K, V> createValue) {
		CacheValue<V> element = cache.get(key);

		// if the element was not found in cache, we put it in atomically
		if(element == null)
		{
			CacheValue<V> cacheElement = new CacheValueLazy<>(createValue, key);
			element = cache.putIfAbsent(key, cacheElement);

			// if an element was not returned (put by another thread) we use our cacheElement
			if(element == null)
				element = cacheElement;
		}

		try
		{
			return element.getValue();
		}
		catch(CreateValueException createValueException)
		{
			cache.remove(key);
			throw createValueException;
		}

	}

	@Override
	public void remove(K key) {
		cache.remove(key);
	}

	@Override
	public List<K> getKeys() {
		return new ArrayList<>(cache.keySet());
	}

	@Override
	public boolean isKeyInCache(K key) {
		return cache.containsKey(key);
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public int getSize() {
		return cache.size();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("size", getSize())
		.toString();
	}

}