<%@ include file="/WEB-INF/jsp/includeJsp.jsp"%>

<s:layout-render
	name="/WEB-INF/jsp/page.tracker/trackerAboutPage.jsp">

	<s:layout-component name="metaDescription">
		Keep track of your favorite books. Add notes, reviews, and ratings for books you've already read.
	</s:layout-component>

	<s:layout-component name="metaKeywords">
		books, book tracker, track books, book reminders, upcoming books
	</s:layout-component>

	<s:layout-component name="contentBodyTrackerContent">
		Like to read? The Book Tracker makes it easy to keep track of books you've read and books you're 
		planning to read. Set target dates for books you have yet to read, or rate and add notes or reviews for books
		you've already read. The Book Tracker makes it easy to organize your books by author or tag, and target dates
		are automatically integrated with the Tracktacular Calendar.
	</s:layout-component>
	
</s:layout-render>