<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report Segment --%>		
	<page:trackerReport>	
		<j:property name="gameCount" value="${trackerReport.gameCount}"/>	
		<j:property name="unratedGameCount" value="${trackerReport.unratedGameCount}"/>	
		
		<j:array var="game" name="unratedGames" items="${trackerReport.unratedGames}" >
			<j:object>
				<c:set var="game" value="${game}" scope="request" />
				<jsp:include page="gameData.jsp" />
			</j:object>
		</j:array>
		
		<j:array var="game" name="targetDateGames" items="${trackerReport.targetDateGames}" >
			<j:object>
				<c:set var="game" value="${game}" scope="request" />
				<jsp:include page="gameData.jsp" />
			</j:object>
		</j:array>	
		
		<j:object name="gamesByTitle">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="title"/>
			<j:property name="color" value="#ff4540"/>
			<j:property name="urlLabel" value="By Title"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="title"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="gamesByPlatform">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="platform"/>
			<j:property name="color" value="#7092BE"/>
			<j:property name="urlLabel" value="By Platform"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="platform"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="gamesByTag">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="tag"/>
			<j:property name="color" value="#589800"/>
			<j:property name="urlLabel" value="By Tag"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="tag"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="gamesByRating">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="rating"/>
			<j:property name="color" value="#ffd300"/>
			<j:property name="urlLabel" value="By Rating"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="rating"/>
				</s:url>
			</j:property>
			
		</j:object>
								
	</page:trackerReport>
	