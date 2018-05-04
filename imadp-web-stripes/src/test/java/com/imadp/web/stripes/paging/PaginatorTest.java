package com.imadp.web.stripes.paging;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.imadp.dao.criteria.Results;
import com.imadp.web.stripes.AbstractActionBean;
import com.imadp.web.stripes.AbstractActionBeanContext;
import com.imadp.web.stripes.link.Link;



/**
 * PaginatorTest
 *
 * Tests the operations of the PageProvider class.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PaginatorTest extends TestCase {
    final long itemCount = 100;

    @Test
    public void testPaginatorFirstPage() {
        PageProvider<TestActionBean, String> paginator = new TestPaginator(10);
        paginator.setPageNumber(1);
        Page<TestActionBean, String> page = paginator.getPage();

        assertEquals(itemCount, page.getItemCount());
        assertEquals(1, page.getPageNumber());
        assertEquals(10, page.getItemsPerPage());
        assertEquals(10, page.getPageCount());
        assertEquals(10, page.getItems().size());
    }

    @Test
    public void testPaginatorLastPage() {
        PageProvider<TestActionBean, String> paginator = new TestPaginator(10);
        paginator.setPageNumber(10);
        Page<TestActionBean, String> page = paginator.getPage();

        assertEquals(itemCount, page.getItemCount());
        assertEquals(10, page.getPageNumber());
        assertEquals(10, page.getItemsPerPage());
        assertEquals(10, page.getPageCount());
        assertEquals(10, page.getItems().size());
    }

    @Test
    public void testPageNumberRange() {
        PageProvider<TestActionBean, String> paginator = new TestPaginator(10);
        paginator.setPageNumber(0);
        Page<TestActionBean, String> page = paginator.getPage();

        assertEquals(itemCount, page.getItemCount());
        assertEquals(1, page.getPageNumber());
        assertEquals(10, page.getItemsPerPage());
        assertEquals(10, page.getPageCount());
        assertEquals(10, page.getItems().size());

        paginator = new TestPaginator(10);
        paginator.setPageNumber(20);
        page = paginator.getPage();

        assertEquals(itemCount, page.getItemCount());
        assertEquals(10, page.getPageNumber());
        assertEquals(10, page.getItemsPerPage());
        assertEquals(10, page.getPageCount());
        assertEquals(10, page.getItems().size());
    }

    @Test
    public void testTrailingResults() {
        PageProvider<TestActionBean, String> paginator = new TestPaginator(99);
        paginator.setPageNumber(2);
        Page<TestActionBean, String> page = paginator.getPage();

        assertEquals(itemCount, page.getItemCount());
        assertEquals(2, page.getPageNumber());
        assertEquals(99, page.getItemsPerPage());
        assertEquals(2, page.getPageCount());
        assertEquals(2, page.getItems().size());
    }

    /**
     * TestPaginator
     *
     * Test implementation of the PageProvider class.
     *
     * @version 1.0
     * @author Anthony DePalma
     */
    public class TestPaginator extends PageProvider<TestActionBean, String> {

        public TestPaginator(int itemsPerPage) {
			super(null, itemsPerPage);
		}

		@Override
        protected long findItemCount() {
            return itemCount;
        }

        @Override
        protected List<String> findItems(Results results) {
            List<String> items = new ArrayList<>();

            for(long i = results.getFirstResult(); i < results.getFirstResult() + results.getMaxResults(); i++)
                items.add(String.valueOf(i));

            return items;
        }

        @Override
        protected Link createPageLink(Class<? extends TestActionBean> linkClass, long page) {
        	return null;
        }

    }

    /**
     * TestActionBean
     *
     * Test implementation of the AbstractActionBean class.
     *
     * @version 1.0
     * @author Anthony DePalma
     */
    public class TestActionBean extends AbstractActionBean<AbstractActionBeanContext<?>> {

    }

}