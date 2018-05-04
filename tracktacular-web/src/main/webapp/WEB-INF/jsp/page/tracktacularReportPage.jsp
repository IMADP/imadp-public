<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Tracktacular Report"
	contentBodyTemplate="tracktacularReportPageContent"
	>

	<%-- Content Heading --%>							
	<s:layout-component name="contentHeading">
		<strong>Report</strong>
	</s:layout-component>
	
	<%-- Content Data --%>	
	<s:layout-component name="contentData">
		<j:property name="trackerReportSimpleView" value="true"/>
		<j:property name="trackersFound" value="${actionBean.tracktacularReport.trackersFound}"/>
		<j:property name="alerts" value="${actionBean.tracktacularReport.alerts}"/>
		<j:property name="anyAlerts" value="${actionBean.tracktacularReport.alerts}"/>
		<j:property name="alertCount" value="${actionBean.tracktacularReport.alertCount}"/>
		<j:property name="trackersWithAlerts" value="${actionBean.tracktacularReport.trackersWithAlerts}"/>
		
		<j:property name="date">
			<i:date value="${actionBean.tracktacularReport.date}" pattern="MMMM d, yyyy" />
		</j:property>
		
		<j:array var="trackerReport" name="trackerReports" items="${actionBean.tracktacularReport.trackerReports}" >
			<j:object>
				<c:set var="trackerReport" value="${trackerReport}" scope="request"/>			
				<jsp:include page="/WEB-INF/jsp/page.tracker.${trackerReport.tracker.name}/reportPageData.jsp" />
			</j:object>						  
		</j:array>
		 
    </s:layout-component>	
  
 	<%-- JS Resources --%>	
	<s:layout-component name="jsResources">
		
		<%-- JavaScript CDN Resources --%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>			
		
		<%-- JavaScript Fallback Resources --%>
		<script>//<![CDATA[
           	window.jQuery||document.write('<script src="/static/tracktacular/js/lib/jquery-1.7.1.min.js"><\/script>')//]]>
		</script>					
		<script>//<![CDATA[
           	window.jQuery.ui||document.write('<script src="/static/tracktacular/js/lib/jquery.ui-1.8.16.min.js"><\/script>')//]]>
           </script>			
		
		<%-- JavaScript Resources --%>
		<script src="/static/tracktacular/js/page-${actionBean.pageVersion}.js"></script>			 	
	 	<script src="/static/tracktacular/js/template-${actionBean.pageVersion}.js"></script>
	 	
 	 	<c:forEach var="entry" items="${actionBean.trackersWithReports}">
 	 		<script src="/static/tracktacular/js/page.tracker.${entry.key.name}-${entry.value}.js"></script>
			<script src="/static/tracktacular/js/template.tracker.${entry.key.name}-${entry.value}.js"></script>
 	 	</c:forEach>
	 </s:layout-component>
				
</s:layout-render>