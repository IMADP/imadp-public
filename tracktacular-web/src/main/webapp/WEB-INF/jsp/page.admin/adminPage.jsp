<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Admin"
	>

	<s:layout-component name="contentHeading">		
		Admin	
	</s:layout-component>
	
	<s:layout-component name="contentBody">	
	
		<h2>Admin Controls</h2>
		
		<section style="margin: 40px 60px 0 60px">
			
			<s:link beanclass="com.tracktacular.web.page.admin.AdminActionBean" event="clearAllCaches">
				Clear All Caches
			</s:link>	
			
			<br/>
			
			<s:link beanclass="com.tracktacular.web.page.admin.AdminActionBean" event="insertTrackerDemoData">
				Insert All Demo Data
			</s:link>	
			
			<br/>
			
			<s:link beanclass="com.tracktacular.web.page.admin.AdminActionBean" event="generateAndSendStatusReport">
				Generate And Send Status Report
			</s:link>	
					
		</section>
	 	
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
	 	
 	 </s:layout-component>
	
</s:layout-render>