<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Routine Page Data --%>
	<j:property name="name" value="${actionBean.routine.name}"/>   
	<j:property name="workoutCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>   
	<j:property name="activeState" value="${actionBean.routine.activeState}"/>   
	<j:property name="archivedState" value="${actionBean.routine.archivedState}"/>   
	<j:property name="deletedState" value="${actionBean.routine.deletedState}"/>   
	
	<j:object name="newWorkout">
		<j:property name="id" value="newWorkout"/>
		<j:property name="routineId" value="${actionBean.routine.uid}"/>
		<j:property name="dateTimeString" value="${actionBean.newDateTimeString}" />
	</j:object>
	
	<j:property name="backUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.exercise.RoutinesActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		</s:url>
	</j:property>
	
	
	<%-- Workouts --%>
	<j:array var="workout" name="workouts" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="workout" value="${workout}" page="workoutData.jsp" />
		</j:object>						  
	</j:array>   
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />