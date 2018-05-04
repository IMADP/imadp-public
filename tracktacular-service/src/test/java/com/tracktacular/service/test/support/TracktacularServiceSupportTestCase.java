package com.tracktacular.service.test.support;

import java.util.List;
import java.util.Random;

import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.imadp.core.test.PackageSuite.ExcludeFromPackageSuite;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * TracktacularServiceSupportTestCase
 *
 * Provides an abstract test case for all support tests.
 * Support tests generally manipulate the database to put it in a consistent state
 * for functional testing.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ExcludeFromPackageSuite
@ContextConfiguration(locations={
		"/com/tracktacular/service/context/application.context.xml",
		"/com/tracktacular/service/test/support/context/support-application.context.xml"})
@TransactionConfiguration(defaultRollback = false)
public class TracktacularServiceSupportTestCase extends TracktacularServiceTestCase {

	// jdbc template
	protected JdbcTemplate jdbcTemplate;

	// random generator
	protected Random random;

	// user
	protected User user;

	@Override
	public void before() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);

		random = new Random();
	}

	/**
	 * Ensures that the protected User is saved in the database.
	 *
	 */
	protected void loadUser() {
		// retrieve the second user found in the database
		List<User> users = userService.findBy(CriteriaParams.<User>of(Results.ALL));

		if(users.size() == 1)
		{
			user = new User();
			userService.save(user);
		}
		else
		{
			user = users.get(1);
		}
	}

}