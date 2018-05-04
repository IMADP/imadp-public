<%@ tag description="Tag providing millisecond time formatting" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  	uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="value"      rtexprvalue="true" required="false"  %>
<%@ attribute name="type"  		rtexprvalue="true" required="false" %>
<%@ attribute name="dateStyle"  rtexprvalue="true" required="false" %>
<%@ attribute name="timeStyle"  rtexprvalue="true" required="false" %>
<%@ attribute name="pattern"    rtexprvalue="true" required="true" %>

	<jsp:useBean id="dateBean" class="java.util.Date" scope="page" />
	
	<c:if test="${value != null}">
		<jsp:setProperty name="dateBean" property="time" value="${value}" />
	</c:if>
	
	<fmt:formatDate value="${dateBean}" 
		type="${type != null ? type : 'date'}" 
		dateStyle="${dateStyle != null ? dateStyle : 'default'}" 
		timeStyle="${timeStyle != null ? timeStyle : 'default'}"
		pattern="${pattern}" />		