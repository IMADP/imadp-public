package com.tracktacular.service.test.support.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tracktacular.service.test.support.CleanDatabaseTest;

/**
 * TracktacularSupportCleanDatabaseSuite
 * 
 * This suite provides a clean database, dropping and re-creating all tables.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith(Suite.class)
@SuiteClasses({CleanDatabaseTest.class})
public class TracktacularSupportCleanDatabaseSuite {
	
}
