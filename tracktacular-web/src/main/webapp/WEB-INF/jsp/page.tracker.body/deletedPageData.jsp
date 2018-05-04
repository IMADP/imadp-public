<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>								
	<j:property name="deletedBodiesCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="body" name="deletedBodies" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="body" value="${body}" page="bodyData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />