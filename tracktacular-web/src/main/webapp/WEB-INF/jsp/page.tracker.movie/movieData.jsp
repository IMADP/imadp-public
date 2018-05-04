<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Movie Data --%>	
	<page:persistableData persistable="${movie}">	
		<j:property name="title" value="${movie.title}"/>
		<j:property name="tag" value="${movie.tag}"/>
		<j:property name="notes" value="${movie.notes}"/>
		<j:property name="rating" value="${movie.rating}"/>
		<j:property name="completed" value="${movie.completed}"/>
		<j:property name="targetDateSet" value="${movie.targetDate != null}"/>
		<j:property name="targetDateString" value="${movie.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${movie.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>