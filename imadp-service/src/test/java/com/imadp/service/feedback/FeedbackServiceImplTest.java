package com.imadp.service.feedback;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;


/**
 * FeedbackServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class FeedbackServiceImplTest extends IMADPServiceTestCase {
	Feedback feedback;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();
		
		feedback = new Feedback();
		feedback.setContent("content");
		feedback.setUser(user);
		
		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(feedback, feedbackService);
	}

}