<%@ include file="/WEB-INF/tags/includeTag.tag" %>

<%@ attribute name="page"  required="true" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>

   	<s:link beanclass="com.tracktacular.web.page.tracker.${actionBean.tracker.name}.${page}ActionBean">
		<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
		${label}
	</s:link>