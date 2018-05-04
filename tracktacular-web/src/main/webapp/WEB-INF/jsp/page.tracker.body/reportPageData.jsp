<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Report --%>
	<page:trackerReport>
		<j:object name="body">
			<i:include name="body" value="${trackerReport.body}" page="bodyData.jsp" />
		</j:object>
		
		<j:property name="recommendedTestDate"> 
			<i:date value="${trackerReport.recommendedTestDate}" pattern="MMM d, yyyy" />
		</j:property>
	</page:trackerReport>