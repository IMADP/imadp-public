<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Report --%>
	<page:trackerReport>
		<j:object name="bloodPressure">
			<i:include name="bloodPressure" value="${trackerReport.bloodPressure}" page="bloodPressureData.jsp" />
		</j:object>
		
		<j:property name="recommendedTestDate"> 
			<i:date value="${trackerReport.recommendedTestDate}" pattern="MMM d, yyyy" />
		</j:property>
	</page:trackerReport>