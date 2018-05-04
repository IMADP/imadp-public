<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedRestaurantsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="restaurant" name="deletedRestaurants" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="restaurant" value="${restaurant}" page="restaurantData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />