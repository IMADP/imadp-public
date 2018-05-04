<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Admin > Status Report"
	>

	<s:layout-component name="contentHeading">		
		Status Report	
	</s:layout-component>
	
	<s:layout-component name="contentBody">	
	
		<h2>Status Report</h2>
		
		<h3 class="center" style="margin-top:40px;">Statistics</h3>
		<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
			<table class="striped-table" style="width: 100%;margin-left: 0;">
				<tr>
					<th>Recent Logins</th>
					<th>Recent Credentials</th>
					<th>Recent Feedback</th>
					<th>Recent Errors</th>
					<th>Total Credentials</th>
					<th>Total Feedback</th>
					<th>Total Errors</th>
				</tr>
				<tr>
					<td class="center">
						${actionBean.statusReport.recentLoginCount}
				   	</td>
				   	<td class="center">
						${actionBean.statusReport.recentCredentialsCount}
				   	</td>
			   		<td class="center">
						${actionBean.statusReport.recentFeedbackCount}
				   	</td>
					<td class="center">
						${actionBean.statusReport.recentErrorLogEntryCount}
				   	</td>
					<td class="center">
						${actionBean.statusReport.credentialsCount}
				   	</td>
				    <td class="center">
						${actionBean.statusReport.feedbackCount}
				   	</td>
				   	<td class="center">
						${actionBean.statusReport.errorLogEntryCount}
				   	</td>
				</tr>
			</table>
		</article>
		
		<h3 class="center" style="margin-top:40px;">Tracker Users</h3>
		<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
			<table class="striped-table" style="width: 100%;margin-left: 0;">
				<tr>
					<th>Tracker</th>
					<th>Recent Users</th>
					<th>Total Users</th>
				</tr>

				<c:forEach var="entry" items="${actionBean.statusReport.distinctRecentUserCountMap}">
					<tr>
						<td class="center">
							${entry.key.capitalizedName}
					   	</td>
					   	<td class="center">
							${actionBean.statusReport.distinctRecentUserCountMap[entry.key]}
					   	</td>
						<td class="center">
							${actionBean.statusReport.distinctUserCountMap[entry.key]}
					   	</td>
					</tr>
				</c:forEach>
			</table>
		</article>
		
		<h3 class="center" style="margin-top:40px;">Recent Logins</h3>
		<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
			<table class="striped-table" style="width: 100%;margin-left: 0;">
				<tr>
					<th>Username</th>
					<th>Email</th>
					<th>Status</th>
					<th>Login Date</th>
					<th>Trackers</th>
				</tr>

				<c:forEach var="credentials" items="${actionBean.statusReport.recentLogin}">
					<tr>
						<td class="center">
							${credentials.username}
					   	</td>
					   	<td class="center">
							${credentials.email}
					   	</td>
						<td class="center">
							${actionBean.statusReport.subscriptionMap[credentials].subscriptionStatus}
					   	</td>
						<td class="center">
							<i:date value="${credentials.lastLoginDate}" pattern="MMM dd, yyyy - h:mm a" />
					   	</td>
						<td class="center">
							${actionBean.statusReport.trackerUserMap[credentials]}
					   	</td>
					</tr>
				</c:forEach>
			</table>
		</article>
	 	
	 	<h3 class="center" style="margin-top:40px;">Recent Joins</h3>
		<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
			<table class="striped-table" style="width: 100%;margin-left: 0;">
				<tr>
					<th>Username</th>
					<th>Email</th>
					<th>Login Date</th>
				</tr>

				<c:forEach var="credentials" items="${actionBean.statusReport.recentCredentials}">
					<tr>
						<td class="center">
							${credentials.username}
					   	</td>
					   	<td class="center">
							${credentials.email}
					   	</td>
						<td class="center">
							<i:date value="${credentials.lastLoginDate}" pattern="MMM dd, yyyy - h:mm a" />
					   	</td>
					</tr>
				</c:forEach>
			</table>
		</article>
		
		<h3 class="center" style="margin-top:40px;">Recent Feedback</h3>
		<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
			<table class="striped-table" style="width: 100%;margin-left: 0;">
				<tr>
					<th>Feedback</th>
				</tr>

				<c:forEach var="feedback" items="${actionBean.statusReport.recentFeedback}">
					<tr>
						<td class="center">
							${feedback.content}
					   	</td>
					</tr>
				</c:forEach>
			</table>
		</article>
		
		<h3 class="center" style="margin-top:40px;">Recent Error Logs</h3>
		<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
			<table class="striped-table" style="width: 100%;margin-left: 0;">
				<tr>
					<th>Error Logs</th>
				</tr>

				<c:forEach var="errorLogEntry" items="${actionBean.statusReport.recentErrorLogEntries}">
					<tr>
						<td style="text-align:left;padding:20px;">
							<strong>Logger:</strong> ${errorLogEntry.logger} <br/>
					 		<strong>Thread:</strong> ${errorLogEntry.thread} <br/>
					 		<strong>Level:</strong> ${errorLogEntry.level} <br/>
					 		<strong>Cause Class:</strong> ${errorLogEntry.throwableCauseClass} <br/>
					 		<strong>Cause Message:</strong> ${errorLogEntry.throwableCauseMessage} <br/>
					 		<strong>Cause Source:</strong> ${errorLogEntry.throwableCauseSource} <br/>
					 		<strong>Timestamp:</strong> ${errorLogEntry.timestamp} <br/>
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