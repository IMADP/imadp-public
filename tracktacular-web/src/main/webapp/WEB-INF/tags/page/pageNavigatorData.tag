<%@ include file="/WEB-INF/tags/includeTag.tag" %>

<%@ attribute name="pageProvider" required="true" rtexprvalue="true" type="com.imadp.web.stripes.paging.PageProvider" %>

	<c:set var="pageNavigator" value="${pageProvider.primaryPageNavigator}" />
	
	
	<%-- Page Navigator Data --%>
	<j:object name="pageNavigator">
		<j:property name="visible" value="${pageNavigator.visible}"/> 
		
		
		<%-- First Page Link --%>
		<j:object name="firstPageLink">
			<j:property name="disabled" value="${pageNavigator.firstPageLink.disabled}"/>
			<j:property name="url">
				<s:url beanclass="${pageNavigator.firstPageLink.beanClass}">   
		    		<c:forEach var="parameter" items="${pageNavigator.firstPageLink.parameters}">
						<s:param name="${parameter.key}" value="${parameter.value}"/>
					</c:forEach>
		    	</s:url>
			</j:property>
		</j:object>
		
		
		<%-- Previous Page Link --%>
		<j:object name="previousPageLink">
			<j:property name="disabled" value="${pageNavigator.previousPageLink.disabled}"/>
			<j:property name="url">
				<s:url beanclass="${pageNavigator.previousPageLink.beanClass}">   
		    		<c:forEach var="parameter" items="${pageNavigator.previousPageLink.parameters}">
						<s:param name="${parameter.key}" value="${parameter.value}"/>
					</c:forEach>
		    	</s:url>
			</j:property>
		</j:object> 
		
		
		<%-- Page Links --%>
		<j:array var="pageLink" name="pageLinks" items="${pageNavigator.pageLinks}" >
			<j:object>
				<j:property name="disabled" value="${pageLink.disabled}"/>
				<j:property name="label" value="${pageLink.label}"/>
				<j:property name="url">
					<s:url beanclass="${pageLink.beanClass}">   
			    		<c:forEach var="parameter" items="${pageLink.parameters}">
							<s:param name="${parameter.key}" value="${parameter.value}"/>
						</c:forEach>
			    	</s:url>
				</j:property>
			</j:object>						  
		</j:array>	
		
				
		<%-- Next Page Link --%>
		<j:object name="nextPageLink">
			<j:property name="disabled" value="${pageNavigator.nextPageLink.disabled}"/>
			<j:property name="url">
				<s:url beanclass="${pageNavigator.nextPageLink.beanClass}">   
		    		<c:forEach var="parameter" items="${pageNavigator.nextPageLink.parameters}">
						<s:param name="${parameter.key}" value="${parameter.value}"/>
					</c:forEach>
		    	</s:url>
			</j:property>
		</j:object>
			
			
		<%-- Last Page Link --%>
		<j:object name="lastPageLink">
			<j:property name="disabled" value="${pageNavigator.lastPageLink.disabled}"/>
			<j:property name="url">
				<s:url beanclass="${pageNavigator.lastPageLink.beanClass}">   
		    		<c:forEach var="parameter" items="${pageNavigator.lastPageLink.parameters}">
						<s:param name="${parameter.key}" value="${parameter.value}"/>
					</c:forEach>
		    	</s:url>
			</j:property>
		</j:object>
		
	</j:object>