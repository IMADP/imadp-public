<%@ include file="/WEB-INF/tags/includeTag.tag" %>

<%@ attribute name="tracker" required="false" rtexprvalue="true" type="com.tracktacular.service.tracker.Tracker" %>

   	<c:set var="link" value="${actionBean.pageLinks[tracker]}" />
    	
   	<c:if test="${!link.disabled}">
        <li class="link-item ${link.active ? 'active' : ''}">
	   		<i:link link="${link}">
		   		<span class="link-item-icon icon-s s-${tracker.name}"></span>
		   		<span class="link-item-label"><fmt:message key="${tracker}"/></span> 
		   		<c:if test="${link.locked}">
					<span class="icon-s s-lock"></span>
				</c:if>
	   		</i:link>
   		</li>  
	</c:if>