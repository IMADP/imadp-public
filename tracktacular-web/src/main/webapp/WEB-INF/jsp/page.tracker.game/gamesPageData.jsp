<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Games Page Data --%>	
	<j:property name="gameCount" value="${actionBean.games.gameCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:object name="gamesByTitle">
		<j:property name="selected" value="${'title' == actionBean.sortProperty}"/>
		<j:property name="id" value="title"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="By Title"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="title"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="gamesByPlatform">
		<j:property name="selected" value="${'platform' == actionBean.sortProperty}"/>
		<j:property name="id" value="platform"/>
		<j:property name="color" value="#7092BE"/>
		<j:property name="urlLabel" value="By Platform"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="platform"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="gamesByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="gamesByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="gameCategory" name="gameCategories" items="${actionBean.games.gameCategories}" >
		<j:object>			
			<j:property name="name" value="${gameCategory.name}"/>
			<j:property name="nameSlug" value="${gameCategory.nameSlug}"/>
			<j:property name="gameCount" value="${gameCategory.itemCount}"/>
	
			<j:array var="game" name="games" items="${gameCategory.items}" >
				<j:object>		
					<i:include name="game" value="${game}" page="gameData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>