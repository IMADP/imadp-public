<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Blood Pressure Page Data --%>			
	<j:property name="bloodPressureCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>				
	<j:property name="showHistoryChart" value="${actionBean.itemsPageProvider.page.itemCount > 1}"/>				
	
	<j:object name="newBloodPressure">
		<j:property name="id" value="newBloodPressure"/>
		<j:property name="dateString" value="${actionBean.newDateString}" />
	</j:object>
	
	<j:array var="bloodPressure" name="allBloodPressures" items="${actionBean.bloodPressures}" >
		<j:object>
			<j:property name="systolic" value="${bloodPressure.systolic}"/> 
			<j:property name="diastolic" value="${bloodPressure.diastolic}"/> 
			<j:property name="dateMillis" value="${bloodPressure.date.millis}"/> 
		</j:object>						  
    </j:array>
    
    <j:array var="bloodPressure" name="bloodPressures" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="bloodPressure" value="${bloodPressure}" page="bloodPressureData.jsp" />
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />