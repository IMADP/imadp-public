<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Birthday Data --%>	
	<page:persistableData persistable="${birthday}">	
		<j:property name="firstName" value="${birthday.firstName}"/>
		<j:property name="middleName" value="${birthday.middleName}"/>
		<j:property name="lastName" value="${birthday.lastName}"/>
		<j:property name="age" value="${birthday.age}"/>
		<j:property name="ageOrdinal" value="${birthday.ageOrdinal}"/>
		<j:property name="notes" value="${birthday.notes}"/>
		<j:property name="past" value="${birthday.past}"/>
		<j:property name="dateString" value="${birthday.dateString}"/>
		
		<j:object name="alertRecurrence">
			<j:property name="type" value="${birthday.alertRecurrence.type}"/> 
			<j:property name="length" value="${birthday.alertRecurrence.length}"/>
		</j:object>
		
		<j:property name="horoscope">
				<fmt:message key="${birthday.horoscope}"/>
		</j:property>					
		
		<j:property name="date">
			<i:date value="${birthday.date}" pattern="MM/dd/yyyy" />
		</j:property>					
		
		<j:property name="longDate">
			<i:date value="${birthday.nextBirthday}" pattern="EEEE, MMMM d, yyyy" />
		</j:property>					
		
		<j:property name="nextBirthday">
			<i:date value="${birthday.nextBirthday}" pattern="EEEE, MMMM dd, yyyy" />
		</j:property>
		
	</page:persistableData>