package com.imadp.service.tag;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.imadp.service.test.IMADPServiceTestCase;

/**
 * TagCloudTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TagCloudTest extends IMADPServiceTestCase {
	List<TagFrequency> tagFrequencies;

	@Override
	public void before() throws Exception {
		super.before();

		tagFrequencies = new ArrayList<>();
		tagFrequencies.add(new TagFrequency("One", 1));
		tagFrequencies.add(new TagFrequency("Five", 5));
		tagFrequencies.add(new TagFrequency("Ten", 10));
	}

	@Test
	public void tagCloud() {
		TagCloud tagCloud = new TagCloud(tagFrequencies, 3, 14);

		assertEquals(3.0, tagCloud.getTagCloudItems().get(0).getWeight(), 1);
		assertEquals(7.9, tagCloud.getTagCloudItems().get(1).getWeight(), 1);
		assertEquals(14.0, tagCloud.getTagCloudItems().get(2).getWeight(), 1);
	}

}
