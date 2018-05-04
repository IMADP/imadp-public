<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report Segment --%>		
	<page:trackerReport>	
		<j:property name="showCount" value="${trackerReport.showCount}"/>	
		<j:property name="unratedShowCount" value="${trackerReport.unratedShowCount}"/>	
		
		<j:array var="show" name="unratedShows" items="${trackerReport.unratedShows}" >
			<j:object>
				<c:set var="show" value="${show}" scope="request" />
				<jsp:include page="showData.jsp" />
			</j:object>
		</j:array>
		
		<j:array var="show" name="targetDateShows" items="${trackerReport.targetDateShows}" >
			<j:object>
				<c:set var="show" value="${show}" scope="request" />
				<jsp:include page="showData.jsp" />
			</j:object>
		</j:array>	
		
		<j:array var="episode" name="targetDateEpisodes" items="${trackerReport.targetDateEpisodes}" >
			<j:object>
				<c:set var="episode" value="${episode}" scope="request" />
				<jsp:include page="episodeData.jsp" />
			</j:object>
		</j:array>	
		
		<j:object name="showsByTitle">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="title"/>
			<j:property name="color" value="#ff4540"/>
			<j:property name="urlLabel" value="By Title"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.tv.ShowsActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="title"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="showsByTag">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="tag"/>
			<j:property name="color" value="#589800"/>
			<j:property name="urlLabel" value="By Tag"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.tv.ShowsActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="tag"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="showsByRating">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="rating"/>
			<j:property name="color" value="#ffd300"/>
			<j:property name="urlLabel" value="By Rating"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.tv.ShowsActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="rating"/>
				</s:url>
			</j:property>
			
		</j:object>
						
	</page:trackerReport>
	