package com.imadp.service.vote;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.DaoTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * SampleVoteDaoImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Transactional
public class VoteDaoImplTest extends IMADPServiceTestCase {
	User user1;
	User user2;
	User user3;
	SampleVote vote1;
	SampleVote vote2;
	SampleVote vote3;
	SampleVotable sampleVotable1;
	SampleVotable sampleVotable2;

	@Override
	public void before() throws Exception {	
		super.before();

		user1 = new User();
		user2 = new User();
		user3 = new User();

		sampleVotable1 = new SampleVotable();
		sampleVotable2 = new SampleVotable();
		
		vote1 = new SampleVote(user1, sampleVotable1, 5);
		vote2 = new SampleVote(user2, sampleVotable1, 10);
		vote3 = new SampleVote(user3, sampleVotable1, -5);

		userService.save(user1);
		userService.save(user2);
		userService.save(user3);
		
		sampleVotableService.save(sampleVotable1);
		sampleVotableService.save(sampleVotable2);
		
		DaoTestUtil.assertSave(vote1, voteDao);				
		DaoTestUtil.assertSave(vote2, voteDao);				
		DaoTestUtil.assertSave(vote3, voteDao);				
	}

	@Test
	public void findRatingSum() {
		assertEquals(10, voteDao.findRatingSumBy(sampleVotable1));
	}

	@Test
	public void findNoRatingSum() {
		assertEquals(0, voteDao.findRatingSumBy(sampleVotable2));
	}

}
