package com.imadp.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.util.CollectionUtils;

import com.imadp.core.CoreComponent;
import com.imadp.core.Property;
import com.imadp.dao.DaoException;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;


/**
 * AbstractHibernateDaoImpl
 * 
 * Provies common functionality for interacting with Spring's HibernateTemplate.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractHibernateDaoImpl extends CoreComponent {
	protected SessionFactory sessionFactory;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		Validate.notNull(sessionFactory);
	}

	/**
	 * Returns a List<T> of results by criteria and result parameters.
	 * 
	 * @param criteria
	 * @param results
	 * @return List<T> or an empty List if no results were found
	 */
	@SuppressWarnings("unchecked")
	protected final <T> List<T> findByCriteria(DetachedCriteria criteria, Results results) {
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		
		if (results.getFirstResult() >= 0) 
			executableCriteria.setFirstResult((int) results.getFirstResult());
		
		if (results.getMaxResults() > 0) 
			executableCriteria.setMaxResults((int) results.getMaxResults());
		
		return executableCriteria.list();
	}

	/**
	 * Returns a List<T> of results by hql and result parameters.
	 * 
	 * @param hql
	 * @param results
	 * @return List<T> or an empty List if no results were found
	 */
	protected final <T> List<T> findByHql(String hql, Results results) {
		return findByQuery(getSession().createQuery(hql), results);
	}
	
	/**
	 * Returns the result of a query as a List<T>, limited by the results given.
	 * 
	 * TODO: Uncast the int results whenever Hibernate accepts longs
	 * 
	 * @param <T>
	 * @param query
	 * @param results
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	protected final <T> List<T> findByQuery(Query query, Results results) {
		if(results.hasFirstResult())
			query.setFirstResult((int) results.getFirstResult());
	
		if(results.hasMaxResults())
			query.setMaxResults((int) results.getMaxResults());
	
		return query.list();
	}

	/**
	 * Adds Orders to a criteria object.
	 * 
	 * @param <T>
	 * @param criteria
	 * @param orders
	 */
	protected final <T> void addOrdering(DetachedCriteria criteria, List<Order<? super T>> orders) {
		for(Order<? super T> order : orders)
			criteria.addOrder(order.isDescending() ? 
					org.hibernate.criterion.Order.desc(order.getPropertyName()) : 
						org.hibernate.criterion.Order.asc(order.getPropertyName()));		
	}	
	
	/**
	 * Adds Orders to an hql query. Orders are appended to the end of the supplied
	 * hql.
	 * 
	 * @param <T>
	 * @param hql
	 * @param orders
	 * @return String
	 */
	protected final <T> String addOrdering(String hql, List<Order<T>> orders) {
		StringBuilder sb = new StringBuilder(hql);
		
		if(!CollectionUtils.isEmpty(orders))
		{
			sb.append(" order by ");
			
			for(Iterator<Order<T>> it = orders.iterator();  it.hasNext();)
			{
				Order<T> order = it.next();				
				sb.append(order.getProperty().getName());				
				sb.append(order.isDescending() ? " desc" : " asc");
				
				if(it.hasNext())
					sb.append(", ");
			}
			
		}
		
		return sb.toString();
	}
	
	/**
	 * Creates a single property name out of two Properties, delimited by a period.
	 * 
	 * @param propertyOne
	 * @param propertyTwo
	 * @return String
	 */
	protected final String createPropertyName(Property<?, ?> propOne, Property<?, ?> propTwo) {
		return StringUtils.join(new Object[] {propOne.getName(), propTwo.getName()}, '.');
	}
	
	/**
	 * Returns the currently bound Session, or a new one if one is not yet bound.
	 * 
	 * @return Session
	 */
	protected final Session getSession() {		
		try
		{
			return sessionFactory.getCurrentSession();
		}
		catch(HibernateException exception)
		{
			throw new DaoException("Could not get current session", exception);
		}
	}

	
	
	// getters and setters
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	

}