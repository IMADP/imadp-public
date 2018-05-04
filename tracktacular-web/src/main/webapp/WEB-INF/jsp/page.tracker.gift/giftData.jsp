<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Gift Data --%>	
	<page:persistableData persistable="${gift}">	
		<j:property name="name" value="${gift.name}"/>
		<j:property name="sender" value="${gift.sender}"/>
		<j:property name="receiver" value="${gift.receiver}"/>
		<j:property name="received" value="${gift.received}"/>
		<j:property name="notes" value="${gift.notes}"/>
		<j:property name="occasion" value="${gift.occasion}"/>
		<j:property name="price" value="${gift.price.amount}" /> 
		<j:property name="formattedPrice" value="${gift.price.formattedAmount}" /> 
		<j:property name="dateString" value="${gift.dateString}"/>
		<j:property name="favorite" value="${gift.favorite}"/>     
		
		<j:property name="date">
			<i:date value="${gift.date}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>