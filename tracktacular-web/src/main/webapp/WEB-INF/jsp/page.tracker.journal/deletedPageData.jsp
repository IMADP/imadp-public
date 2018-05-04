<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>
	<j:property name="deletedJournalsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="journalDto" name="deletedJournals" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="journalDto" value="${journalDto}" page="journalDtoData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />