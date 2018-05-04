<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Item Category Data --%>
	<j:array var="category" name="${name}" items="${categories}" >
		<j:object>
			<j:property name="id" value="${category.uid}"/>
			<j:property name="activeState" value="${category.activeState}"/>
			<j:property name="archivedState" value="${category.archivedState}"/>
			<j:property name="deletedState" value="${category.deletedState}"/>
			<j:property name="name" value="${category.name}"/>
			<j:property name="depth" value="${category.depth}"/>
			<j:property name="root" value="${category.root}"/>
			<j:property name="path" value="${category.path}"/>
			<j:property name="categoryCollapsed" value="${category.collapsed}"/>
			<j:property name="categoryCollapsible" value="${!empty category.items || !empty category.childCategories}"/>
			<j:property name="income" value="${category.income}"/>
			<j:property name="categorySortable" value="${(category.root && category.budget.rootCategoriesSortable) || (!category.root && category.parentCategory.childCategoriesSortable)}"/>
			<j:property name="itemsSortable" value="${category.itemsSortable}"/>
			<j:property name="subCategorizable" value="${category.depth < 5}"/>
			<j:property name="deletable" value="${empty category.items && empty category.childCategories}"/>
			<j:property name="budgetId" value="${category.budget.uid}"/>
			<j:property name="rootCategoryId" value="${category.rootCategory.uid}"/>
			<j:property name="parentCategoryId" value="${category.parentCategory.uid}"/>
						
			<j:property name="netItemAmount">	
				<fmt:formatNumber value="${category.netItemAmount}" pattern="#,###,##0.00"/>
			</j:property>
						  
			<j:object name="newSubCategory">
				<j:property name="id" value="${category.uid}-newSubCategory"/>
				<j:property name="budgetId" value="${actionBean.budget.uid}"/>
				<j:property name="parentCategoryId" value="${category.uid}"/>
			</j:object> 
			  
			<j:object name="newItem">
				<j:property name="id" value="${category.uid}-newItem"/>
				<j:property name="category" value="${category.uid}"/>
			</j:object>  
			
			<%-- Items --%> 
			<j:array var="item" name="items" items="${category.items}" >
				<j:object>
					<j:property name="id" value="${item.uid}"/>     
 					<j:property name="itemsSortable" value="${item.category.itemsSortable}"/>
 					<j:property name="income" value="${item.income}"/>
					<j:property name="amount" value="${item.amount}"/>
					<j:property name="quantity" value="${item.quantity}"/>     
 					<j:property name="name" value="${item.name}"/>     
					<j:property name="notes" value="${item.notes}"/>   
					<j:property name="category" value="${item.category.uid}"/>
					<j:property name="budgetId" value="${item.budget.uid}"/>
					
 					<j:property name="totalItemAmount">	
						<fmt:formatNumber value="${item.totalItemAmount}" pattern="#,###,##0.00"/>
					</j:property> 
					  
				</j:object>						  
			</j:array> 
			
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
			<jsp:include page="budgetPageDataItemCategory.jsp" />
					
		</j:object>						  
	</j:array>