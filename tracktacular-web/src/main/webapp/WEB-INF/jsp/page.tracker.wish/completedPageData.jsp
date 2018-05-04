<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Completed Page Data --%>
	<j:property name="completedItemsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="item" name="completedItems" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="item" value="${item}" page="itemData.jsp" />
		</j:object>						  
	</j:array>	
		
		
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />