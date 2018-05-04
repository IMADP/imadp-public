<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>
	<j:property name="deletedItemsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="item" name="deletedItems" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="item" value="${item}" page="itemData.jsp" />
		</j:object>						  
	</j:array>	
		
		
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />