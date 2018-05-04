<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Game Data --%>	
	<page:persistableData persistable="${game}">	
		<j:property name="title" value="${game.title}"/>
		<j:property name="platform" value="${game.platform}"/>
		<j:property name="tag" value="${game.tag}"/>
		<j:property name="notes" value="${game.notes}"/>
		<j:property name="rating" value="${game.rating}"/>
		<j:property name="completed" value="${game.completed}"/>
		<j:property name="targetDateSet" value="${game.targetDate != null}"/>
		<j:property name="targetDateString" value="${game.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${game.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>