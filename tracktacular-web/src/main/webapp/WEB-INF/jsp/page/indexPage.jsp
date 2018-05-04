<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular"
	>

	<s:layout-component name="contentHeading">		
		Tracktacular	
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
			
		<div class="home-content">
		
			<div class="cf">
				<div class="left" style="width: 500px;">
					<h2 style="margin-right: 0">Organize your life in one place.</h2>
								
					<p class="text" style="padding-right: 20px;">
						It's hard to feel organized when your data is spread out all over the internet.
						Clear your mind and keep track of your life with our intuitive trackers. <br/> No ads, no clutter. Just your data.
						
						<br/>
						<br/>
						
						Sign up today for a free trial and start tracking. <br/>
						<b>No credit card required.</b>
					</p>
				</div>
				<div class="left center" style="width: 250px; margin-top: 35px;">
					<img src="/static/tracktacular/img/page/logo-t.png" style="margin-bottom: 15px;"/><br/>
					<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
						<img src="/static/tracktacular/img/page/try.png"/>
					</s:link>
				</div>
			</div>
		
			<div class="cf" style="margin-top: 30px;">
				<div class="left" style="width: 250px;">
					<img src="/static/tracktacular/img/page/lock.jpg" style="margin-left: 65px;"/>
				</div>
				<div class="left" style="width: 500px;">
					<h2 style="margin-right: 0">Privacy. Guaranteed.</h2>
								
					<p class="text" style="padding-right: 20px;">
						Privacy is somewhat of a luxury on the internet. At Tracktacular, its guaranteed. All of your data
						is encrypted, and we will <b>never</b> sell or share your data or personal information with any marketing company, period.
					</p>
				
				</div>
			</div>
		
			<div class="cf">
				<div class="left" style="width: 450px;">
					<h2 style="margin-right: 0">Track on the move.</h2>
								
					<p class="text" style="padding-right: 20px;">
						Now you can track your data on the go with Tracktacular Mobile, available on all mobile devices with a web browser.
						
						<br/>
						<br/>
						
						Quickly add outstanding tasks, rate the last meal you ordered at a restaurant, or add a new idea to a project 
						journal to explore later.  
						
						<br/>
						<br/>
						
						Never worry about being away from your computer to stay organized. And there's no extra charge to use it.
					</p>
				</div>
				<div class="left center" style="width: 250px; margin-top: 35px;">
					<img src="/static/tracktacular/img/page/screenshot-mini.png" style="margin-bottom: 15px;"/>
				</div>
			</div>
		
			<div class="cf" style="margin-top: 65px;">
				<div class="left" style="width: 350px;">
					<img src="/static/tracktacular/img/page/chart.png" style="margin-left: 65px; margin-top: 10px;"/>
				</div>
				<div class="left" style="width: 375px;">
					<h2 style="margin-top: 0; margin-left:20px;">Charts for days.</h2>
								
					<p class="text" style="padding-right: 0; padding-left: 20px;">
						Visualize your data with dozens of different charts and reports. Graph your weight over a diet,
						show off your lifting gains, or keep an eye on your cholesterol levels.
						
						<br/>
						<br/>
						
						See it all together along with important alerts on your <b>Tracktacular Report</b>.
					</p>
				
				</div>
			</div>
		
			<h2 class="center" style="margin: 0; margin-top: 65px;">And plenty more.</h2>
								
			<p class="center text">
				Tracktacular is completely free to try for 30 days. <br/>
				Try it out today, or learn more over at our 
				<s:link beanclass="com.tracktacular.web.page.FaqsActionBean">
					Frequently Asked Questions
				</s:link>.
			</p>
			
			<p class="center text" style="margin-bottom: -15px;">
				<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
					<img src="/static/tracktacular/img/page/try.png"/>
				</s:link>
			</p>
			
		</div>
		
	</s:layout-component>
	
</s:layout-render>