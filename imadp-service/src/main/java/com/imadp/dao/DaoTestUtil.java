package com.imadp.dao;

import java.util.List;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imadp.core.Property;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;


/**
 * DaoTestUtil
 *
 * Provides utility methods for executing JUnit tests.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class DaoTestUtil {

	// logger
	protected static final Logger logger = LoggerFactory.getLogger(DaoTestUtil.class);

	/**
	 * Save or update a Persistable object.
	 *
	 * @param <T>
	 * @param persistable
	 * @param dao
	 */
	public static <T extends Persistable> void assertSave(
			T persistable, PersistableDao<T> dao) {
		// assert not null
		Assert.assertNotNull("Persistable object cannot be null", persistable);

		// save object
		dao.save(persistable);

		// assert global properties
		Assert.assertNotNull("id cannot be null", persistable.getId());
		Assert.assertNotNull("timeCreated cannot be null", persistable.getTimeCreated());
		Assert.assertNotNull("timeModified cannot be null", persistable.getTimeModified());
		Assert.assertNotNull("version cannot be null", persistable.getVersion());
		Assert.assertNotNull("uid cannot be null", persistable.getUid());

		// assert object loaded
		Assert.assertNotNull("Persistable object could not be loaded",
				DaoTestUtil.assertGet(persistable, dao));
	}

	/**
	 * Load a Persistable object from the database
	 *
	 * @param <T>
	 * @param persistable
	 * @param dao
	 * @return Object
	 */
	public static <T extends Persistable> T assertGet(T persistable, PersistableDao<T> dao) {
		// assert not null
		Assert.assertNotNull("Persistable object cannot be null", persistable);

		return dao.get(persistable.getId());
	}

	/**
	 * Assert that the static Properties defined for an object are valid operations by adding them
	 * as arbitrary SortParams.
	 *
	 * @param <T> the persistable object
	 * @param persistable
	 * @param dao
	 */
	public static <T extends Persistable> void assertProperties(
			T persistable, PersistableDao<T> dao) {
		List<Property<T, ?>> properties = Property.getStaticProperties(persistable.getClass());

		for(Property<T, ?> property : properties)
		{
			FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ONE)
				.orderByAscending(property).build();

			Assert.assertNotNull(dao.findBy(findCriteria));
		}

	}

	/**
	 * Delete a Persistable object from the database
	 *
	 * @param <T>
	 * @param persistable
	 * @param dao
	 */
	public static <T extends Persistable> void assertDelete(
			T persistable, PersistableDao<T> dao) {
		// assert not null
		Assert.assertNotNull("Persistable object cannot be null", persistable);

		// delete object
		dao.delete(persistable);

		// assert object deleted
		Assert.assertNull("Persistable object could not be deleted",
				DaoTestUtil.assertGet(persistable, dao));
	}

	/**
	 * Assert the various IPersistableDao operations on a persistable object.
	 *
	 * @param <T>
	 * @param persistable
	 * @param dao
	 */
	public static <T extends Persistable> void assertPersistable(
			T persistable, PersistableDao<T> dao) {
		logger.info("Asserting persistable dao object ["+persistable.getClass().getName()+"]");
		DaoTestUtil.assertSave(persistable, dao);
		DaoTestUtil.assertGet(persistable, dao);
		DaoTestUtil.assertProperties(persistable, dao);
		DaoTestUtil.assertDelete(persistable, dao);
	}

}
