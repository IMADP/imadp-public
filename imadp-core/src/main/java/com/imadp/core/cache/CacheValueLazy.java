package com.imadp.core.cache;


/**
 * CacheValueLazy
 *
 * An extention of the CacheValue to allow the lazy instantiation of cached values.
 *
 * @param <K>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CacheValueLazy<K, V> extends CacheValue<V> {

	// the interface to create the cached value
	private CreateValue<K, V> createValue;

	// the cache entry key
	private K key;

	/*
	 * This boolean is used as a flag to determine if the value was created.
	 * This is required rather than using a null check because a null value
	 * may be the result that is meant to be stored in cache.
	 *
	 */
	private boolean created;

	// constructor
	public CacheValueLazy(CreateValue<K, V> createValue, K key) {
		this.createValue = createValue;
		this.key = key;
	}

	@Override
	public V getValue() throws CreateValueException {

		synchronized(this)
		{
			// if the object has not been created, nows the time.
			if(!created)
			{
				try
				{
					value = createValue.create(key);
				}
				catch(Exception exception)
				{
					throw new CreateValueException(exception);
				}

				created = true;
			}
		}

		return value;
	}

}