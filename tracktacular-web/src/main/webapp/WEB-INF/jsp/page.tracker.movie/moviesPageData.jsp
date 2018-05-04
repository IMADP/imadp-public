<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Movies Page Data --%>	
	<j:property name="movieCount" value="${actionBean.movies.movieCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:object name="moviesByTitle">
		<j:property name="selected" value="${'title' == actionBean.sortProperty}"/>
		<j:property name="id" value="title"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="By Title"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.movie.MoviesActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="title"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="moviesByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.movie.MoviesActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="moviesByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.movie.MoviesActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="movieCategory" name="movieCategories" items="${actionBean.movies.movieCategories}" >
		<j:object>			
			<j:property name="name" value="${movieCategory.name}"/>
			<j:property name="nameSlug" value="${movieCategory.nameSlug}"/>
			<j:property name="movieCount" value="${movieCategory.itemCount}"/>
	
			<j:array var="movie" name="movies" items="${movieCategory.items}" >
				<j:object>		
					<i:include name="movie" value="${movie}" page="movieData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>