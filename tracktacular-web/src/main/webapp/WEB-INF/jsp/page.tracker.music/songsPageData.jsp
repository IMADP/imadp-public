<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Songs Page Data --%>	
	<j:property name="songCount" value="${actionBean.songs.songCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:object name="songsByArtist">
		<j:property name="selected" value="${'artist' == actionBean.sortProperty}"/>
		<j:property name="id" value="artist"/>
		<j:property name="color" value="#7092BE"/>
		<j:property name="urlLabel" value="By Artist"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.SongsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="artist"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="songsByTitle">
		<j:property name="selected" value="${'title' == actionBean.sortProperty}"/>
		<j:property name="id" value="title"/>
		<j:property name="color" value="#FF7F27"/>
		<j:property name="urlLabel" value="By Title"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.SongsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="title"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	
	
	<j:object name="songsByAlbum">
		<j:property name="selected" value="${'album' == actionBean.sortProperty}"/>
		<j:property name="id" value="album"/>
		<j:property name="color" value="#80699B"/>
		<j:property name="urlLabel" value="By Album"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.SongsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="album"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="songsByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.SongsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="songsByInstrument">
		<j:property name="selected" value="${'instrument' == actionBean.sortProperty}"/>
		<j:property name="id" value="instrument"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="By Instrument"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.SongsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="instrument"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="songsByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.music.SongsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="songCategory" name="songCategories" items="${actionBean.songs.songCategories}" >
		<j:object>			
			<j:property name="name" value="${songCategory.name}"/>
			<j:property name="nameSlug" value="${songCategory.nameSlug}"/>
			<j:property name="songCount" value="${songCategory.itemCount}"/>
	
			<j:array var="song" name="songs" items="${songCategory.items}" >
				<j:object>		
					<i:include name="song" value="${song}" page="songData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>