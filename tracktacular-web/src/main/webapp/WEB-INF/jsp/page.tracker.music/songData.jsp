<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Song Data --%>	
	<page:persistableData persistable="${song}">
		<j:property name="album" value="${song.album}"/>
		<j:property name="artist" value="${song.artist}"/>
		<j:property name="title" value="${song.title}"/>
		<j:property name="tag" value="${song.tag}"/>
		<j:property name="instrument" value="${song.instrument}"/>
		<j:property name="notes" value="${song.notes}"/>
		<j:property name="progress" value="${song.progress}"/>
		<j:property name="rating" value="${song.rating}"/>
		<j:property name="completed" value="${song.completed}"/>
		<j:property name="targetDateString" value="${song.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${song.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>