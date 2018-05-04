<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report --%>		
	<page:trackerReport>	
		<j:property name="dreamCount" value="${trackerReport.dreamCount}"/>
		 		
		<j:object name="chartData">
			<j:array name="dreamsMonthList" items="${trackerReport.dreamsMonthList}"/> 		
			<j:array name="allDreamsByMonthList" items="${trackerReport.allDreamsByMonthList}"/> 		
			<j:array name="favoriteDreamsByMonthList" items="${trackerReport.favoriteDreamsByMonthList}"/> 		
			<j:array name="lucidDreamsByMonthList" items="${trackerReport.lucidDreamsByMonthList}"/> 		
		</j:object>
						
	</page:trackerReport>
	