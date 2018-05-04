<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Getting Started"
	>

	<s:layout-component name="contentHeading">		
		Getting Started
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
	
		<div class="home-content">
				
			<h2>Getting Started</h2>
						
			<div class="text">
				You strike me as a person who wants to be organized. It takes time up front, but
				that time is returned to you and then some in the future. Lets get started!
			</div>
				
			<c:if test="${!actionBean.context.loggedIn}">
				<h3 style="margin: 30px 0 0 60px;"><span class="icon-l l-join" style="margin-bottom: -8px; margin-right: 5px;"></span> 
					Join Up
				</h3>
				
				<div class="text">
					Joining Tracktacular is simple: all you need is a username, email, and a password. You'll get an unlimited
					trial of the site and all trackers for one month.
					<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">Join Now!</s:link>
				</div> 
				
			</c:if>	
			
			<c:if test="${actionBean.context.loggedIn && !actionBean.context.emailVerified}">
			
				<h3 style="margin: 30px 0 0 60px;"><span class="icon-l l-email" style="margin-bottom: -8px; margin-right: 5px;"></span> 
					Email Verification
				</h3>
				
				<div class="text">
					Check your inbox to verify your email address. Without a verified email, we'll have no way of resetting your password
					if you ever lose it.
				</div> 
				
			</c:if>	
				
				
			<h3 style="margin: 30px 0 0 60px;"><span class="icon-l l-account" style="margin-bottom: -8px; margin-right: 5px;"></span> 
				Account Settings
			</h3> 

			<div class="text">
				Visit the <s:link beanclass="com.tracktacular.web.page.account.AccountActionBean"> Account Page </s:link>
				whenever you want to change your tracker settings or general preferences. By default, all trackers are enabled and private.
			</div> 
				
			<h3 style="margin: 30px 0 0 60px;"><span class="icon-l l-task" style="margin-bottom: -8px; margin-right: 5px;"></span> 
				Start Tracking
			</h3>
			
			<div class="text">
				If you're not sure what to do first, I recommend starting with the 
				
				<s:link beanclass="com.tracktacular.web.page.tracker.task.TasksActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					Task Tracker.
				</s:link>
				
				Externalizing your tasks is the simplest and quickest way to clear your mind.
			</div>
			
			<h3 style="margin: 30px 0 0 60px;"><span class="icon-l l-alert" style="margin-bottom: -8px; margin-right: 5px;"></span> 
				Schedule Alerts
			</h3>
			
			<div class="text">
				Some trackers generate alerts, such as not completing a task or goal on time, or reminding you about a calendar event.
				The more alerts you set up, the less you'll need to remember. Try it now by setting a target date on one of your tasks.
			</div> 
				
			<h3 style="margin: 30px 0 0 60px;"><span class="icon-l l-wish" style="margin-bottom: -8px; margin-right: 5px;"></span> 
				Have Fun!
			</h3>
			
			<div class="text">
				Tracktacular makes it fun to organize your life. There's something very rewarding about achieving goals, 
				seeing patterns in your dreams, or watching your exercise progress improve over time. 
				The more you put into it, the more you'll get back. Check out the 
				<s:link beanclass="com.tracktacular.web.page.BlogActionBean">Tracktacular Blog</s:link> if you want to learn more about life logging.
			</div> 
				
			<div class="center text" style="margin-top: 5px;margin-bottom: -30px;">
				<b style="color: #3F4040;">What are you waiting for, start trackin!</b>
			</div>
			
			<c:if test="${!actionBean.context.loggedIn}">
				<p class="center text" style="margin-top: 30px;margin-bottom: -15px;">
					<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
						<img src="/static/tracktacular/img/page/try.png"/>
					</s:link>
				</p>
			</c:if>
			
		</div>
			
	</s:layout-component>
	
</s:layout-render>