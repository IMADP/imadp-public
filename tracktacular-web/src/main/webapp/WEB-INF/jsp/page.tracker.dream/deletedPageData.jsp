<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>	
	<j:property name="deletedDreamsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="dream" name="deletedDreams" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="dream" value="${dream}" page="dreamData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />