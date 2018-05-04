<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Journal Dto --%>
	<page:persistableData persistable="${journalDto.journal}">
		<j:property name="entryCount" value="${journalDto.entryCount}"/>     
		<j:property name="name" value="${journalDto.journal.name}"/>
		<j:property name="notes" value="${journalDto.journal.notes}"/>
		<j:property name="nameSlug" value="${journalDto.journal.nameSlug}"/>
		<j:property name="description" value="${journalDto.journal.description}"/>
		
		<j:object name="alertRecurrence">
			<j:property name="type" value="${journalDto.journal.alertRecurrence.type}"/> 
			<j:property name="length" value="${journalDto.journal.alertRecurrence.length}"/>
		</j:object>
		
		<j:property name="viewJournalUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.journal.JournalActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="nameSlug" value="${journalDto.journal.nameSlug}"/>
				<s:param name="journal" value="${journalDto.journal.uid}"/>
			</s:url>
		</j:property> 
		
		<%-- Recent Entries --%>
		<j:array var="entry" name="recentEntries" items="${journalDto.recentEntries}" >
			<j:object>
				<j:property name="title" value="${entry.title}"/>
				
				<j:property name="date">
					<i:date value="${entry.date}" pattern="MM/dd/yyyy h:mm a" />
				</j:property>
				
			</j:object>						  
		</j:array>   

	</page:persistableData>