<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Dreamsigns Page Data --%>
	<j:property name="dreamsign" value="${actionBean.dreamsign}"/>
	<j:property name="dreamsignSelected" value="${actionBean.dreamsignSelected}"/>
		
	<j:object name="dreamsignTagCloud">
		<j:property name="tagCloudItemCount" value="${actionBean.tagCloud.tagCloudItemCount}"/>   
		
		<j:array var="tagCloudItem" name="tagCloudItems" items="${actionBean.tagCloud.tagCloudItems}" >
			<j:object>
				<j:property name="nameSlug" value="${tagCloudItem.nameSlug}"/>   
				<j:property name="frequency" value="${tagCloudItem.frequency}"/>   
				<j:property name="weight" value="${tagCloudItem.weight}"/> 
				<j:property name="weightInPx" value="${tagCloudItem.weight * 7}"/> 
				
				<j:property name="selectUrl">
					<s:url beanclass="com.tracktacular.web.page.tracker.dream.DreamsignsActionBean">
						<s:param name="dreamsign" value="${tagCloudItem.nameSlug}"/>
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					</s:url>
				</j:property>
				
			</j:object>
		</j:array>	
		
	</j:object>
	
	<c:if test="${actionBean.dreamsignSelected}">
		<j:property name="dreamsignCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
		
		<j:array var="dreamsign" name="dreamsigns" items="${actionBean.itemsPageProvider.page.items}" >
			<j:object>
				<i:include name="dream" value="${dreamsign.taggable}" page="dreamData.jsp" />
			</j:object>						  
		</j:array>	
			
			
		<%-- Page Navigator --%>
		<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />	
		
	</c:if>
