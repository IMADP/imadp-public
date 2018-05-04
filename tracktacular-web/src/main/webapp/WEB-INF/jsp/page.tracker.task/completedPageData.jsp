<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Completed Page Data --%>
	<j:property name="completedTasksCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="task" name="completedTasks" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="task" value="${task}" page="taskData.jsp" />
			<j:property name="persistableState" value="ACTIVE"/>
		</j:object>						  
	</j:array>	
		
	<j:array var="category" name="allCategories" items="${actionBean.taskCategories.allCategories}" >
		<j:object>
			<j:property name="categoryId" value="${category.uid}"/>     
			<j:property name="categoryPath" value="${category.path}"/>
		</j:object>						  
	</j:array>   
	
	<j:object name="chartData">
		<j:array name="tasksMonthList" items="${actionBean.completedTaskStatistics.tasksMonthList}"/> 		
		<j:array name="lowPriorityTasksByMonthList" items="${actionBean.completedTaskStatistics.lowPriorityTasksByMonthList}"/> 		
		<j:array name="mediumPriorityTasksByMonthList" items="${actionBean.completedTaskStatistics.mediumPriorityTasksByMonthList}"/> 		
		<j:array name="highPriorityTasksByMonthList" items="${actionBean.completedTaskStatistics.highPriorityTasksByMonthList}"/> 		
	</j:object>
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />