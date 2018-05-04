package com.imadp.core.cache;

import java.io.Serializable;
import java.util.List;

import net.sf.ehcache.Element;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * CacheEhCacheImpl
 *
 * The Cache implementation for EhCache.
 *
 * @param <K>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public class CacheEhCacheImpl<K extends Serializable, V> implements Cache<K, V> {
	protected net.sf.ehcache.Cache cache;

	// constructor
	public CacheEhCacheImpl(net.sf.ehcache.Cache cache) {
		this.cache = cache;
	}

	/**
	 * Returns the element retrieved from cache, or null if none exists. Note that the element
	 * value may be wrapped by a lazy instantiation wrapper used from the getOrPut method. If this
	 * is the case, the value must be extracted before it is returned.
	 *
	 * @param key
	 * @return V
	 */
	@Override
	@SuppressWarnings("unchecked")
	public V get(K key) throws CreateValueException {
		Element element = cache.get(key);

		if(element == null)
			return null;

		Object value = element.getObjectValue();

		if(value instanceof CacheValueLazy)
			value = ((CacheValueLazy<K, V>) value).getValue();

		return (V) value;
	}

	@Override
	public void put(K key, V value) {
		cache.put(new Element(key, value));
	}

	/**
	 * Returns a value from cache, or if no key exists, a value is created through the
	 * ICreateValue<V> implementation, put into cache, and then returned.
	 *
	 * @note As an optimization we call cache.get(key) before using putIfAbsent.
	 * 		 Since all values are wrapped with CacheValue objects (and ConcurrentHashMap does
	 * 		 not allow nulls), a null check is sufficient to see if the value exists in cache.
	 *
	 * @param key
	 * @param createValue
	 * @throws CreateValueException if unable to create the value
	 * @return <V> value
	 */
	@Override
	@SuppressWarnings("unchecked")
	public V getOrPut(K key, CreateValue<K, V> createValue) {
		Element element = cache.get(key);

		// if the element was not found in cache, we put it in atomically
		if(element == null)
		{
			Element cacheElement = new Element(key, new CacheValueLazy<>(createValue, key));
			element = cache.putIfAbsent(cacheElement);

			// if an element was returned (put by another thread) we use that one
			if(element == null)
				element = cacheElement;
		}

		// the object may be the direct value if put was called earlier, or a wrapped CacheValueLazy
		Object value = element.getObjectValue();

		if(value instanceof CacheValueLazy)
		{
			try
			{
				value = ((CacheValueLazy<K, V>) value).getValue();
			}
			catch(CreateValueException createValueException)
			{
				cache.remove(key);
				throw createValueException;
			}

		}

		return (V) value;
	}

	@Override
	public void remove(K key) {
		cache.remove(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<K> getKeys() {
		return cache.getKeys();
	}

	@Override
	public boolean isKeyInCache(K key) {
		return cache.isKeyInCache(key);
	}

	@Override
	public void clear() {
		cache.removeAll();
	}

	@Override
	public int getSize() {
		return cache.getSize();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("cache", cache)
		.toString();
	}

}