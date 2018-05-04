<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Restaurants Page Data --%>	
	<j:property name="restaurantCount" value="${actionBean.restaurants.restaurantCount}"/>
	<j:property name="sortProperty" value="${actionBean.sortProperty}"/>
	
	<j:object name="restaurantsByName">
		<j:property name="selected" value="${'name' == actionBean.sortProperty}"/>
		<j:property name="id" value="name"/>
		<j:property name="color" value="#7092BE"/>
		<j:property name="urlLabel" value="By Name"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.RestaurantsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="name"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="restaurantsByCity">
		<j:property name="selected" value="${'city' == actionBean.sortProperty}"/>
		<j:property name="id" value="city"/>
		<j:property name="color" value="#80699B"/>
		<j:property name="urlLabel" value="By City"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.RestaurantsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="city"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="restaurantsByState">
		<j:property name="selected" value="${'state' == actionBean.sortProperty}"/>
		<j:property name="id" value="state"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="By State"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.RestaurantsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="state"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="restaurantsByTag">
		<j:property name="selected" value="${'tag' == actionBean.sortProperty}"/>
		<j:property name="id" value="tag"/>
		<j:property name="color" value="#589800"/>
		<j:property name="urlLabel" value="By Tag"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.RestaurantsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="tag"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="restaurantsByRating">
		<j:property name="selected" value="${'rating' == actionBean.sortProperty}"/>
		<j:property name="id" value="rating"/>
		<j:property name="color" value="#ffd300"/>
		<j:property name="urlLabel" value="By Rating"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.restaurant.RestaurantsActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="rating"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="restaurantCategory" name="restaurantCategories" items="${actionBean.restaurants.restaurantCategories}" >
		<j:object>			
			<j:property name="name" value="${restaurantCategory.name}"/>
			<j:property name="nameSlug" value="${restaurantCategory.nameSlug}"/>
			<j:property name="restaurantCount" value="${restaurantCategory.itemCount}"/>
	
			<j:array var="restaurant" name="restaurants" items="${restaurantCategory.items}" >
				<j:object>		
					<i:include name="restaurant" value="${restaurant}" page="restaurantData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
			
	<page:ratingsData/>