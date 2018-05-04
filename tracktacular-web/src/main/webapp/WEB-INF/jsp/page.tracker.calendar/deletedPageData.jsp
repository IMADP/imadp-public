<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Deleted Page Data --%>
	<j:property name="deletedCalendarEntryCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="calendarEntry" name="deletedCalendarEntries" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="calendarEntry" value="${calendarEntry}" page="calendarEntryData.jsp" />						
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />