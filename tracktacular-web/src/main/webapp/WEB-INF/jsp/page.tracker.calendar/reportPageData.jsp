<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Report --%>
	<page:trackerReport>
		<j:property name="itemCount" value="${trackerReport.calendarWeek.itemCount}"/>	
		
		<%-- Items --%>
		<j:array var="calendarEntry" name="calendarEntryAlerts" items="${trackerReport.calendarEntryAlerts}" >
			<j:object>
				<i:include name="calendarEntry" value="${calendarEntry}" page="calendarEntryData.jsp" />
			</j:object>
		</j:array>
		
		<%-- Days --%>
		<j:array var="day" name="days" items="${trackerReport.calendarWeek.days}" >
			<j:object>
				<j:property name="id" value="${day.id}"/>
				<j:property name="itemCount" value="${day.itemCount}"/>
				<j:property name="weekId" value="${trackerReport.calendarWeek.id}"/>
				<j:property name="hidden" value="${day.itemCount == 0}"/>
				<j:property name="dayNumber" value="${day.date.dayOfMonth}"/>
				<j:property name="activeMonth" value="${day.activeMonth}"/>
				<j:property name="currentDay" value="${day.currentDay}"/>
				<j:property name="sunday" value="${day.sunday}"/>
				
				<j:property name="date">
					<i:date value="${day.date}" pattern="EEEE MMMM d, yyyy" /> 
				</j:property>  
				 
				<j:property name="time">
					<i:date value="${day.date}" pattern="h:mm a" /> 
				</j:property>   
				
				<%-- Items --%>
				<j:array var="item" name="items" items="${day.items}" >
					<j:object>
						<i:include name="calendarEntry" value="${item.calendarEntry}" page="calendarEntryData.jsp" />
					</j:object>
				</j:array>
				
			</j:object>
		</j:array>

	</page:trackerReport>