<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- TasksPageDataTaskCategory --%>
	<j:array var="category" name="${name}" items="${categories}" >
		<j:object>
			<page:persistableData persistable="${category}">
				<j:property name="name" value="${category.name}"/>
				<j:property name="root" value="${category.root}"/>
				<j:property name="path" value="${category.path}"/>
				<j:property name="depth" value="${category.depth}"/>
				<j:property name="categoryCollapsed" value="${category.collapsed}"/>
				<j:property name="categoryCollapsible" value="${!empty category.tasks || !empty category.childCategories}"/>
				<j:property name="totalTasks" value="${category.priorities.total}"/>
				<j:property name="hidden" value="${category.root && actionBean.selectedTaskCategory != 'all' && actionBean.selectedTaskCategory != category.uid}"/>
				<j:property name="categorySortable" value="${(category.root && actionBean.taskCategories.rootCategoriesSortable) || (!category.root && category.parentCategory.childCategoriesSortable)}"/>
				<j:property name="subCategorizable" value="${category.depth < 5}"/>
				<j:property name="tasksSortable" value="${!empty category.tasks}"/>
				<j:property name="deletable" value="${empty category.tasks && empty category.childCategories}"/>
				<j:property name="rootCategoryId" value="${category.rootCategory.uid}"/>
				<j:property name="parentCategoryId" value="${category.parentCategory.uid}"/>
			</page:persistableData>
			
			<j:object name="newSubCategory">
				<j:property name="id" value="${category.uid}-newSubCategory"/>
				<j:property name="parentCategoryId" value="${category.uid}"/>
			</j:object>   
			
			<j:object name="newTask">
				<j:property name="id" value="${category.uid}-newTask"/>				
				<j:property name="priority" value="MEDIUM"/>     
				<j:property name="category" value="${category.uid}"/>
			</j:object>   
			
			<j:array var="task" name="tasks" items="${category.tasks}" >
				<j:object>
					<i:include name="task" value="${task}" page="taskData.jsp" />
					<j:property name="category" value="${category.uid}"/>
					<j:property name="rootCategoryId" value="${category.rootCategory.uid}"/>
				</j:object>						  
			</j:array> 
			
			<c:if test="${category.root}">
				
				<%-- Navigation Chart Data --%>
				<j:object name="navigationChart">
					<j:property name="chartId" value="${category.uid}"/>
					<j:property name="chartLabel" value="${category.name}"/>
					<j:property name="selected" value="${actionBean.selectedTaskCategory != 'all' && actionBean.selectedTaskCategory == category.uid}"/>
					<j:property name="selectedTargetId" value="category-${category.uid}"/>
					<j:property name="selectedContainerClass" value="task-category-root-panel"/>
					
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
				
			</c:if>
			
			<%-- Neighboring Categories --%>
			<c:if test="${!category.root}">
				<j:array var="neighbor" name="neighboringCategories" items="${category.parentCategory.childCategories}" >
					<j:object>
						<j:property name="neighborId" value="${neighbor.uid}"/>     
	 					<j:property name="neighborName" value="${neighbor.name}"/>  
					</j:object>						  
				</j:array> 
			</c:if>
			
			<%-- Child Categories --%>
			<c:set var="categories" value="${category.childCategories}" scope="request" />
			<c:set var="name" value="childCategories" scope="request" />
			<jsp:include page="tasksPageDataTaskCategory.jsp" />
						
		</j:object>						  
	</j:array>