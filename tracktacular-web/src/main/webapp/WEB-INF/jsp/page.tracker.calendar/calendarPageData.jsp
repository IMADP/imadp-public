<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Calendar Page Data --%>
	<j:property name="calendarTitle">
		<i:date value="${actionBean.date}" pattern="MMMM, yyyy" /> 
	</j:property>   
	
	<j:property name="monthOfYear" value="${actionBean.calendarMonth.monthOfYear}"/>	
	
	<%-- Need to use c:set to trim --%>
	<c:set var="previousMonthDateParam">
		<i:date value="${actionBean.previousMonth}" pattern="MM-yyyy" />
	</c:set>
	
	<j:property name="previousMonthUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.calendar.CalendarActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			<s:param name="date" value="${previousMonthDateParam}" />
		</s:url>
	</j:property>
	
	<c:set var="nextMonthDateParam">
		<i:date value="${actionBean.nextMonth}" pattern="MM-yyyy" />
	</c:set>
	
	<j:property name="nextMonthUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.calendar.CalendarActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			<s:param name="date" value="${nextMonthDateParam}" />
		</s:url>
	</j:property>
	
	
	<%-- Recurrence Types --%>
	<page:recurrenceTypes/>
	
	
	<%-- Months --%>
	<j:array var="month" name="months" items="${actionBean.months}" >
		<j:object>
			<j:property name="id" value="${month.monthOfYear}"/>
			<j:property name="selected" value="${month.monthOfYear == actionBean.date.monthOfYear}"/>
			
			<j:property name="monthName">
				<i:date value="${month}" pattern="MMM" />
			</j:property>
			
			<%-- Need to use c:set to trim --%>
			<c:set var="dateParam">
				<i:date value="${month}" pattern="MM-yyyy" />
			</c:set>
			
			<j:property name="selectMonthUrl">
				<s:url beanclass="com.tracktacular.web.page.tracker.calendar.CalendarActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					<s:param name="date" value="${dateParam}" />
				</s:url>
			</j:property>
			
		</j:object>
	</j:array>
	
	
	<%-- Weeks --%>
	<j:array var="week" name="weeks" items="${actionBean.calendarMonth.weeks}" >
		<j:object>
			<j:property name="noItems" value="${week.itemCount == 0}"/>
			<j:property name="id" value="week-${week.id}"/>
			<j:property name="weekCollapsed" value="${week.beforeCurrentWeek}"/>
			
			<j:property name="firstDay">
				<i:date value="${week.firstDay.date}" pattern="MMMM d" /> 
			</j:property>  
					
			<j:property name="lastDay">
				<i:date value="${week.lastDay.date}" pattern="MMMM d" /> 
			</j:property>  
						
			<%-- Days --%>
			<j:array var="day" name="days" items="${week.days}" >
				<j:object>
					<j:property name="id" value="day-${day.id}"/>
					<j:property name="dayId" value="day-${day.id}"/>
					<j:property name="itemCount" value="${day.itemCount}"/>
					<j:property name="weekId" value="week-${week.id}"/>
					<j:property name="dayCollapsed" value="${day.beforeCurrentDay}"/>
					<j:property name="hidden" value="${day.itemCount == 0}"/>
					<j:property name="dayNumber" value="${day.date.dayOfMonth}"/>
					<j:property name="activeMonth" value="${day.activeMonth}"/>
					<j:property name="currentDay" value="${day.currentDay}"/>
					<j:property name="sunday" value="${day.sunday}"/>
					
					<j:property name="date">
						<i:date value="${day.date}" pattern="EEEE MMMM d, yyyy" /> 
					</j:property>  
					 
					<j:property name="monthAndYear">
						<i:date value="${day.date}" pattern="MMMM d, yyyy" /> 
					</j:property>  
					 
					<j:property name="dayOfWeek">
						<i:date value="${day.date}" pattern="EEEE" /> 
					</j:property>  
					 
					<j:property name="time">
						<i:date value="${day.date}" pattern="h:mm a" /> 
					</j:property>   
					
					<j:object name="newEntry">
						<j:property name="id" value="entry-day-${day.id}"/>		
										
						<j:property name="startDateString" >
							<i:date pattern="yyyy-MM-dd" value="${day.date}"/>
						</j:property>   					
							
					</j:object>   
					
					<%-- Items --%>
					<j:array var="item" name="items" items="${day.items}" >
						<j:object>
							<i:include name="calendarEntry" value="${item.calendarEntry}" page="calendarEntryData.jsp" />
						</j:object>
					</j:array>
					
				</j:object>
			</j:array>
		</j:object>						  
   </j:array>
