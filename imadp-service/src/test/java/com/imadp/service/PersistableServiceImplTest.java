package com.imadp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.imadp.core.Property;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.criteria.search.SearchCriteria;
import com.imadp.dao.hibernate.SamplePersistable;
import com.imadp.service.test.IMADPServiceTestCase;

/**
 * PersistableServiceImplTest
 *
 * Tests the operations of the PersistableServiceImpl class.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableServiceImplTest extends IMADPServiceTestCase {

	public static class PersistableOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable;

		@Override
		public void before() throws Exception {
			super.before();

			samplePersistable = new SamplePersistable();
			samplePersistable.setName("name");
			samplePersistable.setDescription("This is a sample persistable object");
		}

		@Test
		public void commonPersistableOperations() throws Exception {
			ServiceTestUtil.assertPersistable(samplePersistable, samplePersistableService);
		}

	}

	public static class GetOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable;

		@Override
		public void before() throws Exception {
			super.before();

			samplePersistable = new SamplePersistable();
			samplePersistable.setName("name");
			samplePersistable.setDescription("This is a sample persistable object");

			samplePersistableService.save(samplePersistable);
		}

		@Test
		public void get() throws Exception {
			assertEquals(samplePersistable, samplePersistableService.get(samplePersistable.getId()));
			assertEquals(samplePersistable, samplePersistableService.get(samplePersistable.getUid()));
		}

		@Test
		public void getCache() throws Exception {
			assertFalse(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getId()));
			assertFalse(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getUid()));

			assertEquals(samplePersistable, samplePersistableService.get(samplePersistable.getId()));

			assertTrue(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getId()));
			assertFalse(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getUid()));

			assertEquals(samplePersistable, samplePersistableService.get(samplePersistable.getUid()));

			assertTrue(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getId()));
			assertTrue(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getUid()));

			samplePersistableService.delete(samplePersistable);

			assertFalse(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getId()));
			assertFalse(samplePersistableService.getGetCache().isKeyInCache(samplePersistable.getUid()));
		}

	}

	public static class FindOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable;
		List<Property<SamplePersistable, String>> properties;

		@Override
		public void before() throws Exception {
			super.before();

			properties = new ArrayList<>(1);
			properties.add(SamplePersistable.DESCRIPTION);

			samplePersistable = new SamplePersistable();
			samplePersistable.setName("name");
			samplePersistable.setDescription("This is a sample persistable object");

			samplePersistableService.save(samplePersistable);
		}

		@Test
		public void getCorrectResultUsingFindByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableService.findCriteriaBuilder(CriteriaParams.<SamplePersistable>of(Results.ALL))
				.whereEqualTo(SamplePersistable.NAME, samplePersistable.getName())
				.build();

			List<SamplePersistable> samplePersistables =
				samplePersistableService.findBy(findCriteria);

			assertEquals(1, samplePersistables.size());
			assertEquals(samplePersistable, samplePersistables.get(0));
		}

		@Test
		public void getNoResultUsingFindByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableService.findCriteriaBuilder(CriteriaParams.<SamplePersistable>of(Results.ALL))
				.whereEqualTo(SamplePersistable.NAME, "Not a name")
				.build();

			List<SamplePersistable> samplePersistables =
				samplePersistableService.findBy(findCriteria);

			assertEquals(0, samplePersistables.size());
		}

		@Test
		public void getCorrectCountUsingFindCountByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableService.findCriteriaBuilder(CriteriaParams.<SamplePersistable>of(Results.ALL))
				.whereEqualTo(SamplePersistable.NAME, samplePersistable.getName())
				.build();

			assertEquals(1, samplePersistableService.findCountBy(findCriteria));
		}

		@Test
		public void getNoCountUsingFindCountByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableService.findCriteriaBuilder(CriteriaParams.<SamplePersistable>of(Results.ALL))
				.whereEqualTo(SamplePersistable.NAME, "Not a name")
				.build();

			assertEquals(0, samplePersistableService.findCountBy(findCriteria));
		}

	}

	public static class SearchOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable;
		List<Property<SamplePersistable, String>> properties;

		@Override
		public void before() throws Exception {
			super.before();

			properties = new ArrayList<>(1);
			properties.add(SamplePersistable.DESCRIPTION);

			samplePersistable = new SamplePersistable();
			samplePersistable.setName("name");
			samplePersistable.setDescription("This is a sample persistable object");

			samplePersistableService.save(samplePersistable);
		}

		@Override
		public void after() throws Exception {
			super.after();

			samplePersistableService.searchIndexPurge();
		}

		@Test
		public void getCorrectResultUsingSearchBySearchCriteria() throws Exception {
			SearchCriteria<SamplePersistable> searchCriteria =
				samplePersistableService.searchCriteriaBuilder(
						"sample", properties, CriteriaParams.<SamplePersistable>of(Results.ALL)).build();

			List<SamplePersistable> samplePersistables =
				samplePersistableService.searchBy(searchCriteria);

			assertEquals(1, samplePersistables.size());
			assertEquals(samplePersistable, samplePersistables.get(0));
		}

		@Test
		public void getNoResultUsingSearchBySearchCriteria() throws Exception {
			SearchCriteria<SamplePersistable> searchCriteria =
				samplePersistableService.searchCriteriaBuilder(
						"nothing to find", properties, CriteriaParams.<SamplePersistable>of(Results.ALL)).build();

			List<SamplePersistable> samplePersistables =
				samplePersistableService.searchBy(searchCriteria);

			assertEquals(0, samplePersistables.size());
		}

		@Test
		public void searchCountBySearchCriteria() throws Exception {
			SearchCriteria<SamplePersistable> searchCriteria =
				samplePersistableService.searchCriteriaBuilder(
						"sample", properties, CriteriaParams.<SamplePersistable>of(Results.ALL)).build();

			assertEquals(1, samplePersistableService.searchCountBy(searchCriteria));
		}

		@Test
		public void getNoCountUsingSearchCountBySearchCriteria() throws Exception {
			SearchCriteria<SamplePersistable> searchCriteria =
				samplePersistableService.searchCriteriaBuilder(
						"nothing to find", properties, CriteriaParams.<SamplePersistable>of(Results.ALL)).build();

			assertEquals(0, samplePersistableService.searchCountBy(searchCriteria));
		}

	}

}
