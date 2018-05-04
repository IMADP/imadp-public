<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Meals Page Data --%>	
	<j:property name="mealCount" value="${actionBean.meals.mealCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:object name="mealsByName">
		<j:property name="selected" value="${'name' == actionBean.sortProperty}"/>
		<j:property name="id" value="name"/>
		<j:property name="color" value="#7092BE"/>
		<j:property name="urlLabel" value="By Name"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.MealsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="name"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="mealsByRestaurant">
		<j:property name="selected" value="${'restaurant' == actionBean.sortProperty}"/>
		<j:property name="id" value="restaurant"/>
		<j:property name="color" value="#80699B"/>
		<j:property name="urlLabel" value="By Restaurant"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.MealsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="restaurant"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="mealsByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.MealsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="mealsByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.MealsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="mealCategory" name="mealCategories" items="${actionBean.meals.mealCategories}" >
		<j:object>			
			<j:property name="name" value="${mealCategory.name}"/>
			<j:property name="nameSlug" value="${mealCategory.nameSlug}"/>
			<j:property name="mealCount" value="${mealCategory.itemCount}"/>
	
			<j:array var="meal" name="meals" items="${mealCategory.items}" >
				<j:object>		
					<i:include name="meal" value="${meal}" page="mealData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>