<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Preferences Page Data --%>
	<page:preferencesPageData>
		<j:property name="alertOnBirthdays" value="${trackerPreferences.alertOnBirthdays}"/>
	</page:preferencesPageData>
