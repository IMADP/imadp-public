<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Task Data --%>
	<page:persistableData persistable="${task}">
		<j:property name="priority" value="${task.priority}"/> 
		<j:property name="name" value="${task.name}"/> 
		<j:property name="late" value="${task.late}"/> 
		<j:property name="notes" value="${task.notes}"/> 
		<j:property name="categoryPath" value="${task.categoryPath}"/>
		<j:property name="targetDateString" value="${task.targetDateString}"/> 
		
		<j:property name="targetDate"> 
			<i:date value="${task.targetDate}" pattern="MMM d, yyyy" />
		</j:property>
		
	</page:persistableData>