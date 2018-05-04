<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Pricing"
	>

	<s:layout-component name="contentHeading">		
		Pricing
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
	
		<div class="home-content">
				
			<div class="center text" style="margin-top: 15px;">
				Tracktacular provides peace of mind for the price of a movie ticket:
			</div>
			
			<div class="center" style="margin: 25px 220px 0 220px;">
							
				<div class="box-shadow center" style="margin-bottom: 15px;">
					<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"
						style="color:black;border:0;background: #5C9CCC url(/static/tracktacular/img/lib/redmond/ui-bg_gloss_gold-wave_55_5c9ccc_500x100.png) 50% 50% repeat-x;">
						<span class="center ui-dialog-title">Monthly Subscription</span>
					</div>
					<div style="padding: 15px 15px 0 15px">
						<b style="color: #28A328; font-size: 28px;">$10</b> <br/>
						per month <br/><br/>
						<b>Unlimited Trackers</b> <br/>
						<b>Guaranteed Privacy</b> <br/>
						<b>Military Grade Encryption</b> <br/>
						<b>One Click Data Export</b> <br/>
						<b>No Hassle Cancellation</b> <br/>
						<br/>
						<br/>
					</div>
				</div>
				
			</div>
			
			<div class="text">
				You can reduce or eliminate your subscription fee with our referral system. <br/>
				Each referral grants you a <b>20%</b> discount for the length of their entire subscription, <br/>
				 for a max discount up to <b>100%</b>. Learn more about referrals on our 
				<s:link beanclass="com.tracktacular.web.page.FaqsActionBean">
					FAQs
				</s:link> page.
			</div>
			
			<c:if test="${!actionBean.context.loggedIn}">
				<p class="center text" style="margin-bottom: -15px;">
					<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
						<img src="/static/tracktacular/img/page/try.png"/>
					</s:link>
				</p>
			</c:if>
			
		</div>		
			
	</s:layout-component>
	
</s:layout-render>