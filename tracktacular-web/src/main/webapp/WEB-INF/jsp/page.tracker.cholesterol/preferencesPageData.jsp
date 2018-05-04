<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Preferences Page Data --%>
	<page:preferencesPageData>
		<j:object name="alertRecurrence">
			<j:property name="type" value="${trackerPreferences.alertRecurrence.type}"/> 
			<j:property name="length" value="${trackerPreferences.alertRecurrence.length}"/>
		</j:object>
	</page:preferencesPageData>

	<j:property name="importUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.cholesterol.ImportCholesterolActionBean">	
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
    	</s:url>
	</j:property>
	
	<page:recurrenceTypes/>