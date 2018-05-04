<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report Segment --%>		
	<page:trackerReport>	
		<j:property name="movieCount" value="${trackerReport.movieCount}"/>	
		<j:property name="unratedMovieCount" value="${trackerReport.unratedMovieCount}"/>	
		
		<j:array var="movie" name="unratedMovies" items="${trackerReport.unratedMovies}" >
			<j:object>
				<c:set var="movie" value="${movie}" scope="request" />
				<jsp:include page="movieData.jsp" />
			</j:object>
		</j:array>
		
		<j:array var="movie" name="targetDateMovies" items="${trackerReport.targetDateMovies}" >
			<j:object>
				<c:set var="movie" value="${movie}" scope="request" />
				<jsp:include page="movieData.jsp" />
			</j:object>
		</j:array>	
		
		<j:object name="moviesByTitle">
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
						
	</page:trackerReport>
	