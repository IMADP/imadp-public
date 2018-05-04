<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>


	<%-- Footer --%>				
	<div class="center" style="margin-left: 175px; margin-top: -35px; margin-bottom: 15px;">
		<c:forEach var="tracker" items="${actionBean.allTrackers}">
			<c:set var="link" value="${actionBean.pageLinks[tracker]}" />
				<i:link link="${link}">
			   		<span class="icon-s s-${tracker.name}" title="<fmt:message key="${tracker}"/>" style="margin-right: 8px;"></span>
			   	</i:link>
		</c:forEach>	
	</div>
	
	<footer id="footer">
	
		<nav class="add-nav">
			<ul>
				<li>
					<s:link beanclass="com.tracktacular.web.page.IndexActionBean">
						Home
					</s:link>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.FaqsActionBean">
						FAQs
					</s:link>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.PricingActionBean">
						Pricing
					</s:link>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.GettingStartedActionBean">
						Getting Started
					</s:link>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.NewsActionBean">
						News
					</s:link>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.FeedbackActionBean">
						Feedback
					</s:link>
				</li>
				<li>
					<a href="mailto:webmaster@tracktacular.com">
						Contact
					</a>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
						Join
					</s:link>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.PrivacyPolicyActionBean">
						Privacy Policy
					</s:link>
				</li>
				<li>
					<s:link beanclass="com.tracktacular.web.page.TermsAndConditionsActionBean">
						Terms
					</s:link>
				</li>
				<li>
					<a href="https://www.facebook.com/tracktacular/" class="icon-s s-facebook" style="margin-bottom:-3px"></a>
				</li>
				<li>
					<a href="https://twitter.com/Tracktacular" class="icon-s s-twitter" style="margin-bottom:-3px"></a>
				</li>
			</ul>
		</nav>
		
		<p> &copy; 2012, Tracktacular </p>
		
	</footer>