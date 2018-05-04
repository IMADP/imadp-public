package com.imadp.core.cache;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * DoubleCacheKey
 *
 * An object encapsulating two cache keys, useful when a simple cache key is unsufficient.
 *
 * @note This class is immutable and thread-safe.
 * @note To ensure consistent results, prefer to use immutable objects as keys, or failing that,
 * 		 ensure that the equals/hashCode implementations of your keys are not dependent on mutable data.
 *
 * @param <K>
 * @param <L>
 * @version 1.0
 * @author Anthony DePalma
 */
public class DoubleCacheKey<K extends Serializable, L extends Serializable> implements Serializable {

	/**
	 * Factory method for creating new DoubleCacheKey instances.
	 *
	 * @param <K> type of key one
	 * @param <L> type of key two
	 * @param keyOne
	 * @param keyTwo
	 * @return DoubleCacheKey
	 */
	public static <K extends Serializable, L extends Serializable> DoubleCacheKey<K, L> of(K keyOne, L keyTwo) {
		return new DoubleCacheKey<>(keyOne, keyTwo);
	}

	// properties
	private K keyOne;
	private L keyTwo;

	// constructor
	private DoubleCacheKey(K keyOne, L keyTwo) {
		this.keyOne = keyOne;
		this.keyTwo = keyTwo;
	}

	// getters and setters
	public K getKeyOne() {
		return keyOne;
	}

	public L getKeyTwo() {
		return keyTwo;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;

		if (object == this)
			return true;

		if (object.getClass() != getClass())
			return false;

		DoubleCacheKey<?, ?> doubleCacheKey = (DoubleCacheKey<?, ?>) object;

		return new EqualsBuilder()
		.append(keyOne, doubleCacheKey.keyOne)
		.append(keyTwo, doubleCacheKey.keyTwo)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(keyOne)
		.append(keyTwo)
		.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append(keyOne)
		.append(keyTwo)
		.toString();
	}

}