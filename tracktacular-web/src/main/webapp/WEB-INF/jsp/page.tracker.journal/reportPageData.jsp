<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Report --%>
	<page:trackerReport>
		<j:property name="journalCount" value="${trackerReport.activeJournalsCount}"/>     
		
		<j:array var="journalDto" name="activeJournals" items="${trackerReport.activeJournalsList}" >
			<j:object>
				<i:include name="journalDto" value="${journalDto}" page="journalDtoData.jsp" />
				
				<j:object name="chartData">
					<j:property name="entryCount" value="${journalDto.entryCount}"/>     
				</j:object>
				
			</j:object>						  
		</j:array>
		   
		<j:array var="journal" name="lateJournals" items="${trackerReport.lateJournals}" >
			<j:object>
				<j:property name="name" value="${journal.name}"/>
				<j:property name="type" value="${journal.alertRecurrence.type}"/> 
				<j:property name="length" value="${journal.alertRecurrence.length}"/>
			</j:object>		  
		</j:array>   
		
	</page:trackerReport>