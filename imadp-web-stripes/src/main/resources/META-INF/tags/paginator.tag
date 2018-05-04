<%@ tag description="Tag providing item pagination" pageEncoding="UTF-8"%>

<%@ taglib prefix="i" uri="imadp/IMADPWebStripes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<%@ attribute name="pageProvider" required="true" rtexprvalue="true" type="com.imadp.web.stripes.paging.PageProvider"%>

	<c:if test="${pageProvider.page.pageCount > 1}">		
		<div class="paginator">
		
			<i:link link="${pageProvider.primaryPageNavigator.firstPageLink}" 
				cssClass="short-bookmarkable-paginator-first" title="Go to first page" /> &nbsp;
			
    		<i:link link="${pageProvider.primaryPageNavigator.previousPageLink}" 
				cssClass="short-bookmarkable-paginator-prev" title="Go to previous page" />
		     
			<c:forEach var="link" items="${pageProvider.primaryPageNavigator.pageLinks}" varStatus="status">
				<span class="navigation">
					<i:link link="${link}" cssClass="${link.label == pageProvider.primaryPageNavigator.page.pageNumber ? 'active' : ''}" title="Go to page ${link.label}" />
				</span>
				${not status.last ? '|' : ''}
			</c:forEach>
			
			<i:link link="${pageProvider.primaryPageNavigator.nextPageLink}" 
				cssClass="short-bookmarkable-paginator-next" title="Go to next page" /> &nbsp;
			
    		<i:link link="${pageProvider.primaryPageNavigator.lastPageLink}" 
				cssClass="short-bookmarkable-paginator-last" title="Go to last page" />
		     
	    </div>
	</c:if>