<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report Segment --%>		
	<page:trackerReport>	
		<j:property name="albumCount" value="${trackerReport.albumCount}"/>	
		<j:property name="unratedAlbumCount" value="${trackerReport.unratedAlbumCount}"/>	
		
		<j:array var="album" name="unratedAlbums" items="${trackerReport.unratedAlbums}" >
			<j:object>
				<c:set var="album" value="${album}" scope="request" />
				<jsp:include page="albumData.jsp" />
			</j:object>
		</j:array>
		
		<j:array var="song" name="targetDateSongs" items="${trackerReport.targetDateSongs}" >
			<j:object>
				<c:set var="song" value="${song}" scope="request" />
				<jsp:include page="songData.jsp" />
			</j:object>
		</j:array>	
		
		<j:array var="album" name="targetDateAlbums" items="${trackerReport.targetDateAlbums}" >
			<j:object>
				<c:set var="album" value="${album}" scope="request" />
				<jsp:include page="albumData.jsp" />
			</j:object>
		</j:array>	
		
		<j:object name="albumsByArtist">
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
		
		<j:object name="albumsByRating">
			<j:property name="selected" value="false"/>
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
						
	</page:trackerReport>
	