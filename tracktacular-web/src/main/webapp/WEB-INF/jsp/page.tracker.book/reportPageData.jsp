<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report Segment --%>		
	<page:trackerReport>	
		<j:property name="bookCount" value="${trackerReport.bookCount}"/>		
		<j:property name="unratedBookCount" value="${trackerReport.unratedBookCount}"/>	
		
		<j:array var="book" name="unratedBooks" items="${trackerReport.unratedBooks}" >
			<j:object>
				<c:set var="book" value="${book}" scope="request" />
				<jsp:include page="bookData.jsp" />
			</j:object>
		</j:array>	
		
		<j:array var="book" name="targetDateBooks" items="${trackerReport.targetDateBooks}" >
			<j:object>
				<c:set var="book" value="${book}" scope="request" />
				<jsp:include page="bookData.jsp" />
			</j:object>
		</j:array>	
		
		<j:array var="chapter" name="targetDateChapters" items="${trackerReport.targetDateChapters}" >
			<j:object>
				<c:set var="chapter" value="${chapter}" scope="request" />
				<jsp:include page="chapterData.jsp" />
			</j:object>
		</j:array>	
		
		<j:object name="booksByAuthor">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="author"/>
			<j:property name="color" value="#7092BE"/>
			<j:property name="urlLabel" value="By Author"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="author"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="booksByTitle">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="title"/>
			<j:property name="color" value="#ff4540"/>
			<j:property name="urlLabel" value="By Title"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="title"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="booksByTag">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="tag"/>
			<j:property name="color" value="#589800"/>
			<j:property name="urlLabel" value="By Tag"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="tag"/>
				</s:url>
			</j:property>
			
		</j:object>
		
		<j:object name="booksByRating">
			<j:property name="selected" value="false"/>
			<j:property name="id" value="rating"/>
			<j:property name="color" value="#ffd300"/>
			<j:property name="urlLabel" value="By Rating"/>
			
			<j:property name="url">
				<s:url beanclass="com.tracktacular.web.page.tracker.book.BooksActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="sortProperty" value="rating"/>
				</s:url>
			</j:property>
			
		</j:object>
						
	</page:trackerReport>
	