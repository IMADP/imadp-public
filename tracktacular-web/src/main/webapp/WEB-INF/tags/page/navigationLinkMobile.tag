<%@ include file="/WEB-INF/tags/includeTag.tag" %>

<%@ attribute name="tracker" required="false" rtexprvalue="true" type="com.tracktacular.service.tracker.Tracker" %>

   	<c:set var="link" value="${actionBean.pageLinks[tracker]}" />
    	
   	<j:object>
		<j:property name="tracker" value="${tracker.name}"/>
		<j:property name="trackerScriptUrl" value="/static/tracktacular/js/template.tracker.${tracker.name}.mobile-${actionBean.tracktacularVersion.getPageTrackerVersion(tracker)}.js"/>
		
		<j:property name="trackerLabel">
			<fmt:message key="${tracker}"/>
		</j:property>
		
		<j:property name="trackerUrl">
			<s:url beanclass="${link.beanClass}">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			</s:url>
		</j:property>
		
	</j:object>