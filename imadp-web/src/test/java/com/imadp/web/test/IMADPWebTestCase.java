package com.imadp.web.test;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.imadp.core.cache.CacheManager;
import com.imadp.service.commerce.order.OrderServiceImpl;
import com.imadp.service.commerce.product.ProductServiceImpl;
import com.imadp.service.location.country.CountryServiceImpl;
import com.imadp.service.user.UserService;
import com.imadp.web.commerce.order.billing.BillingTransactionDispatcherPaypalImpl;

/**
 * IMADPWebTestCase
 *
 * Provides an abstract test case for all tests.
 *
 * @version 1.0
 * @author Anthony DePalma
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/imadp/web/context/application.context.xml"})
@TransactionConfiguration(defaultRollback = false)
public abstract class IMADPWebTestCase {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// imadpCoreCache.context
	@Resource protected CacheManager cacheManager;

	// imadpService.context
	@Resource(name = "&sessionFactory") protected LocalSessionFactoryBean sessionFactory;
	@Resource protected UserService userService;
	@Resource protected CountryServiceImpl countryService;
	@Resource protected OrderServiceImpl orderService;
	@Resource protected ProductServiceImpl productService;

	// imadpWebCommerce.context
	@Resource protected BillingTransactionDispatcherPaypalImpl billingTransactionDispatcherPaypal;

	@Before
	public void before() throws Exception {
		cacheManager.clearAll();
		sessionFactory.dropDatabaseSchema();
		sessionFactory.createDatabaseSchema();
	}

	@After
	public void after() throws Exception {

	}

}