package com.tracktacular.service.test.support;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.imadp.core.io.ParseUtil;
import com.imadp.service.account.Account;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsPassword;
import com.imadp.service.account.credentials.CredentialsUsername;
import com.imadp.service.user.User;


/**
 * InsertAccountImmutableDataTest
 *
 * Inserts read only data into the database.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class InsertAccountImmutableDataTest extends TracktacularServiceSupportTestCase {

	// sql resources
	@Resource protected String insertSubscriptionPlansSql;
	@Resource protected String insertAuthoritiesSql;
	@Resource protected String insertCountriesSql;

	private static final int TOTAL_SUBSCRIPTION_PLANS = 1;
	private static final int TOTAL_AUTHORITIES = 5;
	private static final int TOTAL_COUNTRIES = 201;

	@Test
	public void insertSubscriptionPlans() {
		if(subscriptionPlanService.findCount() == 0)
			for(String sql : ParseUtil.parseResourceAsList(insertSubscriptionPlansSql, ";"))
				jdbcTemplate.execute(sql);

		assertEquals(TOTAL_SUBSCRIPTION_PLANS, subscriptionPlanService.findCount());
	}

	@Test
	public void insertAuthorities() {
		if(authorityService.findCount() == 0)
			for(String sql : ParseUtil.parseResourceAsList(insertAuthoritiesSql, ";"))
				jdbcTemplate.execute(sql);

		assertEquals(TOTAL_AUTHORITIES, authorityService.findCount());
	}

	@Test
	public void insertLocations() {
		if(countryService.findCount() == 0)
			for(String sql : ParseUtil.parseResourceAsList(insertCountriesSql, ";"))
				jdbcTemplate.execute(sql);

		assertEquals(TOTAL_COUNTRIES, countryService.findCount());
	}

	@Test
	public void insertAccount() {
		User user = new User();
		user.setUid(accountFacade.getDemoUserUid());

		CredentialsEmail credentialsEmail = new CredentialsEmail();
		credentialsEmail.setEmail("tracktacular@gmail.com");

		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUsername("credentials");

		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password");
		credentialsPassword.setConfirmPassword("password");

		Account account = new Account();
		account.setUser(user);
		account.setCredentialsEmail(credentialsEmail);
		account.setCredentialsUsername(credentialsUsername);
		account.setCredentialsPassword(credentialsPassword);

		Credentials credentials = accountFacade.createUserAccount(account, Locale.getDefault());
		accountFacade.verifyEmail(user, credentials.getVerificationId());

		credentials = accountFacade.getCredentials(user);
		credentials.setUsername("demo");
		accountFacade.getCredentialsService().save(credentials);

		adminFacade.insertTrackerDemoData();
	}

	@Test
	@DirtiesContext
	public void reloadContext() {

	}

}