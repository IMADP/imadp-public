<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Show Data --%>	
	<page:persistableData persistable="${show}">	
		<j:property name="title" value="${show.title}"/>
		<j:property name="tag" value="${show.tag}"/>
		<j:property name="notes" value="${show.notes}"/>
		<j:property name="rating" value="${show.rating}"/>
		<j:property name="showCollapsed" value="true"/>
		<j:property name="episodesSortable" value="${show.episodeCount > 1}"/>
		<j:property name="completed" value="${show.completed}"/>
		<j:property name="targetDateSet" value="${show.targetDate != null}"/>
		<j:property name="targetDateString" value="${show.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${show.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
		<j:object name="newEpisode">
			<j:property name="id" value="${show.uid}-newEpisode"/>
			<j:property name="showId" value="${show.uid}"/>
			<j:property name="showTitle" value="${show.title}"/>
		</j:object>
		
		<j:array var="episode" name="episodes" items="${show.episodes}" >
			<j:object>			
				<i:include name="episode" value="${episode}" page="episodeData.jsp" />
			</j:object>
		</j:array>
		
	</page:persistableData>