<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp" 
	title="Tracktacular > Account"
	contentBodyTemplate="accountPageContent">

	<s:layout-component name="contentHeading">		
		Account	
	</s:layout-component>
	
	<%-- Content Data --%>	
	<s:layout-component name="contentData">
		<j:property name="email" value="${actionBean.context.email}"/>
		<j:property name="emailNotVerified" value="${!actionBean.context.emailVerified}"/>
		<j:property name="emailAlerts" value="${actionBean.userPreferences.emailAlerts}"/>
		<j:property name="blogNotification" value="${actionBean.userPreferences.blogNotification}"/>
		
		<j:property name="expired" value="${actionBean.userSubscription.expired}"/>
		<j:property name="active" value="${actionBean.userSubscription.active}"/>
		<j:property name="trialing" value="${actionBean.userSubscription.trialing}"/>
		<j:property name="pastDue" value="${actionBean.userSubscription.pastDue}"/>
		<j:property name="unpaid" value="${actionBean.userSubscription.unpaid}"/>
		<j:property name="cancelled" value="${actionBean.userSubscription.cancelled}"/>
		<j:property name="subscriptionPeriodDaysRemaining" value="${actionBean.userSubscription.subscriptionPeriodDaysRemaining}"/>
		<j:property name="monthly" value="${actionBean.userSubscription.subscriptionPlan.subscriptionId == 'member_monthly'}"/>
		<j:property name="annual" value="${actionBean.userSubscription.subscriptionPlan.subscriptionId == 'member_annual'}"/>
		<j:property name="lifetime" value="${actionBean.userSubscription.active && actionBean.userSubscription.subscriptionPlan == null}"/>
		<j:property name="activeReferralCount" value="${actionBean.activeReferralCount}"/>
		<j:property name="discount" value="${actionBean.activeReferralCount > 0}"/>
		<j:property name="discountPercentage" value="${actionBean.activeReferralCount > 4 ? 100 : actionBean.activeReferralCount * 20}"/>
		
		<j:property name="subscriptionStart">
			<i:date value="${actionBean.userSubscription.subscriptionStart}" pattern="MMM d, yyyy" />
		</j:property> 
		
		<j:property name="subscriptionPeriodStart">
			<i:date value="${actionBean.userSubscription.subscriptionPeriodStart}" pattern="MMM d, yyyy" />
		</j:property> 
		
		<j:property name="subscriptionPeriodEnd">
			<i:date value="${actionBean.userSubscription.subscriptionPeriodEnd}" pattern="MMM d, yyyy" />
		</j:property>
		
		<j:property name="unsubscribeUrl">	
			<s:url beanclass="com.tracktacular.web.page.account.UnsubscribeActionBean" />				
		</j:property>
		
		<j:property name="subscribeUrl">	
			<s:url beanclass="com.tracktacular.web.page.account.SubscribeActionBean" />				
		</j:property>
		
		<j:property name="sendVerificationEmailUrl">
			<s:url event="sendVerificationEmail" beanclass="com.tracktacular.web.page.account.AccountActionBean" />
		</j:property>			
		
		<j:property name="referralUrl">	
			<s:url beanclass="com.tracktacular.web.page.ReferralActionBean">
				<s:param name="referrerUserEid" value="${actionBean.context.user.eid}"/>
			</s:url>				
		</j:property>
		
		<j:property name="tracktacularReportUrl">	
			<s:url beanclass="com.tracktacular.web.page.TracktacularReportActionBean">
				<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
			</s:url>				
		</j:property>
		
		<j:property name="exportDataUrl">	
			<s:url event="exportData" beanclass="com.tracktacular.web.page.account.AccountActionBean" />				
		</j:property>
		
		<%-- Referrals --%>
		<j:array name="referrals">
			<c:forEach var="referral" varStatus="status" items="${actionBean.referrals}">
				<j:object>		
					<j:property name="subscriptionStatus" value="${referral.subscriptionStatus}"/>
					<j:property name="discount" value="${referral.active}"/>
					
					<j:property name="timeCreatedDate"> 
						<i:date value="${referral.timeCreatedDate}" pattern="MMM d, yyyy" />
					</j:property> 
					
					<j:property name="index" value="${status.index + 1}"/>
				</j:object>
			</c:forEach>	
		</j:array>
		
		<%-- Trackers --%>
		<j:array var="tracker" name="trackers" items="${actionBean.trackers}" >
			<j:object>
				<c:set var="trackerPreferencesName" value="${tracker.name}TrackerPreferences" />
				<j:property name="trackerName" value="${tracker.name}"/>
				<j:property name="trackerPublicName" value="preferences.trackers.${trackerPreferencesName}.trackerPublic"/>
				<j:property name="trackerPublic" value="${actionBean.userPreferences.trackers[trackerPreferencesName].trackerPublic}"/>				
				<j:property name="trackerEnabledName" value="preferences.trackers.${trackerPreferencesName}.trackerEnabled"/>
				<j:property name="trackerEnabled" value="${actionBean.userPreferences.trackers[trackerPreferencesName].trackerEnabled}"/>
				<j:property name="trackerTutorialName" value="preferences.trackers.${trackerPreferencesName}.trackerTutorial"/>
				<j:property name="trackerTutorial" value="${actionBean.userPreferences.trackers[trackerPreferencesName].trackerTutorial}"/>
				
				<j:property name="trackerTitle">
					<fmt:message key="${tracker}"/>
				</j:property> 
				
			</j:object>
		</j:array>
		
	</s:layout-component>	
	
	<%-- JS Resources --%>	
	<s:layout-component name="jsResources">
		
		<%-- JavaScript CDN Resources --%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>			
		
		<%-- JavaScript Fallback Resources --%>
		<script>//<![CDATA[
           	window.jQuery||document.write('<script src="/static/tracktacular/js/lib/jquery-1.7.1.min.js"><\/script>')//]]>
		</script>					
		<script>//<![CDATA[
           	window.jQuery.ui||document.write('<script src="/static/tracktacular/js/lib/jquery.ui-1.8.16.min.js"><\/script>')//]]>
           </script>			
		
		<%-- JavaScript Resources --%>
		<script src="/static/tracktacular/js/page-${actionBean.pageVersion}.js"></script>			 	
	 	<script src="/static/tracktacular/js/template-${actionBean.pageVersion}.js"></script>
	 	
 	 </s:layout-component>
				
</s:layout-render>