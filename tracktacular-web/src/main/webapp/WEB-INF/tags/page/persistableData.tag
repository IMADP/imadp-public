<%@ include file="/WEB-INF/tags/includeTag.tag" %>

<%@ attribute name="persistable" required="true" rtexprvalue="true" type="com.imadp.dao.AbstractPersistable" %>


	<%-- Persistable Data --%>	
	<j:property name="id" value="${persistable.uid}"/>
	<j:property name="activeState" value="${persistable.activeState}"/>   
	<j:property name="archivedState" value="${persistable.archivedState}"/>   
	<j:property name="deletedState" value="${persistable.deletedState}"/>   
	
	<j:property name="createdDate"> 
		<i:time value="${persistable.timeCreated}" pattern="MMMM d, yyyy - h:mm a" />  
	</j:property>
	
	<j:property name="completedDate"> 
		<i:date value="${persistable.archivedDate}" pattern="MMMM d, yyyy - h:mm a" />
	</j:property>							
	
	<j:property name="deletedDate"> 
		<i:date value="${persistable.deletedDate}" pattern="MMMM d, yyyy - h:mm a" />
	</j:property>
	
	<jsp:doBody/> 