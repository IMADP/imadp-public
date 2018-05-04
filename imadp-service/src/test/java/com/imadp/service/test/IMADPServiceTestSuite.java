package com.imadp.service.test;

import org.junit.runner.RunWith;

import com.imadp.core.test.PackageSuite;
import com.imadp.core.test.PackageSuite.SuitePackages;


/**
 * IMADPCoreTestSuite
 * 
 * The main test suite to run all tests.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith (PackageSuite.class)
@SuitePackages({"com.imadp.dao", "com.imadp.service"})
public class IMADPServiceTestSuite {

}