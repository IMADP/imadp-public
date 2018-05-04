package com.tracktacular.service.test.support;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.blog.BlogCategory;
import com.imadp.service.blog.BlogEntry;



/**
 * InsertBlogSampleDataTest
 *
 * Inserts sample data into the database.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class InsertBlogSampleDataTest extends TracktacularServiceSupportTestCase {

	@Override
	public void before() throws Exception {
		super.before();

		loadUser();
	}

	@Test
	public void insertBlog() {
		BlogCategory blogCategory = new BlogCategory();
		blogCategory.setName("Tracktacular");

		adminFacade.saveBlogCategory(blogCategory);

		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setTitle("Life Logging");
		blogEntry.setDescription("The art of logging your life");
		blogEntry.setKeywords("life logging, life blogging");
		blogEntry.setContent("<h3>The art of logging your life</h3><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p>");
		blogEntry.setDate(new DateTime());
		blogEntry.setCategory(blogCategory);

		adminFacade.saveBlogEntry(blogEntry);

		blogCategory = new BlogCategory();
		blogCategory.setName("Personal");

		adminFacade.saveBlogCategory(blogCategory);

		blogEntry = new BlogEntry();
		blogEntry.setTitle("Privacy");
		blogEntry.setDescription("The Promise of Privacy");
		blogEntry.setKeywords("privacy");
		blogEntry.setContent("<h3>The Promise of Privacy</h3><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p>");
		blogEntry.setDate(new DateTime());
		blogEntry.setCategory(blogCategory);

		adminFacade.saveBlogEntry(blogEntry);

		blogCategory = new BlogCategory();
		blogCategory.setName("People");

		adminFacade.saveBlogCategory(blogCategory);

		blogEntry = new BlogEntry();
		blogEntry.setTitle("The Gift of Giving");
		blogEntry.setDescription("The Gift of Giving");
		blogEntry.setKeywords("gifts");
		blogEntry.setContent("<h3>The Gift of Giving</h3><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p><p>Sed varius lectus erat, eu ullamcorper felis hendrerit in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam erat volutpat. Cras dignissim vulputate sapien ut eleifend. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum rhoncus nisl sed dignissim. Vestibulum tincidunt, quam a commodo porta, lectus nisl vehicula nisi, in sodales sapien libero at lorem. Nam suscipit volutpat quam, eget vestibulum justo tincidunt non.</p>");
		blogEntry.setDate(new DateTime());
		blogEntry.setCategory(blogCategory);

		adminFacade.saveBlogEntry(blogEntry);
	}

}
