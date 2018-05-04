<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Report --%>
	<page:trackerReport>
		<j:object name="cholesterol">
			<i:include name="cholesterol" value="${trackerReport.cholesterol}" page="cholesterolData.jsp" />
		</j:object>
		
		<j:property name="recommendedTestDate"> 
			<i:date value="${trackerReport.recommendedTestDate}" pattern="MMM d, yyyy" />
		</j:property>
	</page:trackerReport>
