<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Deleted Page Data --%>
	<j:property name="deletedGoalsCount" value="${actionBean.itemsPageProvider.page.itemCount}"/>
	
	<j:array var="goal" name="deletedGoals" items="${actionBean.itemsPageProvider.page.items}" >
		<j:object>
			<i:include name="goal" value="${goal}" page="goalData.jsp" />
			
			<%-- Objective Clones --%>
			<j:array var="objective" name="objective-clones" items="${goal.objectives}" >			
				<j:object>
 					<j:property name="id" value="${objective.uid}-clone"/>     
					<j:property name="name" value="${objective.name}"/>     
					<j:property name="notes" value="${objective.notes}"/>   
					<j:property name="targetDateString" value="${objective.targetDateString}" />
					<j:property name="goalId" value="${objective.goal.uid}"/>
				</j:object>
			</j:array>
			
		</j:object>						  
	</j:array>	
	
	
	<%-- Page Navigator --%>
	<page:pageNavigatorData pageProvider="${actionBean.itemsPageProvider}" />