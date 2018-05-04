<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Dreams Page Data --%>
	<c:set var="dreamCount" value="${actionBean.dreamCount}" />
	<j:property name="itemCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	<j:property name="favoriteDreamType" value="${actionBean.dreamType == 'favorite'}"/>
	<j:property name="lucidDreamType" value="${actionBean.dreamType == 'lucid'}"/>
	<j:property name="descending" value="${actionBean.sort == 'desc'}"/>   
		
	<j:property name="sortUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.dream.DreamsActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			<s:param name="dreamType" value="${actionBean.dreamType}"/>
			<s:param name="sort" value="${actionBean.sort == 'desc' ? 'asc' : 'desc'}"/>
		</s:url>
	</j:property>
	
	<j:object name="allDreams">
		<j:property name="selected" value="${'favorite' != actionBean.dreamType && 'lucid' != actionBean.dreamType}"/>
		<j:property name="count" value="${dreamCount}"/>
		<j:property name="dreamCount" value="${dreamCount}"/>
		<j:property name="favoriteDreamCount" value="${actionBean.favoriteDreamCount}"/>
		<j:property name="lucidDreamCount" value="${actionBean.lucidDreamCount}"/>
		<j:property name="id" value="all"/>
		<j:property name="urlLabel" value="All Dreams"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.dream.DreamsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sort" value="${actionBean.sort}"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="favoriteDreams">
		<j:property name="selected" value="${'favorite' == actionBean.dreamType}"/>
		<j:property name="count" value="${actionBean.favoriteDreamCount}"/>
		<j:property name="dreamCount" value="${dreamCount}"/>
		<j:property name="id" value="favorite"/>
		<j:property name="urlLabel" value="Favorite"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.dream.DreamsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="dreamType" value="favorite"/>
				<s:param name="sort" value="${actionBean.sort}"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="lucidDreams">
		<j:property name="selected" value="${'lucid' == actionBean.dreamType}"/>
		<j:property name="count" value="${actionBean.lucidDreamCount}"/>
		<j:property name="dreamCount" value="${dreamCount}"/>
		<j:property name="id" value="lucid"/>
		<j:property name="urlLabel" value="Lucid"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.dream.DreamsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="dreamType" value="lucid"/>
				<s:param name="sort" value="${actionBean.sort}"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="newDream"> 
		<j:property name="id" value="newDream"/>		
		<j:property name="dateTimeString" value="${actionBean.newDateTimeString}" />
	</j:object>
	
	<j:array var="dream" name="dreams" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="dream" value="${dream}" page="dreamData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />