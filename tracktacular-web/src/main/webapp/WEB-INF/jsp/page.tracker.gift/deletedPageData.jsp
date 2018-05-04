<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedGiftsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="gift" name="deletedGifts" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="gift" value="${gift}" page="giftData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />