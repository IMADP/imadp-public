<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > News"
	>

	<s:layout-component name="contentHeading">		
		News
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
	
		<div class="news">
		
			<h3>July 12, 2013</h3>
			
			<ul>
				<li>		
					You can now connect your Steam account to your 
					<s:link beanclass="com.tracktacular.web.page.tracker.game.GamesActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						Game Tracker
					</s:link> 
					to keep your game library up to date!
				</li>
			</ul>
			
			<h3>June 19, 2013</h3>
			
			<ul>
				<li>		
					The rating system for all Entertainment trackers has been increased from a range of 1-6 to a range of 0-10, for
					more fine grained ratings.
				</li>
				<li>		
					We've added alert reminders for important birthdays on the 
					<s:link beanclass="com.tracktacular.web.page.tracker.birthday.BirthdaysActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						Birthdays Tracker
					</s:link>.
				</li>
			</ul>
			
			<h3>June 10, 2013</h3>
			
			<ul>
				<li>		
					You can now track your body fat percentage on the
					<s:link beanclass="com.tracktacular.web.page.tracker.body.BodyActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						Body Tracker
					</s:link>.
				</li>
			</ul>
			
			<h3>June 08, 2013</h3>
			
			<ul>
				<li>		
					You can now track albums as well as individual songs on the 
					<s:link beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						Music Tracker
					</s:link>.
				</li>
			</ul>
			
			<h3>June 03, 2013</h3>
			
			<ul>
				<li>		
					We've added an export feature so you can now extract your data from all trackers as CSV files.
					You can export at any time over on the 
					<s:link beanclass="com.tracktacular.web.page.account.AccountActionBean"> Account </s:link> page.
				</li>
			</ul>
			
			<h3>June 02, 2013</h3>
			
			<ul>
				<li>
					Trackers can now generate alerts - important notifications about your data. For example,
					you can configure the Exercise Tracker to alert you if you haven't entered a workout in a while,
					or you can specify important events on the Calendar Tracker. Alerts are displayed on your
					<s:link beanclass="com.tracktacular.web.page.TracktacularReportActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						Tracktacular Report
					</s:link>, and they can be sent to you via email according to your 
					<s:link beanclass="com.tracktacular.web.page.account.AccountActionBean"> Account </s:link> settings.
				</li>
				<li>
					The 
					<s:link beanclass="com.tracktacular.web.page.tracker.music.AlbumsActionBean">
						<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
						Music Tracker
					</s:link> has been revamped to allow musicians to track their progress learning
					how to play songs on their favorite instrument.
				</li>
			</ul>
			
			<h3>May 29, 2013</h3>
			
			<ul>
				<li>
					The mobile site is now live! The mobile site was built with HTML5 standards, so it should be
					usable regardless of what phone you have. Check it out at 
					<s:link beanclass="com.tracktacular.web.page.MobileActionBean"> Tracktacular Mobile </s:link>.
				</li>
			</ul>
			
		</div>		
			
	</s:layout-component>
	
</s:layout-render>