<%@ include file="/WEB-INF/tags/includeTag.tag" %>

<%@ attribute name="page" required="true" rtexprvalue="true" %>

	<div class="cf page">
		
		<div class="left">
			<c:choose>
				<c:when test="${pageBulletNumber == null || pageBulletNumber == 3}">
					<c:set var="pageBulletNumber" value="1" scope="request"/>
					<span class="icon-s s-green-bullet"></span>
				</c:when>
				<c:when test="${pageBulletNumber == 1}">
					<c:set var="pageBulletNumber" value="2" scope="request"/>
					<span class="icon-s s-blue-bullet"></span>
				</c:when>
				<c:when test="${pageBulletNumber == 2}">
					<c:set var="pageBulletNumber" value="3" scope="request"/>
					<span class="icon-s s-orange-bullet"></span>
				</c:when>
			</c:choose>
		</div>
		
		<div class="left">
			<div><b>${page}:</b> <jsp:doBody/></div>
		</div>
		
	</div>