package com.imadp.dao.criteria;

import java.util.ArrayList;
import java.util.List;

import com.imadp.dao.Persistable;

/**
 * AbstractCriteriaBuilder
 *
 * A parent class for Criteria builder implementations.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractCriteriaBuilder<T extends Persistable>  {

	// static properties
	private static final int INITIAL_CRITERIA_SIZE = 2;

	// properties
	protected List<Criterion<T, ?>> criteria;

	/**
	 * Adds an ICriterion to the builder, ensuring that the criteria list is instantiated.
	 *
	 */
	protected final void add(Criterion<T, ?> findCriterion) {
		if(criteria == null)
			criteria = new ArrayList<>(INITIAL_CRITERIA_SIZE);

		criteria.add(findCriterion);
	}

}