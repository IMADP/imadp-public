<%@ include file="/WEB-INF/tags/includeTag.tag" %>
	
	<c:set var="trackerPreferencesName" value="${actionBean.tracker.name}TrackerPreferences"/>
	<c:set var="trackerPreferences" value="${actionBean.trackerUserPreferences.trackers[trackerPreferencesName]}" scope="request"/>
	
	<j:property name="trackerName" value="${actionBean.tracker.name}"/>
	<j:property name="trackerPublicName" value="preferences.trackers.${actionBean.tracker.name}TrackerPreferences.trackerPublic"/>
	<j:property name="trackerPublic" value="${trackerPreferences.trackerPublic}"/>				
	<j:property name="trackerEnabledName" value="preferences.trackers.${actionBean.tracker.name}TrackerPreferences.trackerEnabled"/>
	<j:property name="trackerEnabled" value="${trackerPreferences.trackerEnabled}"/>
	<j:property name="trackerTutorialName" value="preferences.trackers.${actionBean.tracker.name}TrackerPreferences.trackerTutorial"/>
	<j:property name="trackerTutorial" value="${trackerPreferences.trackerTutorial}"/>
		
	<j:property name="trackerTitle">
		<fmt:message key="${actionBean.tracker}"/>
	</j:property> 
	
	<j:object name="trackers">
		<j:object name="${trackerPreferencesName}">
			<jsp:doBody/>
		</j:object>
	</j:object>
		
	<j:property name="tracktacularReportUrl">	
		<s:url beanclass="com.tracktacular.web.page.TracktacularReportActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		</s:url>				
	</j:property>