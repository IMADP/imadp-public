<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	<s:layout-render name="/WEB-INF/jsp/page.tracker/abstractTrackerPage.jsp">	
	
		<%-- Content Data --%>	
		<s:layout-component name="contentData">
			<jsp:include page="/WEB-INF/jsp/page.tracker.${actionBean.tracker.name}/${actionBean.viewName}Data.jsp" />
			
			<!-- Tutorial Preferences -->	
			<j:property name="trackerTitle" value="${actionBean.trackerTitle}"/> 
			<j:property name="trackerTutorial" value="${actionBean.trackerPreferences.trackerTutorial}"/> 
			
			<j:property name="trackerDemoUrl">	
				<s:url beanclass="${actionBean.userToggleLink.beanClass}">   
			   		<c:forEach var="parameter" items="${link.parameters}">
						<s:param name="${parameter.key}" value="${parameter.value}"/>
					</c:forEach>
			   	</s:url>		
			</j:property>
			
			<j:property name="trackerReportUrl">	
				<s:url beanclass="com.tracktacular.web.page.tracker.${actionBean.tracker.name}.ReportActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				</s:url>	
			</j:property>
			
			<j:property name="trackerPreferencesUrl">	
				<s:url beanclass="com.tracktacular.web.page.tracker.${actionBean.tracker.name}.PreferencesActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				</s:url>	
			</j:property>
			
			<j:property name="tracktacularReportUrl">	
				<s:url beanclass="com.tracktacular.web.page.TracktacularReportActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				</s:url>				
			</j:property>
			
		</s:layout-component>	
		
	</s:layout-render>