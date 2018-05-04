package com.imadp.service;

import java.util.List;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;


/**
 * ServiceTestUtil
 *
 * Provides utility methods for executing JUnit tests.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ServiceTestUtil {

	// logger
	protected static final Logger logger = LoggerFactory.getLogger(ServiceTestUtil.class);

	/**
	 * Assert the various IPersistableService operations on a persistable object.
	 *
	 * @param <T>
	 * @param persistable
	 * @param service
	 */
	public static <T extends Persistable> void assertPersistable(T persistable, PersistableService<T> service) {
		logger.info("Asserting persistable service object ["+persistable.getClass().getName()+"]");

		// assert not null
		Assert.assertNotNull("Persistable object cannot be null", persistable);

		// saving object
		service.save(persistable);

		// asserting global properties
		Assert.assertNotNull("id cannot be null", persistable.getId());
		Assert.assertNotNull("uid cannot be null", persistable.getUid());
		Assert.assertNotNull("version cannot be null", persistable.getVersion());
		Assert.assertNotNull("timeCreated cannot be null", persistable.getTimeCreated());
		Assert.assertNotNull("timeModified cannot be null", persistable.getTimeModified());

		// getting object
		Assert.assertNotNull(service.get(persistable.getId()));
		Assert.assertNotNull(service.get(persistable.getUid()));

		// finding by all properties
		List<Property<T, ?>> properties = Property.getStaticProperties(persistable.getClass());

		for(Property<T, ?> property : properties)
			Assert.assertNotNull(service.findBy(
					CriteriaParams.of(Results.ONE, Order.asc(property))));

		// deleting object
		service.delete(persistable);

		// assert object deleted
		Assert.assertNull("Persistable object could not be deleted", service.get(persistable.getId()));
		Assert.assertNull("Persistable object could not be deleted", service.get(persistable.getUid()));
	}

}
