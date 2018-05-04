<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Recurrence Types --%>
	<page:recurrenceTypes/>
	
	<%-- Birthdays Page Data --%>	
	<j:object name="navigationChart">
		<j:property name="chartId" value="all"/>
		<j:property name="chartLabel" value="All"/>
		<j:property name="selected" value="${actionBean.selectedMonth == 'all'}"/>
		<j:property name="selectedAll" value="true"/>
		<j:property name="selectedContainerClass" value="birthday-month-panel"/>
		
		<j:property name="selectUrl">
			<s:url beanclass="com.tracktacular.web.page.tracker.birthday.BirthdaysActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="selectedMonth" value="all"/>
			</s:url>
		</j:property>
		
		<j:object name="chartData">
			<j:property name="id" value="all"/>
		</j:object>
		
	</j:object> 
	
	
	<%-- Birthday Months --%>
	<j:array var="birthdayMonth" name="birthdayMonths" items="${actionBean.birthdays.birthdayMonths}" >
		<j:object>			
			<j:property name="id" value="${birthdayMonth.month}"/>
			<j:property name="month" value="${birthdayMonth.month}"/>
			<j:property name="notCurrentMonth" value="${!birthdayMonth.currentMonth}"/>
			<j:property name="hidden" value="${actionBean.selectedMonth != 'all' && actionBean.selectedMonth != birthdayMonth.month}"/>
			<j:property name="birthdayCount" value="${birthdayMonth.birthdayCount}"/>
			<j:property name="collapsible" value="${birthdayMonth.birthdayCount > 0}"/>
				
			<c:set var="monthName">
				<i:date value="${birthdayMonth.monthAsDate}" pattern="MMM" />
			</c:set>
			
			<j:property name="monthName" value="${monthName}"/>
			 
			<j:property name="monthFullName">
				<i:date value="${birthdayMonth.monthAsDate}" pattern="MMMM" />
			</j:property>
			
			<j:array var="birthday" name="birthdays" items="${birthdayMonth.birthdays}" >
				<j:object>		
					<i:include name="birthday" value="${birthday}" page="birthdayData.jsp" />
				</j:object>
			</j:array>
			
			<%-- Navigation Chart Data --%>
			<j:object name="navigationChart">
				<j:property name="chartId" value="${birthdayMonth.month}"/>
				<j:property name="chartLabel" value="${monthName}"/>
				<j:property name="selected" value="${actionBean.selectedMonth != 'all' && actionBean.selectedMonth == birthdayMonth.month}"/>
				<j:property name="selectedTargetId" value="birthday-month-${birthdayMonth.month}"/>
				<j:property name="selectedContainerClass" value="birthday-month-panel"/>
				
				<j:object name="chartData">
					<j:property name="id" value="${birthdayMonth.month}"/>
				</j:object>
				
				<j:property name="selectUrl">
					<s:url beanclass="com.tracktacular.web.page.tracker.birthday.BirthdaysActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="selectedMonth" value="${birthdayMonth.month}"/>
					</s:url>
				</j:property>
				
			</j:object> 
					
		</j:object>
	</j:array>