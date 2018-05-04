package com.imadp.service.test;

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
import org.springframework.transaction.PlatformTransactionManager;

import com.imadp.core.cache.CacheManager;
import com.imadp.core.encryption.Digestor;
import com.imadp.dao.hibernate.PersistableDaoImpl;
import com.imadp.dao.hibernate.SamplePersistable;
import com.imadp.service.PersistableServiceImpl;
import com.imadp.service.account.Account;
import com.imadp.service.account.AccountFacadeImpl;
import com.imadp.service.account.authority.AuthorityServiceImpl;
import com.imadp.service.account.credentials.CredentialsServiceImpl;
import com.imadp.service.category.Category;
import com.imadp.service.category.CategoryServiceImpl;
import com.imadp.service.commerce.card.CardServiceImpl;
import com.imadp.service.commerce.cart.CartServiceImpl;
import com.imadp.service.commerce.cart.item.CartItemServiceImpl;
import com.imadp.service.commerce.event.EventServiceImpl;
import com.imadp.service.commerce.order.OrderFacadeImpl;
import com.imadp.service.commerce.order.OrderServiceImpl;
import com.imadp.service.commerce.order.billing.authorize.BillingServiceAuthorizeImpl;
import com.imadp.service.commerce.order.report.OrderReportServiceImpl;
import com.imadp.service.commerce.order.shipping.ShippingServiceEmailImpl;
import com.imadp.service.commerce.product.ProductServiceImpl;
import com.imadp.service.commerce.subscription.plan.SubscriptionPlanServiceImpl;
import com.imadp.service.email.PersistableEmailServiceAsynchronousImpl;
import com.imadp.service.email.PersistableEmailServiceSynchronousImpl;
import com.imadp.service.feedback.FeedbackServiceImpl;
import com.imadp.service.location.address.AddressServiceImpl;
import com.imadp.service.location.country.CountryServiceImpl;
import com.imadp.service.log.LogEntryServiceImpl;
import com.imadp.service.metrics.MetricsServiceImpl;
import com.imadp.service.person.PersonServiceImpl;
import com.imadp.service.report.Report;
import com.imadp.service.report.ReportServiceImpl;
import com.imadp.service.tag.SampleTag;
import com.imadp.service.tag.SampleTaggable;
import com.imadp.service.tag.TagDaoImpl;
import com.imadp.service.tag.TagServiceImpl;
import com.imadp.service.user.UserServiceImpl;
import com.imadp.service.vote.SampleVotable;
import com.imadp.service.vote.SampleVote;
import com.imadp.service.vote.VoteDaoImpl;
import com.imadp.service.vote.VoteServiceImpl;
import com.imadp.service.vote.VoteServiceMemoryImpl;

/**
 * IMADPServiceTestCase
 *
 * Provides an abstract test case for all tests.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/imadp/service/context/application.context.xml"})
@TransactionConfiguration(defaultRollback = false)
public abstract class IMADPServiceTestCase {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// imadpCoreCache.context
	@Resource protected CacheManager cacheManager;

	// imadpService.context
	@Resource(name = "&sessionFactory") protected LocalSessionFactoryBean sessionFactory;
	@Resource protected PlatformTransactionManager transactionManager;
	@Resource protected CategoryServiceImpl<Category> categoryService;
	@Resource protected EventServiceImpl eventService;
	@Resource protected FeedbackServiceImpl feedbackService;
	@Resource protected LogEntryServiceImpl logEntryService;
	@Resource protected PersistableDaoImpl<SamplePersistable> samplePersistableDao;
	@Resource protected PersistableServiceImpl<SamplePersistable> samplePersistableService;
	@Resource protected PersistableServiceImpl<SampleVotable> sampleVotableService;
	@Resource protected PersistableServiceImpl<SampleTaggable> sampleTaggableService;
	@Resource protected ReportServiceImpl<Report> reportService;
	@Resource protected UserServiceImpl userService;
	@Resource protected PersistableEmailServiceSynchronousImpl persistableEmailServiceSynchronous;
	@Resource protected PersistableEmailServiceAsynchronousImpl persistableEmailServiceAsynchronous;
	@Resource protected VoteDaoImpl<SampleVote> voteDao;
	@Resource protected VoteServiceImpl<SampleVote, SampleVotable> voteService;
	@Resource protected TagDaoImpl<SampleTag> tagDao;
	@Resource protected TagServiceImpl<SampleTag, SampleTaggable> tagService;
	@Resource protected VoteServiceMemoryImpl<SampleVote, SampleVotable> voteServiceMemory;
	@Resource protected AccountFacadeImpl<Account> accountFacade;
	@Resource protected AddressServiceImpl addressService;
	@Resource protected AuthorityServiceImpl authorityService;
	@Resource protected CountryServiceImpl countryService;
	@Resource protected CredentialsServiceImpl credentialsService;
	@Resource protected PersonServiceImpl personService;
	@Resource protected BillingServiceAuthorizeImpl billingServiceAuthorize;
	@Resource protected CardServiceImpl cardService;
	@Resource protected CartServiceImpl cartService;
	@Resource protected CartItemServiceImpl cartItemService;
	@Resource protected ProductServiceImpl productService;
	@Resource protected MetricsServiceImpl metricsService;
	@Resource protected OrderServiceImpl orderService;
	@Resource protected OrderReportServiceImpl orderReportService;
	@Resource protected OrderFacadeImpl orderFacade;
	@Resource protected SubscriptionPlanServiceImpl subscriptionPlanService;
	@Resource protected ShippingServiceEmailImpl shippingServiceEmail;
	@Resource protected Digestor digestor;

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
