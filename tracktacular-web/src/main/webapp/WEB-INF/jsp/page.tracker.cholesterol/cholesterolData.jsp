<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Cholesterol Data --%>
	<page:persistableData persistable="${cholesterol}">
		<j:property name="notes" value="${cholesterol.notes}"/> 
		<j:property name="ldlCholesterol" value="${cholesterol.ldlCholesterol}"/> 
		<j:property name="hdlCholesterol" value="${cholesterol.hdlCholesterol}"/> 
		<j:property name="triglycerides" value="${cholesterol.triglycerides}"/> 
		<j:property name="totalCholesterol" value="${cholesterol.totalCholesterol}"/> 
		<j:property name="dateString" value="${cholesterol.dateString}"/>
		
		<j:property name="date"> 
			<i:date value="${cholesterol.date}" pattern="MMM d, yyyy" />
		</j:property>
		
	</page:persistableData>