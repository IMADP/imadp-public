<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>
	<j:property name="deletedTasksCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="task" name="deletedTasks" items="${actionBean.itemsPageProvider.page.items}" >
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
	
		
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />