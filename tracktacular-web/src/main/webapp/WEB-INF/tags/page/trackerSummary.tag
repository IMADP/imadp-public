<%@ include file="/WEB-INF/tags/includeTag.tag" %>

<%@ attribute name="tracker" required="true" rtexprvalue="true" type="com.tracktacular.service.tracker.Tracker" %>

	<c:set var="link" value="${actionBean.pageLinks[tracker]}" />
             	
    <li class="cf">
		<div class="left tracker-icon">
			<s:link beanclass="${link.beanClass}" class="${cssClass}" title="${title}" >   
	    		<c:forEach var="parameter" items="${link.parameters}">
					<s:param name="${parameter.key}" value="${parameter.value}"/>
				</c:forEach>
	    		<span class="icon-l l-${tracker.name}"></span>	
	    	</s:link>	
		</div>
		<div class="left tracker-summary">
			<s:link beanclass="${link.beanClass}" class="${cssClass}" title="${title}" >   
	    		<c:forEach var="parameter" items="${link.parameters}">
					<s:param name="${parameter.key}" value="${parameter.value}"/>
				</c:forEach>
	    		<div><fmt:message key="${tracker}"/></div>	
	    	</s:link>	
			<jsp:doBody/>
		</div>
	</li>