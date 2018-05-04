<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Journal Page Data --%>
	<j:property name="name" value="${actionBean.journal.name}"/>   
	<j:property name="descending" value="${actionBean.sort == 'desc'}"/>   
	<j:property name="entryCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>   
	<j:property name="activeState" value="${actionBean.journal.activeState}"/>   
	<j:property name="archivedState" value="${actionBean.journal.archivedState}"/>   
	<j:property name="deletedState" value="${actionBean.journal.deletedState}"/>
	   
	<j:property name="sortUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.journal.JournalActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			<s:param name="nameSlug" value="${actionBean.journal.nameSlug}"/>
			<s:param name="journal" value="${actionBean.journal.uid}"/>
			<s:param name="sort" value="${actionBean.sort == 'desc' ? 'asc' : 'desc'}"/>
		</s:url>
	</j:property>
	
	<j:property name="backUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.journal.JournalsActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		</s:url>
	</j:property>
	
	<j:object name="newEntry">
		<j:property name="id" value="newEntry"/>
		<j:property name="dateTimeString" value="${actionBean.newDateTimeString}" />   
		
		<j:object name="journal">
			<j:property name="id" value="${actionBean.journal.uid}"/>
		</j:object>
	</j:object>
	
	<j:array var="entry" name="entries" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="entry" value="${entry}" page="entryData.jsp" />
		</j:object>						  
	</j:array>   
	

	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />