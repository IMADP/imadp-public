<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Episode Data --%>	
	<page:persistableData persistable="${episode}">
		<j:property name="showId" value="${episode.show.uid}"/>
		<j:property name="showTitle" value="${episode.show.title}"/>
		<j:property name="title" value="${episode.title}"/>
		<j:property name="notes" value="${episode.notes}"/>
		<j:property name="rating" value="${episode.rating}"/>
		<j:property name="episodesSortable" value="${episode.show.episodeCount > 1}"/>
		<j:property name="completed" value="${episode.completed}"/>
		<j:property name="targetDateString" value="${episode.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${episode.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>