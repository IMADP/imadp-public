<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report Segment --%>		
	<page:trackerReport>	
		<j:property name="restaurantCount" value="${trackerReport.restaurantCount}"/>	
		<j:property name="unratedRestaurantCount" value="${trackerReport.unratedRestaurantCount}"/>	
		
		<j:array var="restaurant" name="unratedRestaurants" items="${trackerReport.unratedRestaurants}" >
			<j:object>
				<c:set var="restaurant" value="${restaurant}" scope="request" />
				<jsp:include page="restaurantData.jsp" />
			</j:object>
		</j:array>
		
		<j:array var="restaurant" name="targetDateRestaurants" items="${trackerReport.targetDateRestaurants}" >
			<j:object>
				<c:set var="restaurant" value="${restaurant}" scope="request" />
				<jsp:include page="restaurantData.jsp" />
			</j:object>
		</j:array>	
		
		<j:array var="meal" name="targetDateMeals" items="${trackerReport.targetDateMeals}" >
			<j:object>
				<c:set var="meal" value="${meal}" scope="request" />
				<jsp:include page="mealData.jsp" />
			</j:object>
		</j:array>	
		
		<j:object name="restaurantsByName">
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
			<j:property name="selected" value="false"/>
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
						
	</page:trackerReport>
	