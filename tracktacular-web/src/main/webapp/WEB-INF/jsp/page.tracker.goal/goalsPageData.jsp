<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Goals Page Data --%>
	<j:property name="goalCount" value="${actionBean.goals.goalCount}"/>
	
	<j:object name="newGoal">
		<j:property name="id" value="newGoal"/>
		<j:property name="progressTracker" value="TIME"/> 		
		<j:property name="startDateString" value="${actionBean.newDateString}" /> 
	</j:object>
	
	<j:object name="navigationChart">
		<j:property name="chartId" value="all"/>
		<j:property name="chartLabel" value="All"/>
		<j:property name="selected" value="${actionBean.selectedGoal == 'all'}"/>
		<j:property name="selectedAll" value="true"/>
		<j:property name="selectedContainerClass" value="goal-panel"/>
		
		<j:property name="selectUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.goal.GoalsActionBean" >		
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			</s:url>
		</j:property>
		
		<j:object name="chartData">
			<j:property name="progress" value="${actionBean.goals.averageProgress}"/>
		</j:object>
		
	</j:object> 
	
	
	<%-- Goals --%>
	<j:array var="goal" name="activeGoals" items="${actionBean.goals.goals}" >
		<j:object>
			<i:include name="goal" value="${goal}" page="goalData.jsp" />
			<j:property name="hidden" value="${actionBean.selectedGoal != 'all' && actionBean.selectedGoal != goal.uid}"/>
			
			<%-- Objective Clones --%>
			<j:array var="objective" name="objective-clones" items="${goal.objectives}" >			
				<j:object>
 					<j:property name="id" value="${objective.uid}-clone"/>     
					<j:property name="name" value="${objective.name}"/>     
					<j:property name="notes" value="${objective.notes}"/>   
					<j:property name="targetDateString" value="${objective.targetDateString}" />
					<j:property name="goalId" value="${objective.goal.uid}"/>
				</j:object>
			</j:array>
			
			<%-- Navigation Chart Data --%>
			<j:object name="navigationChart">
				<j:property name="chartId" value="${goal.uid}"/>
				<j:property name="chartLabel" value="${goal.name}"/>
				<j:property name="selected" value="${actionBean.selectedGoal != 'all' && actionBean.selectedGoal == goal.uid}"/>
				<j:property name="selectedTargetId" value="goal-${goal.uid}"/>
				<j:property name="selectedContainerClass" value="goal-panel"/>
				
				<j:object name="chartData">
					<j:property name="progress" value="${goal.progress}"/>
				</j:object>
				
				<j:property name="selectUrl">
					<s:url beanclass="com.tracktacular.web.page.tracker.goal.GoalsActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="selectedGoal" value="${goal.uid}"/>
					</s:url>
				</j:property>
				
			</j:object> 
			
		</j:object>						  
	</j:array>  
	
	<j:object name="chartData"> 
	 	<j:property name="minDate" value="${actionBean.goals.minDate.millis}"/>
		<j:property name="maxDate" value="${actionBean.goals.maxDate.millis}"/>
		
		<j:property name="currentDate">
			<i:date pattern="MMM d, yyyy" />
		</j:property>
		
		<j:array var="goal" name="goals" items="${actionBean.goals.goals}" >
			<j:object>
					<i:out><j:property name="name" value="${goal.name}"/></i:out>
			    <j:property name="progress" value="${goal.progress}"/>
			    <j:property name="startDateInMillis" value="${goal.startDate.millis}"/>
			    <j:property name="timeRemainingInMillis" value="${goal.timeRemainingInMillis}"/>
			    <j:property name="timeCompletedInMillis" value="${goal.timeCompletedInMillis}"/>	
			    
			    <j:property name="targetDate">
					<i:date value="${goal.targetDate}" pattern="MMM d, yyyy" />
				</j:property>	
																	      
			</j:object>						  
		</j:array>
	</j:object>			
	