package com.imadp.dao.criteria;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.imadp.dao.Persistable;

/**
 * AbstractCriteria
 *
 * The parent class for criteria implementations, providing the criteria list and hashCode caching.
 *
 * @note All subclass should promise to provide immutability and thread-safe extentions.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractCriteria<T extends Persistable> implements Serializable {

	// properties
	private final List<Criterion<T, ?>> criteria;

	// cached hashCode
	private volatile int hashCode;

	// constructor
	public AbstractCriteria(List<Criterion<T, ?>> criteria) {
		this.criteria = Collections.unmodifiableList(criteria);
	}

	// getters
	public List<Criterion<T, ?>> getCriteria() {
		return criteria;
	}

	@Override
	public final int hashCode() {
		if(hashCode == 0)
			hashCode = getHashCode();

		return hashCode;
	}

	/**
	 * Returns the hashCode to be cached. This method should be overriden by subclasses
	 * instead of the traditional hashCode method to ensure that the hashcode is cached.
	 *
	 * @note Subclasses overriding this method should make sure NOT call super.hashCode(),
	 * 		 but instead call super.getHashCode() to prevent a method stack loop.
	 *
	 * @return int
	 */
	protected int getHashCode() {
		return 31 * 1 + ((criteria == null) ? 0 : criteria.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCriteria<?> other = (AbstractCriteria<?>) obj;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(getClass().getSimpleName())
			.append(criteria)
			.toString();
	}

}
