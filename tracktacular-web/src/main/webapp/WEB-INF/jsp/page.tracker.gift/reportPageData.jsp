<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

			
	<%-- Report --%>		
	<page:trackerReport>	
		<j:property name="givenGiftCount" value="${trackerReport.givenGiftCount}"/>	
		<j:property name="receivedGiftCount" value="${trackerReport.receivedGiftCount}"/>
		
		<j:object name="giftsGiven">
			<j:object name="giftsByOccasion">
				<j:property name="selected" value="false"/>
				<j:property name="id" value="occasion"/>
				<j:property name="color" value="#ff4540"/>
				<j:property name="urlLabel" value="Given On Occasion"/>
				
				<j:property name="url">
					<s:url beanclass="com.tracktacular.web.page.tracker.gift.GivenActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="sortProperty" value="occasion"/>
					</s:url>
				</j:property>
				
			</j:object>
			
			<j:object name="giftsByReceiver">
				<j:property name="selected" value="false"/>
				<j:property name="id" value="receiver"/>
				<j:property name="color" value="#7092BE"/>
				<j:property name="urlLabel" value="Given To Receiver"/>
				
				<j:property name="url">
					<s:url beanclass="com.tracktacular.web.page.tracker.gift.GivenActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="sortProperty" value="receiver"/>
					</s:url>
				</j:property>
				
			</j:object>
		</j:object>
		
		<j:object name="giftsReceived">
		
			<j:object name="giftsByOccasion">
				<j:property name="selected" value="false"/>
				<j:property name="id" value="occasion"/>
				<j:property name="color" value="#ff4540"/>
				<j:property name="urlLabel" value="Received On Occasion"/>
				
				<j:property name="url">
					<s:url beanclass="com.tracktacular.web.page.tracker.gift.ReceivedActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="sortProperty" value="occasion"/>
					</s:url>
				</j:property>
				
			</j:object>
			
			<j:object name="giftsBySender">
				<j:property name="selected" value="false"/>
				<j:property name="id" value="sender"/>
				<j:property name="color" value="#7092BE"/>
				<j:property name="urlLabel" value="Received By Sender"/>
				
				<j:property name="url">
					<s:url beanclass="com.tracktacular.web.page.tracker.gift.ReceivedActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						<s:param name="sortProperty" value="sender"/>
					</s:url>
				</j:property>
				
			</j:object>
		
		</j:object>
	</page:trackerReport>
	