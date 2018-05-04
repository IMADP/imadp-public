<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Report --%>
	<page:trackerReport>
		<j:property name="routineCount" value="${trackerReport.routineCount}"/>     
		
		<j:array var="routineDto" name="activeRoutines" items="${trackerReport.routines}" >
			<j:object>
				<i:include name="routineDto" value="${routineDto}" page="routineDtoData.jsp" />
				
				<j:object name="chartData">
					<j:property name="workoutCount" value="${routineDto.workoutCount}"/>     
				</j:object>
				
			</j:object>		  
		</j:array>
		   
		<j:array var="routine" name="lateRoutines" items="${trackerReport.lateRoutines}" >
			<j:object>
				<j:property name="name" value="${routine.name}"/>
				<j:property name="type" value="${routine.alertRecurrence.type}"/> 
				<j:property name="length" value="${routine.alertRecurrence.length}"/>
			</j:object>		  
		</j:array>   
		
	</page:trackerReport>