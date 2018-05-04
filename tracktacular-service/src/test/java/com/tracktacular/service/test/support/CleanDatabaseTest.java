package com.tracktacular.service.test.support;

import org.junit.Test;


/**
 * CleanDatabaseTest
 * 
 * Drops all tables in the database and recreates them through the application context.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class CleanDatabaseTest extends TracktacularServiceSupportTestCase {
	
	@Test
	public void createTables() {
		sessionFactory.dropDatabaseSchema();
		sessionFactory.createDatabaseSchema();
	}
	
}
