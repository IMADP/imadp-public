<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Report --%>
	<page:trackerReport>
		<j:property name="taskCount" value="${trackerReport.taskCount}"/> 
		<j:property name="rootCategoriesCount" value="${trackerReport.rootCategoriesCount}"/> 
		<j:property name="highPriorityTasksCount" value="${trackerReport.highPriorityTasksCount}"/>
		<j:property name="lateTasksCount" value="${trackerReport.lateTasksCount}"/>
		
		<j:array var="task" name="highPriorityTasks" items="${trackerReport.highPriorityTasks}" >
			<j:object>
				<j:property name="priority" value="${task.priority}"/> 
				<j:property name="name" value="${task.name}"/> 
				<j:property name="path" value="${task.category.path}"/> 
				
				<c:if test="${task.targetDate != null}">
					<j:property name="targetDate"> 
						<i:date value="${task.targetDate}" pattern="MMM d, yyyy" />
					</j:property> 
				</c:if>
			</j:object>						  
		</j:array>  
		
		<j:array var="task" name="lateTasks" items="${trackerReport.lateTasks}" >
			<j:object>
				<j:property name="priority" value="${task.priority}"/> 
				<j:property name="name" value="${task.name}"/> 
				<j:property name="path" value="${task.category.path}"/>
				
				<j:property name="targetDate"> 
					<i:date value="${task.targetDate}" pattern="MMM d, yyyy" />
				</j:property> 
			</j:object>						  
		</j:array> 
		
		<j:object name="navigationChart">
			<j:property name="chartId" value="all"/>
			<j:property name="chartLabel" value="All"/>
			<j:property name="externalLink" value="true"/>
					
			<j:property name="selectUrl">
				<s:url beanclass="com.tracktacular.web.page.tracker.task.TasksActionBean" >
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				</s:url>
			</j:property>
			
			<j:object name="chartData">
				<j:property name="lowPriorityTasks" value="${trackerReport.allPriorities.low}"/>
				<j:property name="mediumPriorityTasks" value="${trackerReport.allPriorities.medium}"/>
				<j:property name="highPriorityTasks" value="${trackerReport.allPriorities.high}"/> 
			</j:object>
			
		</j:object>
		
		<j:property name="selectTaskCategoryUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.task.TasksActionBean" >
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			</s:url>
		</j:property>
		
		<j:array var="category" name="rootCategoriesList" items="${trackerReport.rootCategoriesList}" >
			<j:object>
				<j:object name="chartData">
					<j:property name="lowPriorities" value="${category.priorities.low}"/>
					<j:property name="mediumPriorities" value="${category.priorities.medium}"/>
					<j:property name="highPriorities" value="${category.priorities.high}"/>
					<j:property name="totalPriorities" value="${category.priorities.total}"/>
				</j:object>					
				<j:property name="name" value="${category.name}"/>     
				<j:property name="selectTaskCategoryUrl">
					<s:url beanclass="com.tracktacular.web.page.tracker.task.TasksActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="selectedTaskCategory" value="${category.uid}" />
					</s:url>
				</j:property>
				
				<%-- Navigation Chart Data --%>
				<j:object name="navigationChart">
					<j:property name="chartId" value="${category.uid}"/>
					<j:property name="chartLabel" value="${category.name}"/>
					<j:property name="externalLink" value="true"/>
					
					<j:object name="chartData">
						<j:property name="lowPriorityTasks" value="${category.priorities.low}"/>
						<j:property name="mediumPriorityTasks" value="${category.priorities.medium}"/>
						<j:property name="highPriorityTasks" value="${category.priorities.high}"/>   
					</j:object>
					
					<j:property name="selectUrl">
						<s:url beanclass="com.tracktacular.web.page.tracker.task.TasksActionBean">
							<s:param name="selectedTaskCategory" value="${category.uid}"/>
							<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						</s:url>
					</j:property>
					
				</j:object> 
				
			</j:object>						  
		</j:array>	
		
	</page:trackerReport>
