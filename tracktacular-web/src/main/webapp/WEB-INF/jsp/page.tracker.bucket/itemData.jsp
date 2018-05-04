<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Item Data --%>
	<page:persistableData persistable="${item}">
		<j:property name="name" value="${item.name}"/> 
		<j:property name="notes" value="${item.notes}"/> 
		<j:property name="description" value="${item.description}"/>
		<j:property name="completed" value="${item.completed}"/>
		<j:property name="completedDateString" value="${item.completedDateString}" />     
		
		<j:property name="completedDate">
			<i:date value="${item.completedDate}" pattern="MM/dd/yyyy" />
		</j:property>		
	
	</page:persistableData>