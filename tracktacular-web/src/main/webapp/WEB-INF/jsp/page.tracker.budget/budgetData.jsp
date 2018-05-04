<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Budget Data --%>
	<page:persistableData persistable="${budget}">
		<j:property name="name" value="${budget.name}"/> 
		<j:property name="notes" value="${budget.notes}"/> 
		<j:property name="description" value="${budget.description}"/>
		<j:property name="income" value="${budget.income}"/>   
		
		<j:property name="netAmount">	
			<fmt:formatNumber value="${budget.netAmount}" pattern="#,###,##0.00"/>
		</j:property> 
			
		<j:property name="viewBudgetUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.budget.BudgetActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="nameSlug" value="${budget.nameSlug}"/>
				<s:param name="budget" value="${budget.uid}"/>
			</s:url>
		</j:property>		
		
		<%-- Chart Data --%>
		<j:object name="chartData">
			
			<j:array var="category" name="rootCategories" items="${budget.itemCategories}" >
				<j:object>
					<j:property name="name" value="${category.name}"/>
					<j:property name="income" value="${category.income}"/>
					<j:property name="netItemAmount" value="${category.netItemAmount}"/>
					
					<j:array var="childCategory" name="childCategories" items="${category.childCategories}" >
						<j:object>
							<j:property name="name" value="${childCategory.name}"/>
							<j:property name="income" value="${childCategory.income}"/>
							<j:property name="netItemAmount" value="${childCategory.netItemAmount}"/>
						</j:object>
					</j:array>
					
				</j:object>
			</j:array>
		</j:object> 
	
	</page:persistableData>