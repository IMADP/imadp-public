package com.imadp.service.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;


/**
 * ReportServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ReportServiceImplTest extends IMADPServiceTestCase {
	Report report;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		User user = new User();
		
		report = new Report();
		report.setTitle("title");
		report.setSummary("summary");
		report.setDate(new DateTime());
		report.setUser(user);
		
		userService.save(user);
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(report, reportService);
	}	
	
	@Test
	public void findByTitle() {
		reportService.save(report);
		
		assertFalse(reportService.findBy(report.getTitle(), CriteriaParams.<Report>of(Results.ALL)).isEmpty());
		assertEquals(report, reportService.findBy(report.getTitle(), CriteriaParams.<Report>of(Results.ALL)).get(0));
		assertTrue(reportService.findBy(report.getSummary(), CriteriaParams.<Report>of(Results.ALL)).isEmpty());
		
		assertEquals(1, reportService.findCountBy(report.getTitle()));
		assertEquals(0, reportService.findCountBy(report.getSummary()));
	}	

}