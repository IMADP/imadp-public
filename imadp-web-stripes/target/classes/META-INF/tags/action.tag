<%@ tag description="Tag providing item pagination" pageEncoding="UTF-8"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>
	
	<input type="hidden" name="${name}.formToken" value="${actionBean.context.randomToken}" />
	<input type="hidden" name="${name}.sessionToken" value="${actionBean.context.sessionToken}" />