<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Budget Page Data --%>
	<i:include name="budget" value="${actionBean.budget}" page="budgetData.jsp" />
	<j:property name="showItems" value="true"/>
	
	<j:property name="netIncomeAmount">
		<fmt:formatNumber value="${actionBean.budget.netIncomeAmount}" pattern="#,###,##0.00"/>
	</j:property>   
	
	<j:property name="netExpenseAmount">	
		<fmt:formatNumber value="${actionBean.budget.netExpenseAmount}" pattern="#,###,##0.00"/>
	</j:property>   
	
	<j:object name="newItemCategory">
		<j:property name="id" value="newItemCategory"/>
		<j:property name="budgetId" value="${actionBean.budget.uid}"/>
	</j:object>
	
	<j:property name="backUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.budget.BudgetsActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		</s:url>
	</j:property>
	
	
	<%-- Item Categories --%>
	<c:set var="categories" value="${actionBean.budget.itemCategories}" scope="request" />
	<c:set var="name" value="categories" scope="request" />
	<jsp:include page="budgetPageDataItemCategory.jsp" />
	
	
	<%-- All Categories --%>
	<c:set var="categories" value="${actionBean.budget.allCategories}" scope="request" />
	<c:set var="name" value="allCategories" scope="request" />
	<jsp:include page="budgetPageDataItemCategory.jsp" />
