<%@ include file="/WEB-INF/tags/includeTag.tag" %>

	<j:object name="trackerReport">
		<j:property name="enabled" value="${trackerReport.enabled}"/> 
		<j:property name="trackerName" value="${trackerReport.tracker.name}"/>
		<j:property name="alerts" value="${trackerReport.alerts}"/> 
			
		<j:property name="trackerTitle">
			<fmt:message key="${trackerReport.tracker}"/>
		</j:property>
		
		<j:array var="trackerPage" name="trackerPages" items="${actionBean.trackerPageMap[trackerReport.tracker]}" >
			<j:object>
				<j:property name="name">
					<fmt:message key="${trackerReport.tracker.capitalizedName}TrackerPage.${trackerPage}"/>
				</j:property> 
				<j:property name="value">
					<s:url beanclass="${trackerPage.actionBean}" >
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					</s:url>
				</j:property> 
			</j:object>			 			  
		</j:array>	
		
		<c:if test="${trackerReport.enabled}">
			<jsp:doBody/>
		</c:if>
	</j:object>