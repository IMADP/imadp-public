<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Bucket List Page Data --%>
	<j:property name="itemCount" value="${actionBean.items.itemCount}"/>
	<j:property name="progress" value="${actionBean.items.progress}"/>
	<j:property name="finishedItemCount" value="${actionBean.items.finishedItemCount}"/>
	<j:property name="unfinishedItemCount" value="${actionBean.items.unfinishedItemCount}"/>
			
	<j:array var="item" name="items" items="${actionBean.items.items}" >
		<j:object>		
			<i:include name="item" value="${item}" page="itemData.jsp" />
		</j:object>
	</j:array> 