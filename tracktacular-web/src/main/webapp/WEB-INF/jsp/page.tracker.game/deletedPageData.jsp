<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedGamesCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="game" name="deletedGames" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="game" value="${game}" page="gameData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />