<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Report --%>			
	<page:trackerReport>
		<j:property name="itemCount" value="${trackerReport.items.itemCount}"/>
		
		<j:object name="chartData">		
			<j:array var="item" name="items" items="${trackerReport.items.items}" >
				<j:object>		
					<i:out><j:property name="name" value="${item.name}"/></i:out>
					<j:property name="amount" value="${item.price.amount}" /> 
				</j:object>
			</j:array> 
		</j:object>
		
	</page:trackerReport>
