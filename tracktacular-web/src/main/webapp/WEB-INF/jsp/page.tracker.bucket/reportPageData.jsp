<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report --%>		
	<page:trackerReport>	
		<j:property name="itemCount" value="${trackerReport.items.itemCount}"/>
		<j:property name="progress" value="${trackerReport.items.progress}"/>
		<j:property name="finishedItemCount" value="${trackerReport.items.finishedItemCount}"/>
	</page:trackerReport>
	