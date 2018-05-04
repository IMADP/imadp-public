<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Blood Pressure Data --%>
	<page:persistableData persistable="${bloodPressure}">
		<j:property name="notes" value="${bloodPressure.notes}"/> 
		<j:property name="systolic" value="${bloodPressure.systolic}"/> 
		<j:property name="diastolic" value="${bloodPressure.diastolic}"/> 
		<j:property name="dateString" value="${bloodPressure.dateString}"/>
		
		<j:property name="date"> 
			<i:date value="${bloodPressure.date}" pattern="MMM d, yyyy" />
		</j:property>
		
	</page:persistableData>