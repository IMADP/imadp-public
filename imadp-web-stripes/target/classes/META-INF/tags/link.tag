<%@ tag description="Tag providing item pagination" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
 
<%@ attribute name="link" required="true" rtexprvalue="true" type="com.imadp.web.stripes.link.Link" %>
<%@ attribute name="cssClass" required="false" rtexprvalue="true" %>
<%@ attribute name="title" required="false" rtexprvalue="true" %>
		
	<c:choose>
	    <c:when test="${link.disabled}">
	    	<span class="${cssClass}">${link.label}</span> 
		</c:when>
	    <c:otherwise>
	    	<s:link beanclass="${link.beanClass}" class="${cssClass}" title="${title}" >   
	    		<c:forEach var="parameter" items="${link.parameters}">
					<s:param name="${parameter.key}" value="${parameter.value}"/>
				</c:forEach>
	    		${link.label} <jsp:doBody/> 
	    	</s:link>			
	   </c:otherwise>
	</c:choose>		    			