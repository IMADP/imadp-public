package com.imadp.dao.criteria;

import java.io.Serializable;

import com.imadp.dao.Persistable;


/**
 * Criterion
 *
 * Represent a single criterion for constructing dynamic queries.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface Criterion<T extends Persistable, V> extends Serializable {

	/**
	 * Applies a criterion to the underlying criteria object.
	 *
	 * @param criteriaObject
	 */
	public void applyCriterion(V criteriaObject);

}
