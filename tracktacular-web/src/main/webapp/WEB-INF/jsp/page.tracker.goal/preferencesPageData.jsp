<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Preferences Page Data --%>
	<page:preferencesPageData>
		<j:property name="alertOnGoalTargetDates" value="${trackerPreferences.alertOnGoalTargetDates}"/>
		<j:property name="alertOnObjectiveTargetDates" value="${trackerPreferences.alertOnObjectiveTargetDates}"/>
	</page:preferencesPageData>