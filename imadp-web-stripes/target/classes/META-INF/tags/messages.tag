<%@ tag description="Tag providing item pagination" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 	<div id="messages">
 	
	 	<c:if test="${actionBean.context.errorMessagesFound}">
			<div id="messages-error">
				<a href="#" title="Close Message" class="to-slide-toggle" data-target-id="messages-error">
					<span class="messages-inner-container"></span>
				</a>
				<s:messages key="MESSAGE_ERROR" />
			</div>
	 	</c:if>
	 	
	 	<c:if test="${actionBean.context.warnMessagesFound}">
			<div id="messages-warn">
				<a href="#" title="Close Message" class="to-slide-toggle" data-target-id="messages-warn">
					<span class="messages-inner-container"></span>
				</a>
				<s:messages key="MESSAGE_WARN" />
			</div>
	 	</c:if>
	 	
	 	<c:if test="${actionBean.context.infoMessagesFound}">
			<div id="messages-info">
				<a href="#" title="Close Message" class="to-slide-toggle" data-target-id="messages-info">
					<span class="messages-inner-container"></span>
				</a>
				<s:messages key="MESSAGE_INFO" />
			</div>
	 	</c:if>
	 	
	 	<c:if test="${actionBean.context.successMessagesFound}">
			<div id="messages-success">
				<a href="#" title="Close Message" class="to-slide-toggle" data-target-id="messages-success">
					<span class="messages-inner-container"></span>
				</a>
				<s:messages key="MESSAGE_SUCCESS" />
			</div>
	 	</c:if>
	 	
	 </div>