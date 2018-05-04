package com.imadp.dao.hibernate.criteria.find;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriteriaBuilder;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.criteria.find.FindCriteriaBuilder;

/**
 * FindCriteriaBuilderHibernateImpl
 *
 * A factory class for creating FindCriteria instances.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class FindCriteriaBuilderHibernateImpl<T extends Persistable>
	extends AbstractCriteriaBuilder<T> implements FindCriteriaBuilder<T> {

	// constructor
	public FindCriteriaBuilderHibernateImpl(Results results) {
		add(new ForResults<T>(results));
	}

	@Override
	public FindCriteria<T> build() {
		return new FindCriteria<>(criteria);
	}

	@Override
	public FindCriteriaBuilder<T> orderByAscending(Property<? super T, ?> property) {
		add(new OrderBy<>(Order.asc(property)));
		return this;
	}

	@Override
	public FindCriteriaBuilder<T> orderByDescending(Property<? super T, ?> property) {
		add(new OrderBy<>(Order.desc(property)));
		return this;
	}

	@Override
	public FindCriteriaBuilder<T> whereAfterDate(Property<? super T, DateTime> property, DateTime date) {
		add(new WhereAfterDate<>(property, date));
		return this;
	}

	@Override
	public FindCriteriaBuilder<T> whereAfterOrOnDate(Property<? super T, DateTime> property, DateTime date) {
		add(new WhereAfterOrOnDate<>(property, date));
		return this;
	}

	@Override
	public FindCriteriaBuilder<T> whereBeforeDate(Property<? super T, DateTime> property, DateTime date) {
		add(new WhereBeforeDate<>(property, date));
		return this;
	}

	@Override
	public FindCriteriaBuilder<T> whereBeforeOrOnDate(Property<? super T, DateTime> property, DateTime date) {
		add(new WhereBeforeOrOnDate<>(property, date));
		return this;
	}

	@Override
	public FindCriteriaBuilder<T> whereIsNull(Property<? super T, ?> property) {
		add(new WhereIsNull<>(property));
		return this;
	}

	@Override
	public FindCriteriaBuilder<T> whereIsNotNull(Property<? super T, ?> property) {
		add(new WhereIsNotNull<>(property));
		return this;
	}

	@Override
	public <V> FindCriteriaBuilder<T> whereEqualTo(Property<? super T, V> property, V value) {
		add(new WhereEqualTo<>(property, value));
		return this;
	}

	@Override
	public <V extends Number> FindCriteriaBuilder<T> whereGreaterThan(
			Property<? super T, V> property, V value) {
		add(new WhereGreaterThan<>(property, value));
		return this;
	}

	@Override
	public <V extends Number> FindCriteriaBuilder<T> whereGreaterThanOrEqualTo(
			Property<? super T, V> property, V value) {
		add(new WhereGreaterThanOrEqualTo<>(property, value));
		return this;
	}

	@Override
	public <V extends Number> FindCriteriaBuilder<T> whereLessThan(
			Property<? super T, V> property, V value) {
		add(new WhereLessThan<>(property, value));
		return this;
	}

	@Override
	public <V extends Number> FindCriteriaBuilder<T> whereLessThanOrEqualTo(
			Property<? super T, V> property, V value) {
		add(new WhereLessThanOrEqualTo<>(property, value));
		return this;
	}

	@Override
	public <V> FindCriteriaBuilder<T> whereNotEqualTo(Property<? super T, V> property, V value) {
		add(new WhereNotEqualTo<>(property, value));
		return this;
	}

}