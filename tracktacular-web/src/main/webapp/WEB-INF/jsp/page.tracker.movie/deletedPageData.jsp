<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedMoviesCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="movie" name="deletedMovies" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="movie" value="${movie}" page="movieData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />