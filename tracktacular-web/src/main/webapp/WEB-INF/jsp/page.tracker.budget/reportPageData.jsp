<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report --%>		
	<page:trackerReport>	
		<j:property name="activeBudgetsCount" value="${trackerReport.activeBudgetsCount}"/> 		
		
		<j:array var="budget" name="activeBudgets" items="${trackerReport.activeBudgetsList}" >
			<j:object>
				<i:include name="budget" value="${budget}" page="budgetData.jsp" />
				
				<j:property name="selectBudgetUrl">
					<s:url beanclass="com.tracktacular.web.page.tracker.budget.BudgetsActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="selectedBudget" value="${budget.uid}"/>
					</s:url>
				</j:property>
				
			</j:object>					  
		</j:array>  
						
	</page:trackerReport>
	