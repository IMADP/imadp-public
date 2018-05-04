<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Cholesterol Page Data --%>			
	<j:object name="newCholesterol">
		<j:property name="id" value="newCholesterol"/>
		<j:property name="dateString" value="${actionBean.newDateString}" />
	</j:object>
	
	<j:property name="cholesterolCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>				
	<j:property name="showHistoryChart" value="${actionBean.itemsPageProvider.page.itemCount > 1}"/>				
		
	<j:array var="cholesterol" name="allCholesterols" items="${actionBean.cholesterols}" >
		<j:object>
			<j:property name="ldlCholesterol" value="${cholesterol.ldlCholesterol}"/> 
			<j:property name="hdlCholesterol" value="${cholesterol.hdlCholesterol}"/> 
			<j:property name="triglycerides" value="${cholesterol.triglycerides}"/> 
			<j:property name="totalCholesterol" value="${cholesterol.totalCholesterol}"/> 
			<j:property name="dateMillis" value="${cholesterol.date.millis}"/> 
		</j:object>						  
    </j:array>
	
	<j:array var="cholesterol" name="cholesterols" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="cholesterol" value="${cholesterol}" page="cholesterolData.jsp" />
		</j:object>						  
    </j:array>
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />