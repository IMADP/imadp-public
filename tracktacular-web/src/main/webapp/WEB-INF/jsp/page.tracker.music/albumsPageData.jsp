<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Albums Page Data --%>	
	<j:property name="albumsPage" value="true"/>
	<j:property name="albumCount" value="${actionBean.albums.albumCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:property name="albumsPageUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		</s:url>
	</j:property>
	
	<j:property name="songsPageUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.music.SongsActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		</s:url>
	</j:property>
	
	<j:object name="albumsByArtist">
		<j:property name="selected" value="${'artist' == actionBean.sortProperty}"/>
		<j:property name="id" value="artist"/>
		<j:property name="color" value="#7092BE"/>
		<j:property name="urlLabel" value="By Artist"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="artist"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="albumsByTitle">
		<j:property name="selected" value="${'title' == actionBean.sortProperty}"/>
		<j:property name="id" value="title"/>
		<j:property name="color" value="#FF7F27"/>
		<j:property name="urlLabel" value="By Title"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="title"/>
			</s:url>
		</j:property>
		
	</j:object> 
	
	<j:object name="albumsByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="albumsByInstrument">
		<j:property name="selected" value="${'instrument' == actionBean.sortProperty}"/>
		<j:property name="id" value="instrument"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="By Instrument"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="instrument"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="albumsByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="albumCategory" name="albumCategories" items="${actionBean.albums.albumCategories}" >
		<j:object>			
			<j:property name="name" value="${albumCategory.name}"/>
			<j:property name="nameSlug" value="${albumCategory.nameSlug}"/>
			<j:property name="albumCount" value="${albumCategory.itemCount}"/>
	
			<j:array var="album" name="albums" items="${albumCategory.items}" >
				<j:object>		
					<i:include name="album" value="${album}" page="albumData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>