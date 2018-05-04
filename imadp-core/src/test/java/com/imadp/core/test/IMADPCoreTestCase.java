package com.imadp.core.test;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imadp.core.cache.CacheManagerConcurrentImpl;
import com.imadp.core.cache.CacheManagerEhCacheImpl;
import com.imadp.core.email.EmailSenderJavaMailImpl;
import com.imadp.core.email.EmailSenderMockSuccessfulImpl;
import com.imadp.core.email.EmailSenderMockUnsuccessfulImpl;
import com.imadp.core.encryption.DigestorJasyptImpl;
import com.imadp.core.encryption.EncryptorJasyptImpl;
import com.imadp.core.random.RandomGeneratorNumericalRecipiesImpl;
import com.imadp.core.random.RandomGeneratorSecureRandomImpl;
import com.imadp.core.template.TemplateEngineVelocityImpl;

/**
 * IMADPCoreTestCase
 *
 * Provides an abstract test case for all tests.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/imadp/core/context/application.context.xml"})
public abstract class IMADPCoreTestCase {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// imadpCore.context
	@Resource protected CacheManagerConcurrentImpl cacheManagerConcurrent;
	@Resource protected CacheManagerEhCacheImpl cacheManagerEhCache;
	@Resource protected DigestorJasyptImpl digestorJasypt;
	@Resource protected EncryptorJasyptImpl encryptorJasypt;
	@Resource protected EmailSenderJavaMailImpl emailSenderJavaMail;
	@Resource protected EmailSenderMockSuccessfulImpl emailSenderMockSuccessful;
	@Resource protected EmailSenderMockUnsuccessfulImpl emailSenderMockUnsuccessful;
	@Resource protected TemplateEngineVelocityImpl templateEngineVelocity;
	@Resource protected RandomGeneratorNumericalRecipiesImpl randomGeneratorNumericalRecipies;
	@Resource protected RandomGeneratorSecureRandomImpl randomGeneratorSecureRandom;

	@Before
	public void before() throws Exception {

	}

	@After
	public void after() throws Exception {

	}

}
