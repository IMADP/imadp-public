package com.imadp.dao.hibernate.criteria.find;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.DaoTestUtil;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.hibernate.SamplePersistable;
import com.imadp.service.test.IMADPServiceTestCase;

/**
 * OrderByTest
 * 
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Transactional
public class OrderByTest extends IMADPServiceTestCase {
	SamplePersistable persistableA;
	SamplePersistable persistableZ;
	FindCriteria<SamplePersistable> findCriteria;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		findCriteria = null;
		
		persistableA = new SamplePersistable();
		persistableA.setName("A");
		persistableA.setDescription("description");
		
		persistableZ = new SamplePersistable();
		persistableZ.setName("Z");
		persistableZ.setDescription("description");
		
		DaoTestUtil.assertSave(persistableA, samplePersistableDao);
		DaoTestUtil.assertSave(persistableZ, samplePersistableDao);
	}
	
	@Test
	public void initialResultSize() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL).build();
		
		assertEquals(2, samplePersistableDao.findBy(findCriteria).size());
	}
	
	@Test
	public void alphabeticalNameAscendingOrder() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.orderByAscending(SamplePersistable.NAME).build();
		
		List<SamplePersistable> results = samplePersistableDao.findBy(findCriteria);
		
		assertEquals(2, results.size());		
		assertEquals(persistableA, results.get(0));
		assertEquals(persistableZ, results.get(1));
	}
	
	@Test
	public void alphabeticalNameDescendingOrder() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.orderByDescending(SamplePersistable.NAME).build();
		
		List<SamplePersistable> results = samplePersistableDao.findBy(findCriteria);
		
		assertEquals(persistableZ, results.get(0));
		assertEquals(persistableA, results.get(1));	
	}

	
	@Test
	public void alphabeticalNameDescendingOrderSecondOrderSort() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.orderByAscending(SamplePersistable.VERSION)
			.orderByDescending(SamplePersistable.NAME).build();

		List<SamplePersistable> results = samplePersistableDao.findBy(findCriteria);
		
		assertEquals(persistableZ, results.get(0));
		assertEquals(persistableA, results.get(1));	
	}

}
