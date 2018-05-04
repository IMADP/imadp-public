<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Calendar Entry Data --%>
	<page:persistableData persistable="${calendarEntry}">
		<j:property name="editable" value="${!calendarEntry.unsaved}"/> 
		<j:property name="startDateString" value="${calendarEntry.startDateString}"/> 
		<j:property name="endDateString" value="${calendarEntry.endDateString}"/> 
		<j:property name="startTimeString" value="${calendarEntry.startTimeString}"/> 
		<j:property name="endTimeString" value="${calendarEntry.endTimeString}"/> 
		<j:property name="name" value="${calendarEntry.name}"/> 
		<j:property name="tracker" value="${calendarEntry.tracker.name}"/> 
		<j:property name="description" value="${calendarEntry.description}"/> 
		<j:property name="notes" value="${calendarEntry.notes}"/>
		<j:property name="recurring" value="${calendarEntry.recurring}"/>
		<j:property name="alert" value="${calendarEntry.alert}"/>
		
		<j:object name="recurrence">
			<j:property name="type" value="${calendarEntry.recurrence.type}"/> 
			<j:property name="length" value="${calendarEntry.recurrence.length}"/>
		</j:object>
		
		<j:property name="startDate">
			<i:date value="${calendarEntry.startDate}" pattern="M/dd/yyyy" />
		</j:property>					
		
		<j:property name="endDate">
			<i:date value="${calendarEntry.endDate}" pattern="M/dd/yyyy" />
		</j:property>					
		
		<j:property name="startTime">
			<i:date value="${calendarEntry.startTime}" pattern="h:mm aa" />
		</j:property>					
		
		<j:property name="endTime">
			<i:date value="${calendarEntry.endTime}" pattern="h:mm aa" />
		</j:property>					
		
	</page:persistableData>