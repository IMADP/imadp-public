<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

	
	<%-- Gifts Page Data --%>	
	<j:property name="giftCount" value="${actionBean.gifts.giftCount}"/>
	<j:property name="showChart" value="${actionBean.gifts.giftCount > 0}"/>
		
	<j:property name="givenPageUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.gift.GivenActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			<s:param name="sortProperty" value="occasion"/>
		</s:url>
	</j:property>
	
	<j:property name="receivedPageUrl">
		<s:url beanclass="com.tracktacular.web.page.tracker.gift.ReceivedActionBean">
			<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			<s:param name="sortProperty" value="occasion"/>
		</s:url>
	</j:property>
	
	<j:object name="giftsByOccasion">
		<j:property name="selected" value="${'occasion' == actionBean.sortProperty}"/>
		<j:property name="id" value="occasion"/>
		<j:property name="color" value="#ff4540"/>
		<j:property name="urlLabel" value="On Occasion"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.gift.GivenActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="occasion"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:object name="giftsByReceiver">
		<j:property name="selected" value="${'receiver' == actionBean.sortProperty}"/>
		<j:property name="id" value="receiver"/>
		<j:property name="color" value="#7092BE"/>
		<j:property name="urlLabel" value="By Receiver"/>
		
		<j:property name="url">
			<s:url beanclass="com.tracktacular.web.page.tracker.gift.GivenActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
				<s:param name="sortProperty" value="receiver"/>
			</s:url>
		</j:property>
		
	</j:object>
	
	<j:array var="giftCategory" name="giftCategories" items="${actionBean.gifts.giftCategories}" >
		<j:object>			
			<j:property name="name" value="${giftCategory.name}"/>
			<j:property name="nameSlug" value="${giftCategory.nameSlug}"/>
			<j:property name="giftCount" value="${giftCategory.itemCount}"/>
	
			<j:array var="gift" name="gifts" items="${giftCategory.items}" >
				<j:object>		
					<i:include name="gift" value="${gift}" page="giftData.jsp" />
				</j:object>
			</j:array>
					
		</j:object>
	</j:array>
	
	<%-- Chart Data --%>
	<j:object name="chartData">
		<j:array var="category" name="categories" items="${actionBean.gifts.chartGiftCategories}" >
			<j:object>
				<i:out><j:property name="name" value="${category.name}"/></i:out>
				<j:property name="giftCount" value="${category.itemCount}"/>
				
				<j:array var="childCategory" name="childCategories" items="${category.childCategories}" >
					<j:object>
						<i:out><j:property name="name" value="${childCategory.name}"/></i:out>
						<j:property name="giftCount" value="${childCategory.itemCount}"/>
					</j:object>
				</j:array>
				
			</j:object>
		</j:array>
	</j:object>