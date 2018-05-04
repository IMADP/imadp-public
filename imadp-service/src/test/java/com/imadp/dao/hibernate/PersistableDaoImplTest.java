package com.imadp.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.imadp.core.Property;
import com.imadp.dao.DaoTestUtil;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.criteria.search.SearchCriteria;
import com.imadp.service.test.IMADPServiceTestCase;

/**
 * PersistableDaoImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableDaoImplTest extends IMADPServiceTestCase {

	@Transactional
	public static class PersistableOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable;

		@Override
		public void before() throws Exception {
			super.before();

			samplePersistable = new SamplePersistable();
			samplePersistable.setName("name");
			samplePersistable.setIntNumber(Integer.MAX_VALUE);
			samplePersistable.setLongNumber(Long.MAX_VALUE);
			samplePersistable.setStartDate(new DateTime());
			samplePersistable.setEndDate(new DateTime());
			samplePersistable.setDescription("This is a sample persistable object");
		}

		@Test
		public void commonPersistableOperations() throws Exception {
			DaoTestUtil.assertPersistable(samplePersistable, samplePersistableDao);
		}

	}

	public static class VersionAndTimestamp extends IMADPServiceTestCase {
		SamplePersistable samplePersistable;

		@Override
		public void before() throws Exception {
			super.before();

			samplePersistable = new SamplePersistable();
			samplePersistable.setName("name");
			samplePersistable.setIntNumber(Integer.MAX_VALUE);
			samplePersistable.setLongNumber(Long.MAX_VALUE);
			samplePersistable.setStartDate(new DateTime());
			samplePersistable.setEndDate(new DateTime());
			samplePersistable.setDescription("This is a sample persistable object");
		}

		@Test
		public void versioning() throws Exception {
			assertEquals(1, samplePersistable.getVersion());

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	samplePersistableDao.save(samplePersistable);
	            	return null;
	            }
	        });

			assertEquals(1, samplePersistable.getVersion());

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	samplePersistableDao.save(samplePersistable);
	            	return null;
	            }
	        });

			assertEquals(2, samplePersistable.getVersion());
		}

		@Test
		public void timestamps() throws Exception {
			assertNull(samplePersistable.getTimeCreated());
			assertNull(samplePersistable.getTimeModified());

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	samplePersistableDao.save(samplePersistable);
	            	return null;
	            }
	        });

			assertNotNull(samplePersistable.getTimeCreated());
			assertNotNull(samplePersistable.getTimeModified());
			assertEquals(samplePersistable.getTimeCreated(), samplePersistable.getTimeModified());

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	samplePersistableDao.save(samplePersistable);
	            	return null;
	            }
	        });

			assertTrue(samplePersistable.getTimeCreated() <= samplePersistable.getTimeModified());
			assertTrue(samplePersistable.isCreatedToday());
			assertTrue(samplePersistable.isModifiedToday());
		}

	}

	@Transactional
	public static class FindOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable1;
		SamplePersistable samplePersistable2;
		List<Property<SamplePersistable, String>> properties;

		@Override
		@Before
		public void before() throws Exception {
			super.before();

			properties = new ArrayList<>(1);
			properties.add(SamplePersistable.DESCRIPTION);

			samplePersistable1 = new SamplePersistable();
			samplePersistable1.setName("name");
			samplePersistable1.setDescription("This is a sample persistable object");

			samplePersistable2 = new SamplePersistable();
			samplePersistable2.setName("SamplePersistable2");
			samplePersistable2.setDescription("Description");

			DaoTestUtil.assertSave(samplePersistable1, samplePersistableDao);
			DaoTestUtil.assertSave(samplePersistable2, samplePersistableDao);
		}

		@Test
		public void findAll() throws Exception {
			List<SamplePersistable> list = samplePersistableDao.findAll();

			assertEquals(2, list.size());
		}

		@Test
		public void findCount() throws Exception {
			assertEquals(2, samplePersistableDao.findCount());
		}

		@Test
		public void findByHql() throws Exception {
			assertEquals(2, samplePersistableDao.findByHql(
					"from SamplePersistable", Results.ALL).size());
		}

		@Test
		public void findByHqlAddOrdering() throws Exception {
			List<Order<SamplePersistable>> orders = new ArrayList<>(1);
			orders.add(Order.asc(SamplePersistable.START_DATE));
			orders.add(Order.desc(SamplePersistable.END_DATE));

			String hql = samplePersistableDao.addOrdering(
					"from SamplePersistable", orders);

			assertEquals(2, samplePersistableDao.findByHql(hql, Results.ALL).size());
		}

		@Test
		public void findByHqlResultParameters() throws Exception {
			assertEquals(2, samplePersistableDao.findByHql(
					"from SamplePersistable", new Results(0, 10)).size());

			assertEquals(1, samplePersistableDao.findByHql(
					"from SamplePersistable", new Results(0, 1)).size());
		}

		@Test
		public void getCorrectResultUsingFindByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableDao.findCriteriaBuilder(Results.ALL)
				.whereEqualTo(SamplePersistable.NAME, samplePersistable1.getName()).build();

			List<SamplePersistable> samplePersistables = samplePersistableDao.findBy(findCriteria);

			assertEquals(1, samplePersistables.size());
			assertEquals(samplePersistable1, samplePersistables.get(0));
		}

		@Test
		public void getNoResultUsingFindByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableDao.findCriteriaBuilder(Results.ALL)
				.whereEqualTo(SamplePersistable.NAME, "Not a name").build();

			List<SamplePersistable> samplePersistables = samplePersistableDao.findBy(findCriteria);

			assertEquals(0, samplePersistables.size());
		}

		@Test
		public void getNoResultUsingFindByFindCriteriaDueToEncryption() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableDao.findCriteriaBuilder(Results.ALL)
				.whereEqualTo(SamplePersistable.DESCRIPTION, samplePersistable1.getDescription()).build();

			List<SamplePersistable> samplePersistables = samplePersistableDao.findBy(findCriteria);

			assertEquals(0, samplePersistables.size());
		}

		@Test
		public void getCorrectCountUsingFindCountByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableDao.findCriteriaBuilder(Results.ALL)
				.whereEqualTo(SamplePersistable.NAME, samplePersistable1.getName()).build();

			assertEquals(1, samplePersistableDao.findCountBy(findCriteria));
		}

		@Test
		public void getNoCountUsingFindCountByFindCriteria() throws Exception {
			FindCriteria<SamplePersistable> findCriteria =
				samplePersistableDao.findCriteriaBuilder(Results.ALL)
				.whereEqualTo(SamplePersistable.NAME, "Not a name").build();

			assertEquals(0, samplePersistableDao.findCountBy(findCriteria));
		}

	}

	@Transactional
	public static class DeleteOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable1;
		SamplePersistable samplePersistable2;

		@Override
		public void before() throws Exception {
			super.before();

			samplePersistable1 = new SamplePersistable();
			samplePersistable1.setName("name1");
			samplePersistable1.setDescription("This is a sample persistable object");

			samplePersistable2 = new SamplePersistable();
			samplePersistable2.setName("name2");
			samplePersistable2.setDescription("Description");

			DaoTestUtil.assertSave(samplePersistable1, samplePersistableDao);
			DaoTestUtil.assertSave(samplePersistable2, samplePersistableDao);
		}

		@Test
		public void deleteAll() throws Exception {
			assertEquals(2, samplePersistableDao.findAll().size());

			assertEquals(2, samplePersistableDao.deleteAll());

			assertEquals(0, samplePersistableDao.findAll().size());
		}

		@Test
		public void deleteByHql() throws Exception {
			assertEquals(2, samplePersistableDao.findAll().size());

			assertEquals(2, samplePersistableDao.deleteBy(
					"delete " + samplePersistableDao.entityName));

			assertEquals(0, samplePersistableDao.findAll().size());
		}

	}

	public static class SearchOperations extends IMADPServiceTestCase {
		SamplePersistable samplePersistable1;
		SamplePersistable samplePersistable2;
		List<Property<SamplePersistable, String>> properties;

		@Override
		@Before
		public void before() throws Exception {
			super.before();

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	samplePersistableDao.searchIndexPurge();
	            	return null;
	            }
	        });

			properties = new ArrayList<>(1);
			properties.add(SamplePersistable.DESCRIPTION);

			samplePersistable1 = new SamplePersistable();
			samplePersistable1.setName("name");
			samplePersistable1.setDescription("This is a sample persistable object");

			samplePersistable2 = new SamplePersistable();
			samplePersistable2.setName("name");
			samplePersistable2.setDescription("Some description here");

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	DaoTestUtil.assertSave(samplePersistable1, samplePersistableDao);
	    			DaoTestUtil.assertSave(samplePersistable2, samplePersistableDao);
	            	return null;
	            }
	        });
		}

		@Test
		public void getCorrectResultUsingSearchBySearchCriteria() throws Exception {
			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	SearchCriteria<SamplePersistable> searchCriteria =
	    				samplePersistableDao.searchCriteriaBuilder(
	    						"sample", properties, Results.ALL).build();

	            	List<SamplePersistable> samplePersistables = samplePersistableDao.searchBy(searchCriteria);

	            	assertEquals(1, samplePersistables.size());
	    			assertEquals(samplePersistable1, samplePersistables.get(0));
	            	return null;
	            }
	        });
		}

		@Test
		public void getNoResultUsingSearchBySearchCriteria() throws Exception {
			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	SearchCriteria<SamplePersistable> searchCriteria =
	    				samplePersistableDao.searchCriteriaBuilder(
	    						"Nothing to find", properties, Results.ALL).build();

	    			List<SamplePersistable> samplePersistables = samplePersistableDao.searchBy(searchCriteria);

	    			assertEquals(0, samplePersistables.size());
	            	return null;
	            }
	        });
		}

		@Test
		public void searchCountBySearchCriteria() throws Exception {
			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	SearchCriteria<SamplePersistable> searchCriteria =
	    				samplePersistableDao.searchCriteriaBuilder(
	    						"sample", properties, Results.ALL).build();

	    			assertEquals(1, samplePersistableDao.searchCountBy(searchCriteria));
	            	return null;
	            }
	        });
		}

		@Test
		public void getNoCountUsingSearchCountBySearchCriteria() throws Exception {
			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	SearchCriteria<SamplePersistable> searchCriteria =
	    				samplePersistableDao.searchCriteriaBuilder(
	    						"Nothing to find", properties, Results.ALL).build();

	    			assertEquals(0, samplePersistableDao.searchCountBy(searchCriteria));
	    			return null;
	            }
	        });
		}

		@Test
		public void geNoResultsAfterSearchIndexPurge() throws Exception {
			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	samplePersistableDao.searchIndexPurge();
	            	return null;
	            }
	        });

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	SearchCriteria<SamplePersistable> searchCriteria =
	    				samplePersistableDao.searchCriteriaBuilder(
	    						"sample", properties, Results.ALL).build();

	    			List<SamplePersistable> samplePersistables = samplePersistableDao.searchBy(searchCriteria);

	    			assertEquals(0, samplePersistables.size());
	            	return null;
	            }
	        });
		}

		@Test
		public void geCorrectResultsAfterSearchIndexPurgeAndIndex() throws Exception {
			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	samplePersistableDao.searchIndexPurge();
	    			samplePersistableDao.searchIndexAll(10);
	            	return null;
	            }
	        });

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	SearchCriteria<SamplePersistable> searchCriteria =
	    				samplePersistableDao.searchCriteriaBuilder(
	    						"sample", properties, Results.ALL).build();

	    			List<SamplePersistable> samplePersistables = samplePersistableDao.searchBy(searchCriteria);

	    			assertEquals(1, samplePersistables.size());
	    			assertEquals(samplePersistable1, samplePersistables.get(0));

	    			searchCriteria = samplePersistableDao.searchCriteriaBuilder(
	    					"here", properties, Results.ALL).build();

	    			 samplePersistables = samplePersistableDao.searchBy(searchCriteria);

	    			assertEquals(1, samplePersistables.size());
	    			assertEquals(samplePersistable2, samplePersistables.get(0));
	            	return null;
	            }
	        });
		}

		@Test
		public void deleteByHqlSearchIndexPurge() throws Exception {
			final SearchCriteria<SamplePersistable> searchCriteria =
				samplePersistableDao.searchCriteriaBuilder(
						"sample", properties, Results.ALL).build();

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	assertEquals(2, samplePersistableDao.findAll().size());
	    			assertEquals(1, samplePersistableDao.searchCountBy(searchCriteria));
	            	return null;
	            }
	        });

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	assertEquals(2, samplePersistableDao.deleteBy(
	    					"delete " + samplePersistableDao.entityName));
	            	return null;
	            }
	        });

			new TransactionTemplate(transactionManager).execute(new TransactionCallback<Void>() {
	            @Override
				public Void doInTransaction(TransactionStatus status) {
	            	assertEquals(0, samplePersistableDao.findAll().size());
	    			assertEquals(0, samplePersistableDao.searchCountBy(searchCriteria));
	            	return null;
	            }
	        });
		}

	}

}
