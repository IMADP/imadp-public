<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	<j:object escapeXml="false"> 
		<j:object name="validationResult">	
			<j:property name="validationErrorsFound" value="${actionBean.context.validationErrorsFound}"/>
			<j:array name="ajaxValidationErrors" var="error" items="${actionBean.context.ajaxValidationErrors}">
				<j:object>
					<j:property name="validationMessage" value="${error.validationMessage}"/>
					<j:array name="objectNames" items="${error.objectNames}"/>					
				</j:object>	
			</j:array>		
		</j:object>
	</j:object>