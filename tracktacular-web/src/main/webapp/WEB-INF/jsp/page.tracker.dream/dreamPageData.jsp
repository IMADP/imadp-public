<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Dream Page Data --%>
	<j:object name="dream">
		<i:include name="dream" value="${actionBean.dream}" page="dreamData.jsp" />
	</j:object>