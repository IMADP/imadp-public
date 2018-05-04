package com.imadp.dao.hibernate.criteria.find;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.DaoTestUtil;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.hibernate.SamplePersistable;
import com.imadp.service.test.IMADPServiceTestCase;

/**
 * WhereEqualToTest
 * 
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Transactional
public class WhereNotEqualToTest extends IMADPServiceTestCase {
	SamplePersistable samplePersistable;
	FindCriteria<SamplePersistable> findCriteria;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		findCriteria = null;
		
		samplePersistable = new SamplePersistable();
		samplePersistable.setName("name");
		samplePersistable.setDescription("This is a sample persistable object");
		
		DaoTestUtil.assertSave(samplePersistable, samplePersistableDao);
	}
	
	@Test
	public void initialResultSize() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL).build();
		
		assertEquals(1, samplePersistableDao.findBy(findCriteria).size());
	}
	
	@Test
	public void persistableValueEqualToTestedValue() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.whereNotEqualTo(SamplePersistable.NAME, samplePersistable.getName()).build();
		
		assertEquals(0, samplePersistableDao.findBy(findCriteria).size());
	}
	
	@Test
	public void persistableValueNotEqualToTestedValue() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.whereNotEqualTo(SamplePersistable.NAME, StringUtils.EMPTY).build();
		
		assertEquals(1, samplePersistableDao.findBy(findCriteria).size());		
	}

}
