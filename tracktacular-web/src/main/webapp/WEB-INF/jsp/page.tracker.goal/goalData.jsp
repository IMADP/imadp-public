<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

		
	<%-- Goal Data --%>
	<page:persistableData persistable="${goal}">
		<j:property name="objectivesCount" value="${goal.objectivesCount}"/> 
		<j:property name="name" value="${goal.name}"/> 
		<j:property name="notes" value="${goal.notes}"/> 
		<j:property name="description" value="${goal.description}"/> 
		<j:property name="goalCollapsed" value="${goal.collapsed || !goal.activeState}"/>     
		<j:property name="progress" value="${goal.archivedState ? 100 : goal.progress}"/>    
		<j:property name="progressTracker" value="${goal.progressTracker}"/>    
		<j:property name="hasObjectives" value="${goal.objectivesCount > 0}"/> 
		<j:property name="completedObjectivesCount" value="${goal.completedObjectivesCount}"/> 
		<j:property name="objectivesCount" value="${goal.objectivesCount}"/> 
		<j:property name="startDateString" value="${goal.startDateString}" />     
		<j:property name="targetDateString" value="${goal.targetDateString}" />     
			
		<j:property name="startDate"> 
			<i:date value="${goal.startDate}" pattern="MMM d, yyyy" />
		</j:property>
		
		<j:property name="targetDate"> 
			<i:date value="${goal.targetDate}" pattern="MMM d, yyyy" />
		</j:property>
		
		<j:object name="newObjective">
			<j:property name="id" value="${goal.uid}-newObjective"/>
			<j:property name="goalId" value="${goal.uid}"/>
		</j:object>   
			
		<j:property name="selectGoalUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.goal.GoalsActionBean">
				<s:param name="selectedGoal" value="${goal.uid}"/>
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			</s:url>
		</j:property>
		
		<%-- Objectives --%>
		<j:array var="objective" name="objectives" items="${goal.objectives}" >
			<j:object>
				<page:persistableData persistable="${objective}">
					<j:property name="name" value="${objective.name}"/>     
					<j:property name="notes" value="${objective.notes}"/>     
					<j:property name="completed" value="${objective.completed}"/>     
					<j:property name="late" value="${objective.late}"/>     
					<j:property name="targetDateString" value="${objective.targetDateString}" />     
					<j:property name="goalId" value="${objective.goal.uid}"/>
					
					<j:property name="targetDate">
						<i:date value="${objective.targetDate}" pattern="MMM d, yyyy" />
					</j:property>
					
				</page:persistableData>
			</j:object>								  
		</j:array>	
		
	</page:persistableData>