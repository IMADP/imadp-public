<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Body Data --%>
	<page:persistableData persistable="${body}">
		<j:property name="notes" value="${body.notes}"/> 
		<j:property name="weight" value="${body.weight}"/> 
		<j:property name="height" value="${body.height}"/> 
		<j:property name="neck" value="${body.neck}"/> 
		<j:property name="chest" value="${body.chest}"/> 
		<j:property name="waist" value="${body.waist}"/> 
		<j:property name="hips" value="${body.hips}"/> 
		<j:property name="biceps" value="${body.biceps}"/> 
		<j:property name="forearms" value="${body.forearms}"/> 
		<j:property name="wrists" value="${body.wrists}"/> 
		<j:property name="thighs" value="${body.thighs}"/> 
		<j:property name="calves" value="${body.calves}"/> 
		<j:property name="ankles" value="${body.ankles}"/> 
		<j:property name="feet" value="${body.feet}"/> 
		<j:property name="bodyFat" value="${body.bodyFat}"/> 
		<j:property name="dateString" value="${body.dateString}"/>
		
		<j:property name="date"> 
			<i:date value="${body.date}" pattern="MMM d, yyyy" />
		</j:property>
		
	</page:persistableData>