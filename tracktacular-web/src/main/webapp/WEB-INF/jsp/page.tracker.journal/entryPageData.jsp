<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Dream Page Data --%>
	<j:object name="entry">
		<i:include name="entry" value="${actionBean.entry}" page="entryData.jsp" />
	</j:object>