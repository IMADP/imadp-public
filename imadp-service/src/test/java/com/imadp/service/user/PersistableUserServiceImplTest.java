package com.imadp.service.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.imadp.core.cache.Cache;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.category.Category;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * PersistableUserServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableUserServiceImplTest extends IMADPServiceTestCase {

	public static class PersistableOperations extends IMADPServiceTestCase {
		Category category;
		
		@Override
		public void before() throws Exception {
			super.before();
			
			User user = new User();
			
			category = new Category();
			category.setName("name");
			category.setUser(user);

			userService.save(user);				
		}
		
		@Test
		public void commonPersistableOperations() throws Exception {		
			ServiceTestUtil.assertPersistable(category, categoryService);
		}
		
	}
	
	public static class FindByUserFindCriteriaCache extends IMADPServiceTestCase {
		User user1;
		User user2;
		Category category1;		
		Category category2;
		
		@Override
		public void before() throws Exception {
			super.before();
		
			user1 = new User();
			user2 = new User();
			
			category1 = new Category();
			category1.setName("category1");
			category1.setUser(user1);

			category2 = new Category();
			category2.setName("category2");
			category2.setUser(user2);
			
			userService.save(user1);				
			userService.save(user2);				
		}
		
		@Test
		public void findByUserFindCriteriaCache() throws Exception {
			Cache<User, Cache<FindCriteria<Category>, List<Category>>> cache = 
				categoryService.getFindByUserFindCriteriaCache();
						
			assertFalse(cache.isKeyInCache(user1));
			
			categoryService.findByUser(user1, CriteriaParams.<Category>of(Results.ONE));
			
			assertTrue(cache.isKeyInCache(user1));
			assertFalse(cache.isKeyInCache(user2));	
			
			categoryService.findByUser(user2,  CriteriaParams.<Category>of(Results.ONE));
			
			assertTrue(cache.isKeyInCache(user2));			
			assertTrue(cache.isKeyInCache(user1));	
			
			categoryService.save(category1);					
			
			assertFalse(cache.isKeyInCache(user1));	
			assertTrue(cache.isKeyInCache(user2));	
						
			categoryService.findByUser(user1,  CriteriaParams.<Category>of(Results.ONE));
			
			assertTrue(cache.isKeyInCache(user1));	
			assertTrue(cache.isKeyInCache(user2));	
			
			categoryService.delete(category2);					
			
			assertTrue(cache.isKeyInCache(user1));	
			assertFalse(cache.isKeyInCache(user2));	
			
			categoryService.delete(category1);					
							
			assertFalse(cache.isKeyInCache(user1));	
			assertFalse(cache.isKeyInCache(user2));				
		}   
	}

	public static class FindCountByUserFindCriteriaCache extends IMADPServiceTestCase {
		User user1;
		User user2;
		Category category1;		
		Category category2;
		
		@Override
		public void before() throws Exception {
			super.before();
		
			user1 = new User();
			user2 = new User();
			
			category1 = new Category();
			category1.setName("category1");
			category1.setUser(user1);

			category2 = new Category();
			category2.setName("category2");
			category2.setUser(user2);
			
			userService.save(user1);				
			userService.save(user2);				
		}
		
		@Test
		public void findCountByUserFindCriteriaCache() throws Exception {
			Cache<User, Cache<FindCriteria<Category>, Long>> cache = 
				categoryService.getFindCountByUserFindCriteriaCache();
						
			assertFalse(cache.isKeyInCache(user1));
			
			
			categoryService.findCountByUser(user1);
			
			assertTrue(cache.isKeyInCache(user1));
			assertFalse(cache.isKeyInCache(user2));	
			
			categoryService.findCountByUser(user2);
			
			assertTrue(cache.isKeyInCache(user2));			
			assertTrue(cache.isKeyInCache(user1));	
			
			categoryService.save(category1);					
			
			assertFalse(cache.isKeyInCache(user1));	
			assertTrue(cache.isKeyInCache(user2));	
						
			categoryService.findCountByUser(user1);
			
			assertTrue(cache.isKeyInCache(user1));	
			assertTrue(cache.isKeyInCache(user2));	
			
			categoryService.delete(category2);					
			
			assertTrue(cache.isKeyInCache(user1));	
			assertFalse(cache.isKeyInCache(user2));	
			
			categoryService.delete(category1);					
							
			assertFalse(cache.isKeyInCache(user1));	
			assertFalse(cache.isKeyInCache(user2));				
		}   
	}
	
}
