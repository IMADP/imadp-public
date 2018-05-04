package com.tracktacular.service.tracker.blood;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;

/**
 * BloodPressureServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BloodPressureServiceImplTest extends TracktacularServiceTestCase {
	BloodPressure bloodPressure;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		bloodPressure = new BloodPressure();
		bloodPressure.setSystolic(10);
		bloodPressure.setDiastolic(10);
		bloodPressure.setDate(new DateTime());
		bloodPressure.setUser(user);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(bloodPressure, blood_bloodPressureService);
	}

}