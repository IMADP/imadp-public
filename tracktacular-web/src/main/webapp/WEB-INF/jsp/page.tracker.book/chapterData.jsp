<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Chapter Data --%>	
	<page:persistableData persistable="${chapter}">
		<j:property name="bookId" value="${chapter.book.uid}"/>
		<j:property name="bookTitle" value="${chapter.book.title}"/>
		<j:property name="title" value="${chapter.title}"/>
		<j:property name="notes" value="${chapter.notes}"/>
		<j:property name="rating" value="${chapter.rating}"/>
		<j:property name="chaptersSortable" value="${chapter.book.chapterCount > 1}"/>
		<j:property name="completed" value="${chapter.completed}"/>
		<j:property name="targetDateString" value="${chapter.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${chapter.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>