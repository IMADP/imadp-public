<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedShowsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="show" name="deletedShows" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="show" value="${show}" page="showData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />