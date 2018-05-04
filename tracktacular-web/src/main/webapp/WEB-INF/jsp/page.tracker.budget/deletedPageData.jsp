<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>
	<j:property name="deletedBudgetsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="budget" name="deletedBudgets" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="budget" value="${budget}" page="budgetData.jsp" />
		</j:object>						  
	</j:array>	
		
		
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />