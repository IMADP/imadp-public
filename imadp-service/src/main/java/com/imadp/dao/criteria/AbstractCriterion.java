package com.imadp.dao.criteria;

import com.imadp.dao.Persistable;

/**
 * AbstractCriterion
 *
 * A skeletal implementation of the Criterion interface. Subclasses should extend this to ensure
 * mandatory method signatures are overridden.
 *
 * @note All subclass should promise to provide immutability and thread-safe extentions.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractCriterion<T extends Persistable, V> implements Criterion<T, V> {

	/**
	 * Mandatory toString implementation.
	 *
	 * @return String
	 */
	@Override
	public abstract String toString();

	/**
	 * Mandatory hashCode implementation.
	 *
	 * @return int
	 */
	@Override
	public abstract int hashCode();

	/**
	 * Mandatory equals implementation.
	 *
	 * @param object
	 * @return boolean
	 */
	@Override
	public abstract boolean equals(Object object);

}
