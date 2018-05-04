<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > FAQs"
	>

	<s:layout-component name="contentHeading">		
		FAQs
	</s:layout-component>
	
	<s:layout-component name="contentBody">		
	
		<div class="faqs">
		
			<h3>
				What is Tracktacular?
			</h3>
			
			<p>
				 Tracktacular is a way to simplify your life in one place. If you like to be organized, if you like to see patterns
				 in your behaviour through charts and graphs, if you like building up personal libraries of information, then 
				 Tracktacular is the place for you.
				 <br/>
				 <br/>
				  At the heart, Tracktacular is a collection of small applications called trackers, 
				 each one designed to organize one aspect of your life.
			</p>
			
			<h3>
				Why use Tracktacular over individual apps? 
			 </h3>
			
			<p>
			 	We built Tracktacular because we felt disorganized having the things we keep track of spread all over the internet.
			 	Different applications means different credentials, different interfaces, different privacy and security. 
			 	<br/>
				 <br/>
				 Tracktacular provides one place to keep track of dozens of different things, with a simple to use interface and clean
			 	presentation of your data. And its much more convenient to see a summary of everything in one spot.
			</p>
			
			<h3>
				 What about privacy?
			</h3> 
			
			<p>
				 We use a multi-faceted approach to protecting your privacy and your data. 
				 
				 <br/>
				 <br/>
				 
				 <b>1)</b> Every page on the site is protected by SSL. <br/>
				 <b>2)</b> Your password is encrypted with a one-way digest. <br/>
				 <b>3)</b> Your tracker data is stored with military grade encryption. <br/>
				 <b>4)</b> Your tracker data will <b>never</b> be sold or shared to a marketing company. <br/>
				
				 <br/>
				 
				 We use Tracktacular too, and we take privacy very seriously. We wanted to build a site where privacy
				 wasn't a luxury, but a guarantee. Your data belongs to you, it will not be shared with others,
				 and we take no ownership of anything you submit.
			</p>
			
			<h3>
				How do trackers work? 
			</h3>
		
			<p>
			 	 Each tracker is designed to be a lightweight application that organizes one aspect of your life.
				 Some trackers might be used frequently like Tasks or Journals,
			 	 others like Blood Pressure and Cholesterol might only be used annually. Each tracker has its own 
			 	 privacy settings and preferences, as well as a tutorial on how to use it.
			</p>
			
			<h3>
				Can I see a demo of a tracker?
			</h3>
			
			<p>
				You can see a demonstration of any tracker when you are logged out simply by visiting the tracker links in the left 
				navigation. If you are logged in, you can toggle between demo mode and live mode via the <b>View Demo Data</b> 
				link at the top of the page.
			</p>
			
			<h3>
				Do I have to use all the trackers?
			</h3>
			
			<p>
			 	We try to make all of our trackers useful and fun, but there's no hard feelings if you aren't interested in tracking
			 	certain things. On your
			
				<s:link beanclass="com.tracktacular.web.page.account.AccountActionBean">
					Account
				</s:link> 
				
				 page, you can disable any tracker you don't want to use and it will be removed
			 	from the navigation and all of your reports.
			</p>
			
			<h3>
				What are tracker alerts?
			</h3>
			
			<p>
			 	Each tracker has the ability to generate an alert, or an important event or notification about your data. For example, 
			 	an important event on your calendar, a reminder about an approaching birthday, an important task or goal, or a reminder 
			 	about an overdue cholesterol measurement. 
			 	
			 	<br/><br/>
			 	Alerts are combined and displayed on the 

				<s:link beanclass="com.tracktacular.web.page.TracktacularReportActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					Tracktacular Report
				</s:link>
	
				or emailed to you every day, so you can quickly focus on what's important. You can control the way alerts are 
				generated on a per-tracker basis, so you'll only see what matters to you.
			</p>
			
			<h3>
				What is the Tracktacular Report?
			</h3>
			
			<p>
			 	Each tracker will generate a report that contains a summary and any alerts associated with the tracker. The 
			 	
			 	<s:link beanclass="com.tracktacular.web.page.TracktacularReportActionBean">
					<s:param name="trackerUserUsername" value="${actionBean.trackerUserUsername}"/>
					Tracktacular Report
				</s:link>
				
				combines all these reports into one, so you can see at a glance any important events 
			 	or notifications in one place. You can also have the report emailed to you whenever alerts are found.
			</p>
			
			<h3>
				Can I share the stuff I track?
			</h3>
			
			<p>
			 	By default, all of your trackers are private, which means that only you can see them when you are logged in. However,
			 	you can make a tracker public and share the link with other people (members or non-members). In fact, when you look 
			 	at a tracker demonstration, you are seeing all of the public data of a fictitious user called <i>demo</i>.
			</p>
			
			<h3>
				Is there a mobile version? 
			</h3>
			
			<p>
			 	Yes, we've developed a mobile friendly version of the site to read and enter data on the go. The mobile site was built
			 	with html5, so it should be available for use in a wide variety of mobile devices, regardless of what type of phone you have. 
			 	Instructions for using the mobile site are on the 
			 	
			 	<s:link beanclass="com.tracktacular.web.page.MobileActionBean">
					Mobile
				</s:link> 
				
				page.
			</p>
			
			<h3>
				Can I export my tracker data?
			</h3>
			
			<p>
			 	You can export your data as a collection of csv files (Comma Separated Values) at any time with one click on the 
			 	Account page. You can do this to have a personal backup, or to migrate your data to other applications.
			</p>
			
			<h3>
				Can I import data into Tracktacular?
			</h3>
			
			<p>
				Certain trackers, like the Entertainment trackers, will allow you to import data as a csv file, which can easily be created from a spreadsheet or other
				applications. You'll find links to import your data on the tracker's <b>Preferences</b> page.
			</p>
			
			<h3>
				Why isn't Tracktacular free?
			</h3>
			
			<p>
				In order to pay for development, bandwidth, and server maintenance, we need a source of income. Traditionally, 
			 	web sites resort to targetted advertising to recoup these costs. Unfortunately, targetted advertising simply isn't 
			 	an option for us, because it would require us to share your personal data with advertising companies in order for it 
			 	to be effective. 
			 	<br/><br/>
			 	Selling or sharing your data to others is strictly against our 
			 	
			 	<s:link beanclass="com.tracktacular.web.page.PrivacyPolicyActionBean">
					Privacy Policy
				</s:link>, so instead 
			 
			 	we implemented a subscription service. As an added benefit, a subscription service keeps our customer base smaller, 
			 	so we can focus on improving the experience for a smaller group of people.
			</p>
			
			<h3>
				How much does it cost to use Tracktacular?
			</h3>
			
			<p>
			 	You can try Tracktacular free for one month, with only a username, password, and an email address (no credit card of
			 	any kind). Afterwards, there is a <b>$10/Month</b> subscription plan. However, you can reduce or even eliminate your entire subscription fee with our 
			 	referral system. 
			</p>
			
			<h3>
				How do referrals work?
			</h3>
			
			<p>
			 	Tracktacular has a generous referral system that will grant you a <b>20%</b> discount, per referral, for a maximum discount 
			 	of <b>100%</b> of your subscription fee. The discount will be active for the entire length of time that your referral is a 
			 	paying member.  
			 	
			 	<br/><br/>
			 	That means if you sign up 5 people who become subscribing members, your subscription will be completely 
			 	free. If you sign up more than 5 people, you'll simply earn insurance that your subscription will remain free if any 
			 	of your original 5 members cancel their subscription. 
			 	<br/><br/>
			 	
			 	Additionally, the people you refer will earn a <b>50%</b> discount on their first payment automatically.
			 	
			 	<br/><br/>
			 	You can find your referral link to share with others on your 
			 	
			 	<s:link beanclass="com.tracktacular.web.page.account.AccountActionBean">
					Account
				</s:link> 
				
				 page.
			</p>
			
			<h3>
				How do I get started?
			</h3>
			
			<p>
			 	If you don't have an account, 
			 	
			 	<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
					Sign up
				</s:link> 
				
				 and try it! You can try Tracktacular for free for one month, without 
			 	having to enter a credit card of any kind. If you are ambitious, you can even start getting referrals before your 
			 	trial period is over. 
			 	
			 	<br/><br/>
			 	
			 	Once you've signed up, head over to the 

				<s:link beanclass="com.tracktacular.web.page.GettingStartedActionBean">
					Getting Started
				</s:link> 
				
				page for more instructions, or 
			 	pick your favorite tracker and play around with it. Each tracker has a tutorial to help you get started. Don't get 
			 	overwhelmed, although there is a lot you can do, just track as much or as little as you want at first and take it 
			 	from there.
			</p>
			
			<h3>
				What if I have more questions?
			</h3>
			
			<p>
				Feel free to <a href="mailto:webmaster@tracktacular.com">Email</a> us anytime, and we'll respond as quickly as we can.
			</p>
			
			<c:if test="${!actionBean.context.loggedIn}">
				<p class="center text" style="margin-bottom: -15px; margin-top: 25px;">
					<s:link beanclass="com.tracktacular.web.page.account.JoinActionBean">
						<img src="/static/tracktacular/img/page/try.png"/>
					</s:link>
				</p>
			</c:if>
		</div>		
			
	</s:layout-component>
	
</s:layout-render>