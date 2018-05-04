package com.imadp.service.tag;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.DaoTestUtil;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
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
public class TagDaoImplTest extends IMADPServiceTestCase {
	User user;
	SampleTag tag1;
	SampleTag tag2;
	SampleTag tag3;
	SampleTag tag4;
	SampleTag tag5;
	SampleTag tag6;
	SampleTaggable sampleTaggable1;
	SampleTaggable sampleTaggable2;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		sampleTaggable1 = new SampleTaggable();
		sampleTaggable2 = new SampleTaggable();

		tag1 = new SampleTag(user, sampleTaggable1, "TAG1");
		tag1.setPersistableState(PersistableState.ACTIVE);

		tag2 = new SampleTag(user, sampleTaggable1, "TAG2");
		tag2.setPersistableState(PersistableState.ACTIVE);

		tag3 = new SampleTag(user, sampleTaggable2, "TAG2");
		tag3.setPersistableState(PersistableState.ACTIVE);

		tag4 = new SampleTag(user, sampleTaggable2, "TAG3");
		tag4.setPersistableState(PersistableState.ACTIVE);

		tag5 = new SampleTag(user, sampleTaggable2, "TAG4");
		tag5.setPersistableState(PersistableState.ACTIVE);

		tag6 = new SampleTag(user, sampleTaggable2, "TAG5");
		tag6.setPersistableState(PersistableState.DELETED);

		userService.save(user);

		sampleTaggableService.save(sampleTaggable1);
		sampleTaggableService.save(sampleTaggable2);

		DaoTestUtil.assertSave(tag1, tagDao);
		DaoTestUtil.assertSave(tag2, tagDao);
		DaoTestUtil.assertSave(tag3, tagDao);
		DaoTestUtil.assertSave(tag4, tagDao);
		DaoTestUtil.assertSave(tag5, tagDao);
		DaoTestUtil.assertSave(tag6, tagDao);
	}

	@Test
	public void findTagFrequencies() {
		List<TagFrequency> tagFrequencies = tagDao.findTagFrequencies(
				user, PersistableState.ACTIVE, CriteriaParams.<TagFrequency>of(Results.ALL,
						Order.<TagFrequency>desc(TagFrequency.FREQUENCY), Order.<TagFrequency>asc(TagFrequency.NAME_SLUG)));

		assertEquals(4, tagFrequencies.size());
		assertEquals("tag2", tagFrequencies.get(0).getNameSlug());
		assertEquals(2, tagFrequencies.get(0).getFrequency());
		assertEquals("tag1", tagFrequencies.get(1).getNameSlug());
		assertEquals(1, tagFrequencies.get(1).getFrequency());
		assertEquals("tag3", tagFrequencies.get(2).getNameSlug());
		assertEquals(1, tagFrequencies.get(2).getFrequency());
		assertEquals("tag4", tagFrequencies.get(3).getNameSlug());
		assertEquals(1, tagFrequencies.get(3).getFrequency());

		tagFrequencies = tagDao.findTagFrequencies(
				user, PersistableState.DELETED, CriteriaParams.<TagFrequency>of(Results.ALL,
						Order.<TagFrequency>desc(TagFrequency.FREQUENCY), Order.<TagFrequency>asc(TagFrequency.NAME_SLUG)));

		assertEquals(1, tagFrequencies.size());
		assertEquals("tag5", tagFrequencies.get(0).getNameSlug());

		tagFrequencies = tagDao.findTagFrequencies(
				user, null, CriteriaParams.<TagFrequency>of(Results.ALL,
						Order.<TagFrequency>desc(TagFrequency.FREQUENCY), Order.<TagFrequency>asc(TagFrequency.NAME_SLUG)));

		assertEquals(5, tagFrequencies.size());
		assertEquals("tag2", tagFrequencies.get(0).getNameSlug());
		assertEquals(2, tagFrequencies.get(0).getFrequency());
		assertEquals("tag1", tagFrequencies.get(1).getNameSlug());
		assertEquals(1, tagFrequencies.get(1).getFrequency());
		assertEquals("tag3", tagFrequencies.get(2).getNameSlug());
		assertEquals(1, tagFrequencies.get(2).getFrequency());
		assertEquals("tag4", tagFrequencies.get(3).getNameSlug());
		assertEquals(1, tagFrequencies.get(3).getFrequency());
		assertEquals("tag5", tagFrequencies.get(4).getNameSlug());
		assertEquals(1, tagFrequencies.get(4).getFrequency());
	}

	@Test
	public void findTagFrequencyCount() {
		assertEquals(4, tagDao.findTagFrequencyCount(user, PersistableState.ACTIVE));
		assertEquals(1, tagDao.findTagFrequencyCount(user, PersistableState.DELETED));
		assertEquals(5, tagDao.findTagFrequencyCount(user, null));
	}

}
