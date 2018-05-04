package com.imadp.core.cache;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * CacheValue
 *
 * An object encapsulating a cached value. This provides the ability to cache null objects.
 *
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public class CacheValue<V> {
	protected V value;

	// constructor
	public CacheValue() {

	}

	// constructor
	public CacheValue(V value) {
		this.value = value;
	}

	// getters
	public V getValue() {
		return value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append(value)
		.toString();
	}

}