package com.tracktacular.service.test.support.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tracktacular.service.test.support.CleanDatabaseTest;
import com.tracktacular.service.test.support.InsertAccountImmutableDataTest;
import com.tracktacular.service.test.support.InsertAccountSampleDataTest;
import com.tracktacular.service.test.support.InsertBlogSampleDataTest;

/**
 * TracktacularSupportInsertAllSampleDataSuite
 *
 * This suite provides a fresh database with all immutable and all sample data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith(Suite.class)
@SuiteClasses({
	CleanDatabaseTest.class,
	InsertAccountImmutableDataTest.class,
	InsertAccountSampleDataTest.class,
	InsertBlogSampleDataTest.class})
public class TracktacularSupportInsertAllSampleDataSuite {

}