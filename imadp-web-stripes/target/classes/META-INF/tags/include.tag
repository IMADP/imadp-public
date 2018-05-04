<%@ tag description="Tag providing readable jsp includes with an object parameter" pageEncoding="UTF-8"%>

<%@ attribute name="page" rtexprvalue="true" required="true" %>
<%@ attribute name="name" rtexprvalue="true" required="true" %>
<%@ attribute name="value" rtexprvalue="true" required="true" type="java.lang.Object" %>

	<% request.setAttribute(name, value); %>
	<jsp:include page="${page}" />
	<% request.removeAttribute(name); %>