<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Admin > Metrics"
	>

	<s:layout-component name="contentHeading">		
		Metrics	
	</s:layout-component>
	
	<s:layout-component name="contentBody">	
	
		<h3 class="center" style="margin-top:40px;">Recent Metrics</h3>
		<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
			<table class="striped-table" style="width: 100%;margin-left: 0;">
				<tr>
					<th>Name</th>
					<th>Count</th>
					<th>Mean</th>
					<th>Min</th>
					<th>Max</th>
				</tr>
				
				<c:forEach var="metricsSummary" items="${actionBean.metricsSummaries.summaries}">
					<tr>
						<td class="center" style="font-size: 12px;">
							${metricsSummary.name}
					   	</td>
					   	<td class="center">
							${metricsSummary.metricCount}
					   	</td>
					   	<td class="center">
							${metricsSummary.meanDuration}
					   	</td>
					   	<td class="center">
							${metricsSummary.minDuration}
					   	</td>
						<td class="center">
							${metricsSummary.maxDuration}
					   	</td>
					</tr>
				</c:forEach>
				
			</table>
		</article>
	 	
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