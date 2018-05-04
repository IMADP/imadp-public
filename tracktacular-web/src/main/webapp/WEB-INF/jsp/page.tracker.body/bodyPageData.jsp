<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Body Page Data --%>			
	<j:property name="bodyCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>				
	<j:property name="showHistoryChart" value="${actionBean.itemsPageProvider.page.itemCount > 1}"/>				
	
	<j:object name="newBody">
		<j:property name="id" value="newBody"/>
		
		<j:property name="dateString" value="${actionBean.newDateString}" />
	</j:object>
	
	<j:array var="body" name="allBodies" items="${actionBean.bodies}" >
		<j:object>
			<j:property name="weight" value="${body.weight}"/> 
			<j:property name="height" value="${body.height}"/> 
			<j:property name="neck" value="${body.neck}"/> 
			<j:property name="chest" value="${body.chest}"/> 
			<j:property name="waist" value="${body.waist}"/> 
			<j:property name="hips" value="${body.hips}"/> 
			<j:property name="biceps" value="${body.biceps}"/> 
			<j:property name="forearms" value="${body.forearms}"/> 
			<j:property name="wrists" value="${body.wrists}"/> 
			<j:property name="thighs" value="${body.thighs}"/> 
			<j:property name="calves" value="${body.calves}"/> 
			<j:property name="ankles" value="${body.ankles}"/> 
			<j:property name="feet" value="${body.feet}"/> 
			<j:property name="bodyFat" value="${body.bodyFat}"/> 
			<j:property name="dateMillis" value="${body.date.millis}"/> 
		</j:object>						  
	</j:array>	
	
	<j:array var="body" name="bodies" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="body" value="${body}" page="bodyData.jsp" />
		</j:object>						  
	</j:array>
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />