<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Entry Data --%>	
	<page:persistableData persistable="${entry}">
		<j:property name="notes" value="${entry.notes}"/>
		<j:property name="title" value="${entry.title}"/>
		<j:property name="content" value="${entry.content}"/>
		<j:property name="dateTimeString" value="${entry.dateTimeString}"/>
		
		<j:object name="journal">
			<j:property name="id" value="${entry.journal.uid}"/>
		</j:object>   
		
		<j:property name="date">
			<i:date value="${entry.date}" pattern="MMM dd, yyyy - h:mm a" />
		</j:property>
		
		<j:property name="entryLink">
			<s:url beanclass="com.tracktacular.web.page.tracker.journal.EntryActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="entry" value="${entry.uid}"/>
				<s:param name="titleSlug" value="${entry.titleSlug}"/>
			</s:url>
		</j:property>
		
		<j:property name="journalLink">
			<s:url beanclass="com.tracktacular.web.page.tracker.journal.JournalActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="nameSlug" value="${entry.journal.nameSlug}"/>
				<s:param name="journal" value="${entry.journal.uid}"/>
			</s:url>
		</j:property>
	
	</page:persistableData>