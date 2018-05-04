<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Dream Data --%>	
	<page:persistableData persistable="${dream}">
		<j:property name="showTags" value="${dream.lucid || dream.dreamsignCount > 0}"/> 
		<j:property name="analysis" value="${dream.analysis}"/> 
		<j:property name="title" value="${dream.title}"/> 
		<j:property name="content" value="${dream.content}"/>
		<j:property name="favorite" value="${dream.favorite}"/> 
		<j:property name="lucid" value="${dream.lucid}"/> 
		<j:property name="dreamsignsAsString" value="${dream.dreamsignsAsString}"/> 
		<j:property name="dateTimeString" value="${dream.dateTimeString}"/>
		
		<j:property name="date"> 
			<i:date value="${dream.date}" pattern="MMM dd, yyyy - h:mm a" />  
		</j:property>
		
		<j:array var="dreamsign" name="dreamsigns" items="${dream.dreamsigns}" >
			<j:object>
				<j:property name="name" value="${dreamsign.name}"/> 
			</j:object>
		</j:array>
		
		<j:property name="dreamLink">
			<s:url beanclass="com.tracktacular.web.page.tracker.dream.DreamActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="dream" value="${dream.uid}"/>
				<s:param name="titleSlug" value="${dream.titleSlug}"/>
			</s:url>
		</j:property>
	
	</page:persistableData>