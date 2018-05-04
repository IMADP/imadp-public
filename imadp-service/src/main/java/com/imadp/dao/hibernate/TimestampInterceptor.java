package com.imadp.dao.hibernate;

import java.io.Serializable;
import java.util.Arrays;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.imadp.dao.AbstractPersistable;


/**
 * TimestampInterceptor
 * 
 * Interceptor which applies timestamps to persisted objects.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class TimestampInterceptor extends EmptyInterceptor {
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames, Type[] types) {
		long time = System.currentTimeMillis();
		setValue(currentState, propertyNames, AbstractPersistable.TIME_MODIFIED.getName(), time);
		return true;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		long time = System.currentTimeMillis();
		setValue(state, propertyNames, AbstractPersistable.TIME_CREATED.getName(), time);
		setValue(state, propertyNames, AbstractPersistable.TIME_MODIFIED.getName(), time);
		return true;
	}

	/**
	 * Sets a value on the entity object.
	 * 
	 * @param state
	 * @param propertyNames
	 * @param propertyToSet
	 * @param value
	 */
	private void setValue(Object[] state, String[] propertyNames, String propertyToSet, Object value) {
		int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		
		if(index >= 0) 
			state[index] = value;		
	}
	
}
