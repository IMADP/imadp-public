<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedBirthdaysCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="birthday" name="deletedBirthdays" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="birthday" value="${birthday}" page="birthdayData.jsp" />
		</j:object>
	</j:array>	


	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />