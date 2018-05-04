<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	<s:layout-render name="/WEB-INF/jsp/page.tracker/abstractTrackerPage.jsp">	
	
		<%-- Content Data --%>	
		<s:layout-component name="contentData">
		
			<%-- Tracker Report Data --%>
			<c:set var="trackerReport" value="${actionBean.trackerReport}" scope="request"/>			
			<jsp:include page="/WEB-INF/jsp/page.tracker.${actionBean.tracker.name}/reportPageData.jsp" />
			
		</s:layout-component>	
		
	</s:layout-render>
	
	
			
			
			