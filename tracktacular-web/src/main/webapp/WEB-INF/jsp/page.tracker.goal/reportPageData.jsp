<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Report --%>
	<page:trackerReport>
		<j:property name="goalCount" value="${trackerReport.goalCount}"/> 		
		<j:property name="progress" value="${trackerReport.averageProgress}"/>
		<j:property name="lateGoalsCount" value="${trackerReport.lateGoalsCount}"/> 		
		<j:property name="lateObjectivesCount" value="${trackerReport.lateObjectivesCount}"/> 		
		
  		<j:object name="navigationChart">
			<j:property name="chartId" value="all"/>
			<j:property name="chartLabel" value="All"/>
			<j:property name="externalLink" value="true"/>
					
			<j:property name="selectUrl">
				<s:url beanclass="com.tracktacular.web.page.tracker.goal.GoalsActionBean" >		
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				</s:url>
			</j:property>
			
			<j:object name="chartData">
				<j:property name="progress" value="${trackerReport.averageProgress}"/>
			</j:object>
			
		</j:object> 
		
		<j:array var="goal" name="lateGoals" items="${trackerReport.lateGoals}" >
			<j:object>
				<j:property name="name" value="${goal.name}"/>
				<j:property name="description" value="${goal.description}"/>
				
				<j:property name="targetDate"> 			
					<i:date value="${goal.targetDate}" pattern="MMM d, yyyy" />
				</j:property>
			</j:object>						  
		</j:array>
		
		<j:array var="objective" name="lateObjectives" items="${trackerReport.lateObjectives}" >
			<j:object>
				<j:property name="name" value="${objective.name}"/> 		
				<j:property name="goalName" value="${objective.goal.name}"/> 		
						
				<j:property name="targetDate"> 			
					<i:date value="${objective.targetDate}" pattern="MMM d, yyyy" />
				</j:property>
			</j:object>						  
		</j:array>		
		
		<%-- Goals --%>
		<j:array var="goal" name="activeGoals" items="${trackerReport.goals}" >
			<j:object>
				<i:include name="goal" value="${goal}" page="goalData.jsp" />
				
				<%-- Navigation Chart Data --%>
				<j:object name="navigationChart">
					<j:property name="chartId" value="${goal.uid}"/>
					<j:property name="chartLabel" value="${goal.name}"/>
					<j:property name="externalLink" value="true"/>
					
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
		
	</page:trackerReport>
	