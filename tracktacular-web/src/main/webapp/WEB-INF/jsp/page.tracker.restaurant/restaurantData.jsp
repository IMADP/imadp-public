<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Restaurant Data --%>	
	<page:persistableData persistable="${restaurant}">	
		<j:property name="name" value="${restaurant.name}"/>
		<j:property name="state" value="${restaurant.state}"/>
		<j:property name="city" value="${restaurant.city}"/>
		<j:property name="tag" value="${restaurant.tag}"/>
		<j:property name="notes" value="${restaurant.notes}"/>
		<j:property name="rating" value="${restaurant.rating}"/>
		<j:property name="restaurantCollapsed" value="true"/>
		<j:property name="mealsSortable" value="${restaurant.mealCount > 1}"/>
		<j:property name="completed" value="${restaurant.completed}"/>
		<j:property name="targetDateSet" value="${restaurant.targetDate != null}"/>
		<j:property name="targetDateString" value="${restaurant.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${restaurant.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
		<j:object name="newMeal">
			<j:property name="id" value="${restaurant.uid}-newMeal"/>
			<j:property name="restaurantId" value="${restaurant.uid}"/>
			<j:property name="restaurantName" value="${restaurant.name}"/>
		</j:object>
		
		<j:array var="meal" name="meals" items="${restaurant.meals}" >
			<j:object>			
				<i:include name="meal" value="${meal}" page="mealData.jsp" />
			</j:object>
		</j:array>
		
	</page:persistableData>