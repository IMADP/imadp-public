<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Budgets Page Data --%>
	<j:array var="budget" name="activeBudgets" items="${actionBean.budgets}" >
		<j:object>
			<i:include name="budget" value="${budget}" page="budgetData.jsp" />
		</j:object>						  
	</j:array>  
	