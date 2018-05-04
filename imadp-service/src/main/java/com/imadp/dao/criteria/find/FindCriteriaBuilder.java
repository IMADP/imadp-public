package com.imadp.dao.criteria.find;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;

/**
 * IFindCriteriaBuilder
 *
 * Provides dynamic FindCriteria instances.
 *
 * @note Implementations of this class are mutable by necessity, therefore instances
 * 		 should not be shared among multiple threads, or used as cache keys.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface FindCriteriaBuilder<T extends Persistable> {

	/**
	 * Builds and returns a FindCriteria based on the supplied criteria.
	 *
	 * @return FindCriteria<T>
	 */
	public FindCriteria<T> build();

	/**
	 * Applies an order criterion, equivalent to saying:
	 *
	 * "Order results by {property}, ascending"
	 *
	 * @param property
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> orderByAscending(Property<? super T, ?> property);

	/**
	 * Applies an order criterion, equivalent to saying:
	 *
	 * "Order results by {property}, descending"
	 *
	 * @param property
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> orderByDescending(Property<? super T, ?> property);

	/**
	 * Applies a null criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is null"
	 *
	 * @param property
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> whereIsNull(Property<? super T, ?> property);

	/**
	 * Applies a non null criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is not null"
	 *
	 * @param property
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> whereIsNotNull(Property<? super T, ?> property);

	/**
	 * Applies an equals criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is equal to {value}"
	 *
	 * @param <V>
	 * @param property
	 * @param value
	 * @return IFindCriteriaBuilder<T>
	 */
	public <V> FindCriteriaBuilder<T> whereEqualTo(Property<? super T, V> property, V value);

	/**
	 * Applies a greater than criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is greater than {value}"
	 *
	 * @param <V>
	 * @param property
	 * @param value
	 * @return IFindCriteriaBuilder<T>
	 */
	public <V extends Number> FindCriteriaBuilder<T> whereGreaterThan(Property<? super T, V> property, V value);

	/**
	 * Applies a greater than or equal criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is greater than or equal to {value}"
	 *
	 * @param <V>
	 * @param property
	 * @param value
	 * @return IFindCriteriaBuilder<T>
	 */
	public <V extends Number> FindCriteriaBuilder<T> whereGreaterThanOrEqualTo(
			Property<? super T, V> property, V value);

	/**
	 * Applies a less than criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is less than {value}"
	 *
	 * @param <V>
	 * @param property
	 * @param value
	 * @return IFindCriteriaBuilder<T>
	 */
	public <V extends Number> FindCriteriaBuilder<T> whereLessThan(Property<? super T, V> property, V value);

	/**
	 * Applies a less than or equal criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is less than or equal to {value}"
	 *
	 * @param <V>
	 * @param property
	 * @param value
	 * @return IFindCriteriaBuilder<T>
	 */
	public <V extends Number> FindCriteriaBuilder<T> whereLessThanOrEqualTo(Property<? super T, V> property, V value);

	/**
	 * Applies a not equal criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is not equal to {value}"
	 *
	 * @param <V>
	 * @param property
	 * @param value
	 * @return IFindCriteriaBuilder<T>
	 */
	public <V> FindCriteriaBuilder<T> whereNotEqualTo(Property<? super T, V> property, V value);

	/**
	 * Applies a date criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is after {date}"
	 *
	 * @param property
	 * @param date
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> whereAfterDate(Property<? super T, DateTime> property, DateTime date);

	/**
	 * Applies a date criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is after or on {date}"
	 *
	 * @param property
	 * @param date
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> whereAfterOrOnDate(Property<? super T, DateTime> property, DateTime date);

	/**
	 * Applies a date criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is before {date}"
	 *
	 * @param property
	 * @param date
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> whereBeforeDate(Property<? super T, DateTime> property, DateTime date);

	/**
	 * Applies a date criterion, equivalent to saying:
	 *
	 * "Find any persisted value where the value of {property} is before or on {date}"
	 *
	 * @param property
	 * @param date
	 * @return IFindCriteriaBuilder<T>
	 */
	public FindCriteriaBuilder<T> whereBeforeOrOnDate(Property<? super T, DateTime> property, DateTime date);

}