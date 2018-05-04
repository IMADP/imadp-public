<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Entries Page Data--%>
	<j:property name="calendarEntryCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="calendarEntry" name="calendarEntries" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="calendarEntry" value="${calendarEntry}" page="calendarEntryData.jsp" />	
		</j:object>						  
	</j:array>	
	
	<j:object name="newEntry">
		<j:property name="id" value="newEntry"/>
		<j:property name="startDateString" value="${actionBean.newDateString}" /> 
	</j:object>   
	

	<%-- Recurrence Types --%>
	<page:recurrenceTypes/>
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />