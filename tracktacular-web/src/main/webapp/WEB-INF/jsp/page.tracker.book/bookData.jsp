<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Book Data --%>	
	<page:persistableData persistable="${book}">	
		<j:property name="title" value="${book.title}"/>
		<j:property name="author" value="${book.author}"/>
		<j:property name="tag" value="${book.tag}"/>
		<j:property name="notes" value="${book.notes}"/>
		<j:property name="rating" value="${book.rating}"/>
		<j:property name="bookCollapsed" value="true"/>
		<j:property name="chaptersSortable" value="${book.chapterCount > 1}"/>
		<j:property name="completed" value="${book.completed}"/>
		<j:property name="targetDateSet" value="${book.targetDate != null}"/>
		<j:property name="targetDateString" value="${book.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${book.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
		<j:object name="newChapter">
			<j:property name="id" value="${book.uid}-newChapter"/>
			<j:property name="bookId" value="${book.uid}"/>
			<j:property name="bookTitle" value="${book.title}"/>
		</j:object>
		
		<j:array var="chapter" name="chapters" items="${book.chapters}" >
			<j:object>			
				<i:include name="chapter" value="${chapter}" page="chapterData.jsp" />
			</j:object>
		</j:array>
		
	</page:persistableData>