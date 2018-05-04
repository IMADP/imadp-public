<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Shows Page Data --%>	
	<j:property name="showCount" value="${actionBean.shows.showCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:object name="showsByTitle">
		<j:property name="selected" value="${'title' == actionBean.sortProperty}"/>
		<j:property name="id" value="title"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="By Title"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.tv.ShowsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="title"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="showsByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.tv.ShowsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="showsByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.tv.ShowsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="showCategory" name="showCategories" items="${actionBean.shows.showCategories}" >
		<j:object>			
			<j:property name="name" value="${showCategory.name}"/>
			<j:property name="nameSlug" value="${showCategory.nameSlug}"/>
			<j:property name="showCount" value="${showCategory.itemCount}"/>
	
			<j:array var="show" name="shows" items="${showCategory.items}" >
				<j:object>		
					<i:include name="show" value="${show}" page="showData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>