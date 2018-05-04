package com.imadp.dao.hibernate.criteria.find;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.DaoTestUtil;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.hibernate.SamplePersistable;
import com.imadp.service.test.IMADPServiceTestCase;

/**
 * WhereAfterOrOnDateTest
 * 
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Transactional
public class WhereAfterOrOnDateTest extends IMADPServiceTestCase {
	SamplePersistable samplePersistable;
	FindCriteria<SamplePersistable> findCriteria;
	DateTime startDate;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		findCriteria = null;
		startDate = new DateTime();
		
		samplePersistable = new SamplePersistable();
		samplePersistable.setName("name");
		samplePersistable.setStartDate(startDate);
		
		DaoTestUtil.assertSave(samplePersistable, samplePersistableDao);
	}
	
	@Test
	public void initialResultSize() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL).build();
		
		assertEquals(1, samplePersistableDao.findBy(findCriteria).size());
	}
	
	@Test
	public void persistableDateAfterTestedValue() throws Exception {		
		DateTime date = new DateMidnight().minusDays(5).toDateTime();		
		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.whereAfterOrOnDate(SamplePersistable.START_DATE, date).build();
		
		assertEquals(1, samplePersistableDao.findBy(findCriteria).size());
	}
	
	@Test
	public void persistableDateBeforeTestedValue() throws Exception {		
		DateTime date = new DateMidnight().plusDays(5).toDateTime();		
		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.whereAfterOrOnDate(SamplePersistable.START_DATE, date).build();
		
		assertEquals(0, samplePersistableDao.findBy(findCriteria).size());
	}
	
	@Test
	public void persistableDateOnTestedValue() throws Exception {		
		findCriteria = samplePersistableDao.findCriteriaBuilder(Results.ALL)
			.whereAfterOrOnDate(SamplePersistable.START_DATE, startDate).build();
		
		assertEquals(1, samplePersistableDao.findBy(findCriteria).size());
	}

}