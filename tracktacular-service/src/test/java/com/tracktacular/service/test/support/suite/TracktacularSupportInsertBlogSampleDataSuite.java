package com.tracktacular.service.test.support.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tracktacular.service.test.support.CleanDatabaseTest;
import com.tracktacular.service.test.support.InsertBlogSampleDataTest;

/**
 * TracktacularSupportInsertBlogSampleDataSuite
 *
 * This suite provides a fresh database with immutable and sample data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith(Suite.class)
@SuiteClasses({
	CleanDatabaseTest.class,
	InsertBlogSampleDataTest.class})
public class TracktacularSupportInsertBlogSampleDataSuite {

}
