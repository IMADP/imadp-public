<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Meal Data --%>	
	<page:persistableData persistable="${meal}">
		<j:property name="restaurantId" value="${meal.restaurant.uid}"/>
		<j:property name="restaurantName" value="${meal.restaurant.name}"/>
		<j:property name="restaurantCity" value="${meal.restaurant.city}"/>
		<j:property name="restaurantState" value="${meal.restaurant.state}"/>
		<j:property name="name" value="${meal.name}"/>
		<j:property name="notes" value="${meal.notes}"/>
		<j:property name="tag" value="${meal.tag}"/>
		<j:property name="rating" value="${meal.rating}"/>
		<j:property name="mealsSortable" value="${meal.restaurant.mealCount > 1}"/>
		<j:property name="completed" value="${meal.completed}"/>
		<j:property name="targetDateString" value="${meal.targetDateString}"/>
		
		<j:property name="targetDate">
			<i:date value="${meal.targetDate}" pattern="MM/dd/yyyy" />
		</j:property>		
		
	</page:persistableData>