<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Routine Dto Data --%>
	<page:persistableData persistable="${routineDto.routine}">
		<j:property name="workoutCount" value="${routineDto.workoutCount}"/>     
		<j:property name="name" value="${routineDto.routine.name}"/>
		<j:property name="notes" value="${routineDto.routine.notes}"/>
		<j:property name="description" value="${routineDto.routine.description}"/>
		<j:property name="startDateString" value="${routineDto.routine.startDateString}"/>
		
		<j:object name="alertRecurrence">
			<j:property name="type" value="${routineDto.routine.alertRecurrence.type}"/> 
			<j:property name="length" value="${routineDto.routine.alertRecurrence.length}"/>
		</j:object>
		
		<j:property name="startDate">
			<i:date value="${routineDto.routine.startDate}" pattern="MMM d, yyyy" /> 
		</j:property>
		
		<j:property name="viewRoutineUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.exercise.RoutineActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="nameSlug" value="${routineDto.routine.nameSlug}"/>
				<s:param name="routine" value="${routineDto.routine.uid}"/>
			</s:url>
		</j:property>
	
	</page:persistableData>