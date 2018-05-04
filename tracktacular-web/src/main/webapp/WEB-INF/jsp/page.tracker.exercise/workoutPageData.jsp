<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Workout Page Data --%>
	<j:object name="workout">
		<i:include name="workout" value="${actionBean.workout}" page="workoutData.jsp" />
	</j:object>