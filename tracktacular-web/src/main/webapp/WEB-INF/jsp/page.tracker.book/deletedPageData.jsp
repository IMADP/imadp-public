<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedBooksCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="book" name="deletedBooks" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="book" value="${book}" page="bookData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />