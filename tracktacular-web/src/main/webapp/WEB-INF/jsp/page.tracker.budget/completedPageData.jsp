<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Completed Page Data --%>
	<j:property name="completedBudgetsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="budget" name="completedBudgets" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="budget" value="${budget}" page="budgetData.jsp" />
		</j:object>						  
	</j:array>	
	

	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />