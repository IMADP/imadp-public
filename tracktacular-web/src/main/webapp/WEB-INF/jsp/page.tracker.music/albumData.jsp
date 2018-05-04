<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Album Data --%>	
	<page:persistableData persistable="${album}">
		<j:property name="artist" value="${album.artist}"/>
		<j:property name="title" value="${album.title}"/>
		<j:property name="tag" value="${album.tag}"/>
		<j:property name="notes" value="${album.notes}"/>
		<j:property name="rating" value="${album.rating}"/>
		<j:property name="completed" value="${album.completed}"/>
		<j:property name="targetDateString" value="${album.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${album.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>