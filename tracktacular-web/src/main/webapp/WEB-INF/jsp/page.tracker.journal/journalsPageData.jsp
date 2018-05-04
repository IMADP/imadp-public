<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Journals Page Data --%>
	<j:property name="entryCount" value="${actionBean.journalsEntryCount}"/>   
	
	<%-- Recurrence Types --%>
	<page:recurrenceTypes/>
	
	<%-- Journal Dtos --%>
	<j:array var="journalDto" name="activeJournals" items="${actionBean.journals}" >
		<j:object>
			<i:include name="journalDto" value="${journalDto}" page="journalDtoData.jsp" />
			
			<j:object name="chartData">
				<j:property name="entryCount" value="${journalDto.entryCount}"/>     
			</j:object>
			
		</j:object>						  
	</j:array>