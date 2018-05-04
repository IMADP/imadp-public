<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Item Data --%>
	<page:persistableData persistable="${item}">
		<j:property name="name" value="${item.name}"/> 
		<j:property name="notes" value="${item.notes}"/> 
		<j:property name="description" value="${item.description}"/>
		<j:property name="url" value="${item.url}"/>
		<j:property name="price" value="${item.price.amount}" /> 
		<j:property name="formattedPrice" value="${item.price.formattedAmount}" /> 
	</page:persistableData>