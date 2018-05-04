<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report --%>		
	<page:trackerReport>	
		<j:property name="birthdayCount" value="${trackerReport.birthdayCount}"/>	
		<j:property name="upcomingBirthdayCount" value="${trackerReport.upcomingBirthdayCount}"/>	
		
		<j:property name="currentMonthName">
			<i:date value="${trackerReport.currentMonth}" pattern="MMMM" />
		</j:property>
		
		<j:property name="nextMonthName">
			<i:date value="${trackerReport.nextMonth}" pattern="MMMM" />
		</j:property>
		
		<j:array var="birthday" name="alertedBirthdays" items="${trackerReport.alertedBirthdays}" >
			<j:object>
				<c:set var="birthday" value="${birthday}" scope="request" />
				<jsp:include page="birthdayData.jsp" />
			</j:object>
		</j:array>
		
		<j:array var="birthday" name="upcomingBirthdays" items="${trackerReport.upcomingBirthdays}" >
			<j:object>
				<c:set var="birthday" value="${birthday}" scope="request" />
				<jsp:include page="birthdayData.jsp" />
			</j:object>
		</j:array>
		
		<j:array var="birthday" name="currentBirthdays" items="${trackerReport.currentBirthdays}" >
			<j:object>
				<c:set var="birthday" value="${birthday}" scope="request" />
				<jsp:include page="birthdayData.jsp" />
			</j:object>
		</j:array>
						
	</page:trackerReport>
	