<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Preferences Page Data --%>
	<page:preferencesPageData>
		<j:property name="alertOnTargetDates" value="${trackerPreferences.alertOnTargetDates}"/>
	</page:preferencesPageData>
	