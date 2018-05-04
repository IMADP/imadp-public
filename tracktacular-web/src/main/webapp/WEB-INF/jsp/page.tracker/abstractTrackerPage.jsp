<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-definition>

	<s:layout-render 
		name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
		title="Tracktacular > ${actionBean.trackerTitle} Tracker > ${actionBean.trackerPageTitle}"
		tracker="${actionBean.tracker.name}"
		contentBodyTemplate="${actionBean.trackerContentBodyTemplate}">

		<c:if test="${!actionBean.mobile}">
			<%@ include file="/WEB-INF/jsp/page.tracker/abstractTrackerPageContent.jsp" %>
		</c:if>
		
		<c:if test="${actionBean.mobile}">
			<%@ include file="/WEB-INF/jsp/page.tracker/abstractTrackerPageContentMobile.jsp" %>
		</c:if>	
	
	</s:layout-render>

</s:layout-definition>