<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Wish List Page Data --%>
	<j:property name="showChart" value="${actionBean.items.itemCount > 0}"/>
	<j:property name="itemsSortable" value="${actionBean.items.itemCount > 1}"/>
	<j:property name="itemCount" value="${actionBean.items.itemCount}"/>
		
	<j:object name="chartData">		
		<j:array var="item" name="items" items="${actionBean.items.items}" >
			<j:object>		
				<i:out><j:property name="name" value="${item.name}"/></i:out>
				<j:property name="amount" value="${item.price.amount}" /> 
			</j:object>
		</j:array> 
	</j:object>
	
	<j:array name="items" >
		<c:forEach var="item" varStatus="status" items="${actionBean.items.items}">
			<j:object>		
				<i:include name="item" value="${item}" page="itemData.jsp" />
				<j:property name="index" value="${status.index + 1}"/>
			</j:object>
		</c:forEach>		
	</j:array> 