<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>								
	<j:property name="deletedCholesterolsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="cholesterol" name="deletedCholesterols" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="cholesterol" value="${cholesterol}" page="cholesterolData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />