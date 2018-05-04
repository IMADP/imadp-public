<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Books Page Data --%>	
	<j:property name="bookCount" value="${actionBean.books.bookCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:object name="booksByAuthor">
		<j:property name="selected" value="${'author' == actionBean.sortProperty}"/>
		<j:property name="id" value="author"/>
		<j:property name="color" value="#7092BE"/>
		<j:property name="urlLabel" value="By Author"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="author"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="booksByTitle">
		<j:property name="selected" value="${'title' == actionBean.sortProperty}"/>
		<j:property name="id" value="title"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="By Title"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="title"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="booksByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="booksByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="bookCategory" name="bookCategories" items="${actionBean.books.bookCategories}" >
		<j:object>			
			<j:property name="name" value="${bookCategory.name}"/>
			<j:property name="nameSlug" value="${bookCategory.nameSlug}"/>
			<j:property name="bookCount" value="${bookCategory.itemCount}"/>
	
			<j:array var="book" name="books" items="${bookCategory.items}" >
				<j:object>		
					<i:include name="book" value="${book}" page="bookData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>