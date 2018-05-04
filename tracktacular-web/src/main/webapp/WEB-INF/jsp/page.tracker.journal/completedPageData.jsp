<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Completed Page Data --%>
	<j:property name="completedJournalsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="journalDto" name="completedJournals" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="journalDto" value="${journalDto}" page="journalDtoData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />