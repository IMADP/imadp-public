package com.imadp.service.vote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.cache.Cache;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * VoteServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class VoteServiceImplTest extends IMADPServiceTestCase {

	public static class IsFoundBy extends IMADPServiceTestCase {
		User user;
		SampleVote vote;
		SampleVotable sampleVotable1;
		SampleVotable sampleVotable2;

		@Override
		public void before() throws Exception {
			super.before();

			sampleVotable1 = new SampleVotable();
			sampleVotable2 = new SampleVotable();

			user = new User();

			userService.save(user);

			sampleVotableService.save(sampleVotable1);
			sampleVotableService.save(sampleVotable2);

			vote = new SampleVote(user, sampleVotable1, 1);

			voteService.save(vote);
		}

		@Test
		public void isVoteFound() {
			assertTrue(voteService.isFoundByUser(user, sampleVotable1));
		}

		@Test
		public void isVoteNotFound() {
			assertFalse(voteService.isFoundByUser(user, sampleVotable2));
		}

	}

	public static class IsFoundByMultipleVotes extends IMADPServiceTestCase {
		User user;
		SampleVote vote1;
		SampleVote vote2;
		SampleVote vote3;
		SampleVotable sampleVotable1;
		SampleVotable sampleVotable2;
		SampleVotable sampleVotable3;
		SampleVotable sampleVotable4;
		SampleVotable sampleVotable5;

		@Override
		public void before() throws Exception {
			super.before();

			user = new User();

			userService.save(user);

			sampleVotable1 = new SampleVotable();
			sampleVotable2 = new SampleVotable();
			sampleVotable3 = new SampleVotable();
			sampleVotable4 = new SampleVotable();
			sampleVotable5 = new SampleVotable();

			sampleVotableService.save(sampleVotable1);
			sampleVotableService.save(sampleVotable2);
			sampleVotableService.save(sampleVotable3);
			sampleVotableService.save(sampleVotable4);
			sampleVotableService.save(sampleVotable5);

			vote1 = new SampleVote(user, sampleVotable1, 1);
			vote2 = new SampleVote(user, sampleVotable2, 1);
			vote3 = new SampleVote(user, sampleVotable3, 1);

			voteService.save(vote1);
			voteService.save(vote2);
			voteService.save(vote3);
		}

		@Test
		public void isVoteFound() {
			assertTrue(voteService.isFoundByUser(user, sampleVotable1));
			assertTrue(voteService.isFoundByUser(user, sampleVotable2));
			assertTrue(voteService.isFoundByUser(user, sampleVotable3));
		}

		@Test
		public void isVoteNotFound() {
			assertFalse(voteService.isFoundByUser(user, sampleVotable4));
			assertFalse(voteService.isFoundByUser(user, sampleVotable5));
		}

	}

	public static class FindTallyBy extends IMADPServiceTestCase {
		User user1;
		User user2;
		User user3;
		SampleVote vote1;
		SampleVote vote2;
		SampleVote vote3;
		SampleVotable sampleVotable1;

		@Override
		public void before() throws Exception {
			super.before();

			user1 = new User();
			user2 = new User();
			user3 = new User();

			sampleVotable1 = new SampleVotable();

			vote1 = new SampleVote(user1, sampleVotable1, 5);
			vote2 = new SampleVote(user2, sampleVotable1, 10);
			vote3 = new SampleVote(user3, sampleVotable1, -5);

			userService.save(user1);
			userService.save(user2);
			userService.save(user3);

			sampleVotableService.save(sampleVotable1);

			voteService.save(vote1);
			voteService.save(vote2);
			voteService.save(vote3);
		}

		@Test
		public void findTallyBy() {
			assertEquals(10, voteService.findTallyBy(sampleVotable1));
		}

		@Test
		public void findTallyByCache() {
			Cache<SampleVotable, Long> cache = voteService.getFindTallyByVotableCache();

			assertFalse(cache.isKeyInCache(sampleVotable1));

			assertEquals(10, voteService.findTallyBy(sampleVotable1));

			assertTrue(cache.isKeyInCache(sampleVotable1));

			assertEquals(10, voteService.findTallyBy(sampleVotable1));
		}

		@Test
		public void findTallyByCacheClear() {
			Cache<SampleVotable, Long> cache = voteService.getFindTallyByVotableCache();

			assertFalse(cache.isKeyInCache(sampleVotable1));

			assertEquals(10, voteService.findTallyBy(sampleVotable1));

			assertTrue(cache.isKeyInCache(sampleVotable1));

			assertEquals(10, voteService.findTallyBy(sampleVotable1));

			voteService.save(vote1);

			assertFalse(cache.isKeyInCache(sampleVotable1));

			assertEquals(10, voteService.findTallyBy(sampleVotable1));

			assertTrue(cache.isKeyInCache(sampleVotable1));

			voteService.delete(vote1);

			assertFalse(cache.isKeyInCache(sampleVotable1));
		}

	}

}