<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Tasks Page Data --%>
	<j:object name="navigationChart">
		<j:property name="chartId" value="all"/>
		<j:property name="chartLabel" value="All"/>
		<j:property name="selected" value="${actionBean.selectedTaskCategory == 'all'}"/>
		<j:property name="selectedAll" value="true"/>
		<j:property name="selectedContainerClass" value="task-category-root-panel"/>
		
		<j:property name="selectUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.task.TasksActionBean" >
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			</s:url>
		</j:property>
		
		<j:object name="chartData">
			<j:property name="lowPriorityTasks" value="${actionBean.taskCategories.allPriorities.low}"/>
			<j:property name="mediumPriorityTasks" value="${actionBean.taskCategories.allPriorities.medium}"/>
			<j:property name="highPriorityTasks" value="${actionBean.taskCategories.allPriorities.high}"/> 
		</j:object>
		
	</j:object> 

	<j:array var="category" name="allCategories" items="${actionBean.taskCategories.allCategories}" >
		<j:object>
			<j:property name="categoryId" value="${category.uid}"/>     
			<j:property name="categoryPath" value="${category.path}"/>
		</j:object>						  
	</j:array>   
	
	<%-- Root Categories --%>
	<c:set var="categories" value="${actionBean.taskCategories.rootCategories}" scope="request" />
	<c:set var="name" value="categories" scope="request" />
	<jsp:include page="tasksPageDataTaskCategory.jsp" />

