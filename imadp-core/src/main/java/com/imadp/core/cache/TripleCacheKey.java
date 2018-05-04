package com.imadp.core.cache;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * TripleCacheKey
 *
 * An object encapsulating three cache keys, useful when a simple cache key is unsufficient.
 *
 * @note This class is immutable and thread-safe.
 * @note To ensure consistent results, prefer to use immutable objects as keys, or failing that,
 * 		 ensure that the equals/hashCode implementations of your keys are not dependent on mutable data.
 *
 * @param <K>
 * @param <L>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public class TripleCacheKey<K extends Serializable, L extends Serializable, V extends Serializable>
	implements Serializable {

	/**
	 * Factory method for creating new TripleCacheKey instances.
	 *
	 * @param <K> type of key one
	 * @param <L> type of key two
	 * @param <V> type of key three
	 * @param keyOne
	 * @param keyTwo
	 * @param keyThree
	 * @return TripleCacheKey
	 */
	public static <K extends Serializable, L extends Serializable, V extends Serializable> TripleCacheKey<K, L, V>
		of(K keyOne, L keyTwo, V keyThree) {
		return new TripleCacheKey<>(keyOne, keyTwo, keyThree);
	}

	// properties
	private K keyOne;
	private L keyTwo;
	private V keyThree;

	// constructor
	private TripleCacheKey(K keyOne, L keyTwo, V keyThree) {
		this.keyOne = keyOne;
		this.keyTwo = keyTwo;
		this.keyThree = keyThree;
	}

	// getters and setters
	public K getKeyOne() {
		return keyOne;
	}

	public L getKeyTwo() {
		return keyTwo;
	}

	public V getKeyThree() {
		return keyThree;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;

		if (object == this)
			return true;

		if (object.getClass() != getClass())
			return false;

		TripleCacheKey<?, ?, ?> tripleCacheKey = (TripleCacheKey<?, ?, ?>) object;

		return new EqualsBuilder()
		.append(keyOne, tripleCacheKey.keyOne)
		.append(keyTwo, tripleCacheKey.keyTwo)
		.append(keyThree, tripleCacheKey.keyThree)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(keyOne)
		.append(keyTwo)
		.append(keyThree)
		.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append(keyOne)
		.append(keyTwo)
		.append(keyThree)
		.toString();
	}

}