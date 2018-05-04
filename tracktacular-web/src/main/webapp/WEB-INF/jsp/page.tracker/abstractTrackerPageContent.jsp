<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	<%-- Content Heading --%>								
	<s:layout-component name="contentHeading">
		<fmt:message key="${actionBean.tracker}"/>
	</s:layout-component>
	
	
	<%-- Content Header Navigation --%>								
	<s:layout-component name="contentHeaderNavigation">	
		<ul>			
			<c:forEach var="link" items="${actionBean.contentHeaderLinks}" varStatus="status">
				<li class="hover ${link.beanClass == actionBean.beanClass ? 'active' : ''}">
					<i:link link="${link}"><span class="arrow">arrow</span></i:link>
				</li>
			</c:forEach>
			<c:if test="${actionBean.trackerPublic}">
				<li class="add-this">
					<div class="addthis_toolbox addthis_default_style">
						<a class="addthis_counter addthis_pill_style"></a>
					</div>
				</li>
			</c:if>
		</ul>					
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
		<script src="/static/tracktacular/js/page.tracker.${actionBean.tracker.name}-${actionBean.pageTrackerVersion}.js"></script>
		<script src="/static/tracktacular/js/template.tracker.${actionBean.tracker.name}-${actionBean.pageTrackerVersion}.js"></script>
		
		<c:if test="${actionBean.trackerPublic}">
			<script src="https://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-50144d8c2df7f46d"></script>
		</c:if>
		
	</s:layout-component>