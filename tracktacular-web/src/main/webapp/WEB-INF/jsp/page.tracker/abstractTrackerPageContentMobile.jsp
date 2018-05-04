<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Content Heading --%>								
	<s:layout-component name="contentHeading">
		<fmt:message key="${actionBean.tracker}"/>
	</s:layout-component>
	
	
	<%-- JS Resources --%>								
	<s:layout-component name="jsResources">
		<script src="/static/tracktacular/js/template.tracker.${actionBean.tracker.name}.mobile-${actionBean.pageTrackerVersion}.js"></script>
	</s:layout-component>