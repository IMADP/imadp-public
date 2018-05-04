package com.imadp.service.vote;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.imadp.core.cache.Cache;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * VoteServiceMemoryImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class VoteServiceMemoryImplTest extends IMADPServiceTestCase {
		
	public static class IsFoundBy extends IMADPServiceTestCase {
		SampleVotable sampleVotable1;
		SampleVotable sampleVotable2;
		User user;
		SampleVote vote;
		
		@Override
		public void before() throws Exception {	
			super.before();
			
			user = new User();
			
			userService.save(user);
			
			sampleVotable1 = new SampleVotable();
			sampleVotable2 = new SampleVotable();
			
			sampleVotableService.save(sampleVotable1);
			sampleVotableService.save(sampleVotable2);
			
			vote = new SampleVote(user, sampleVotable1, 1);
			
			voteService.save(vote);
		}

		@Test 
		public void isVoteFound() {
			assertTrue(voteServiceMemory.isFoundByUser(user, sampleVotable1));
		}
		
		@Test 
		public void isVoteNotFound() {
			assertFalse(voteServiceMemory.isFoundByUser(user, sampleVotable2));
		}
			
		@Test 
		public void isVoteFoundCache() {
			Cache<User, Cache<FindCriteria<SampleVote>, List<SampleVote>>> cache = 
				voteServiceMemory.getFindByUserFindCriteriaCache();
			
			assertFalse(cache.isKeyInCache(user));
			
			assertTrue(voteServiceMemory.isFoundByUser(user, sampleVotable1));
			
			assertTrue(cache.isKeyInCache(user));
		}
				
	}		
	
	public static class IsFoundByMultipleVotes extends IMADPServiceTestCase {
		SampleVotable sampleVotable1;
		SampleVotable sampleVotable2;
		SampleVotable sampleVotable3;
		SampleVotable sampleVotable4;
		SampleVotable sampleVotable5;
		User user;
		SampleVote vote1;
		SampleVote vote2;
		SampleVote vote3;
		
		@Override
		public void before() throws Exception {	
			super.before();
			
			sampleVotable1 = new SampleVotable();
			sampleVotable2 = new SampleVotable();
			sampleVotable3 = new SampleVotable();
			sampleVotable4 = new SampleVotable();
			sampleVotable5 = new SampleVotable();
			
			user = new User();
			
			userService.save(user);
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
			assertTrue(voteServiceMemory.isFoundByUser(user, sampleVotable1));
			assertTrue(voteServiceMemory.isFoundByUser(user, sampleVotable2));
			assertTrue(voteServiceMemory.isFoundByUser(user, sampleVotable3));
		}
		
		@Test 
		public void isVoteNotFound() {
			assertFalse(voteServiceMemory.isFoundByUser(user, sampleVotable4));
			assertFalse(voteServiceMemory.isFoundByUser(user, sampleVotable5));
		}
			
	}	
	
}
