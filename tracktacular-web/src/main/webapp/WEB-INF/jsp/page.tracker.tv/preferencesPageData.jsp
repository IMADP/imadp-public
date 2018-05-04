<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Preferences Page Data --%>
	<page:preferencesPageData>
		<j:property name="alertOnTargetDates" value="${trackerPreferences.alertOnTargetDates}"/>
	</page:preferencesPageData>

	<j:property name="importUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.tv.ImportShowsActionBean">	
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
    	</s:url>
	</j:property>