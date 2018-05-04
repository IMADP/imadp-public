package com.imadp.service.category;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;


/**
 * CategoryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CategoryServiceImplTest extends IMADPServiceTestCase {
	Category category;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		category = new Category(user);
		category.setName("name");
		category.setSort(1);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(category, categoryService);
	}

	@Test
	public void findParentsByUser() {
		categoryService.save(category);

		assertEquals(1, category.getDepth());

		addChild(category, "child1");

		assertEquals(1, categoryService.findCountByParentByUser(user, null));
	}

	@Test
	public void findByUserAndNameAndParent() {
		categoryService.save(category);

		assertEquals(1, category.getDepth());

		Category child = addChild(category, "child1");

		assertEquals(1, categoryService.findByUser(child.getUser(), child.getName(), child.getParentCategory(),
				CriteriaParams.<Category>of(Results.ONE)).size());
	}

	@Test
	public void findByUserAndNameAndNullParent() {
		categoryService.save(category);

		assertEquals(1, category.getDepth());

		addChild(category, "child1");

		assertEquals(1, categoryService.findByUser(category.getUser(), category.getName(),
				category.getParentCategory(), CriteriaParams.<Category>of(Results.ONE)).size());
	}

	@Test
	public void categoryHierarchy() {
		categoryService.save(category);
		Category child;

		assertEquals(1, category.getDepth());

		child = addChild(category, "child1");

		assertEquals(3, addChild(child, "subChild1").getDepth());
		assertEquals(3, addChild(child, "subChild2").getDepth());
		assertEquals(3, addChild(child, "subChild3").getDepth());

		assertEquals(2, child.getDepth());

		child = addChild(category, "child2");

		assertEquals(3, addChild(child, "subChild1").getDepth());
		assertEquals(3, addChild(child, "subChild2").getDepth());
		assertEquals(3, addChild(child, "subChild3").getDepth());

		assertEquals(2, child.getDepth());

		child = addChild(category, "child3");

		assertEquals(3, addChild(child, "subChild1").getDepth());
		assertEquals(3, addChild(child, "subChild2").getDepth());
		assertEquals(3, addChild(child, "subChild3").getDepth());

		assertEquals(2, child.getDepth());

		category = categoryService.get(category.getId());

		assertEquals(13, category.getTotalCategoryCount());
	}

	@Test
	public void flattenedChildCategories() {
		categoryService.save(category);
		Category child;

		assertEquals(1, category.getDepth());

		child = addChild(category, "child1");

		assertEquals(3, addChild(child, "subChild1").getDepth());
		assertEquals(3, addChild(child, "subChild2").getDepth());
		assertEquals(3, addChild(child, "subChild3").getDepth());

		assertEquals(2, child.getDepth());

		child = addChild(category, "child2");

		assertEquals(3, addChild(child, "subChild1").getDepth());
		assertEquals(3, addChild(child, "subChild2").getDepth());
		assertEquals(3, addChild(child, "subChild3").getDepth());

		assertEquals(2, child.getDepth());

		child = addChild(category, "child3");

		assertEquals(3, addChild(child, "subChild1").getDepth());
		assertEquals(3, addChild(child, "subChild2").getDepth());
		assertEquals(3, addChild(child, "subChild3").getDepth());

		assertEquals(2, child.getDepth());

		category = categoryService.get(category.getId());

		assertEquals(13, category.getTotalCategoryCount());
		assertEquals(13, category.getFlattenedCategories().size());
	}

	/**
	 * Adds and saves a child category.
	 *
	 * @param parent
	 * @param name
	 * @return Category
	 */
	private Category addChild(Category parent, String name) {
		Category category = new Category(user);
		category.setName(name);
		category.setParentCategory(parent);
		categoryService.save(category);
		return category;
	}

}