<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedAlbumsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="album" name="deletedAlbums" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="album" value="${album}" page="albumData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />