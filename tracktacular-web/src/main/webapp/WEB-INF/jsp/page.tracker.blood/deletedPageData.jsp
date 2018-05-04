<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>								
	<j:property name="deletedBloodPressureCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="bloodPressure" name="deletedBloodPressures" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="bloodPressure" value="${bloodPressure}" page="bloodPressureData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />