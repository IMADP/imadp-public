	{#contentBody}
	
		<h2>Account Details</h2>		
		
		<div class="box-shadow preferences">		
	
			<h2>Credentials</h2>
		
			<section class="center">
			
				Your email is <strong>{email}</strong> {^emailNotVerified} <span style="color:green"><b>(Verified)</b></span> {/emailNotVerified}
				
				<br/>
				<br/>
		
				<a href="#" class="to-dialog-form dialog-form-button ui-state-default ui-priority-primary ui-corner-all" style="margin-right: 25px;" data-dialog-form-template="changeEmailDialog">
					Change Email
				</a>
				
				<a href="#" class="to-dialog-form dialog-form-button ui-state-default ui-priority-primary ui-corner-all" data-dialog-form-template="changePasswordDialog">
					Change Password
				</a>
				
				{?emailNotVerified}
				
					<a style="margin-left: 25px;" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all" href="{sendVerificationEmailUrl}">
						Resend Verification Email
					</a>
					
				{/emailNotVerified}
					
			</section>
			
		</div>
		
		<div class="box-shadow preferences">		
		
			<h2>Subscription</h2>
		
			<section class="center">
			
				{?expired}
					
					Your subscription period has <b>expired</b>. <br/>
					Subscribe now for unlimited access as a full fledged member! <br/> <br/>
					<a href="{subscribeUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">Subscribe</a>
					
				{:else}
				
					{?trialing}
					
						Your account is in <b>Trial Mode</b> until {subscriptionPeriodEnd}. <br/>
						You have <b>{subscriptionPeriodDaysRemaining} days</b> remaining for your free trial. <br/> <br/>
						<a href="{subscribeUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">Subscribe Now</a>
						
					{/trialing}
					
					{?cancelled}
						
						Your subscription has been <b>cancelled</b>. <br/>
						You have <b>{subscriptionPeriodDaysRemaining} days</b> until your subscription period ends on {subscriptionPeriodEnd}.
					
					{/cancelled}
					
					{?unpaid}
						
						Your subscription has been <b>unpaid</b>. <br/>
						You have <b>{subscriptionPeriodDaysRemaining} days</b> until your subscription period ends on {subscriptionPeriodEnd}.
					
					{/unpaid}
					
					{?pastDue}
						
						Your subscription is <b>past due</b>. <br/>
						You have <b>{subscriptionPeriodDaysRemaining} days</b> until your subscription period ends on {subscriptionPeriodEnd}.
					
					{/pastDue}
					
					{?active}
					
						{?lifetime}
					
							You have a <b>Lifetime</b> account subscription. Thanks for your support!
							
						{/lifetime}
				
						{?monthly}
						
							You have a <b>Monthly</b> account subscription. Thanks for your support! <br/>
							You have <b>{subscriptionPeriodDaysRemaining} days</b> remaining in the current billing cycle.
							
						{/monthly}
				
						{?annual}
						
							You have an <b>Annual</b> account subscription. Thanks for your support! <br/>
							You have <b>{subscriptionPeriodDaysRemaining} days</b> remaining in the current billing cycle.
							
						{/annual}
				
						{^lifetime}
							<br/>
							<br/>
							
							<a href="{unsubscribeUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">Cancel Your Subscription</a>
						{/lifetime}
												
					{/active}
					
				{/expired}
					
			</section>
			
		</div>
		<div class="box-shadow preferences">		
		
			<h2>Referrals</h2>
		
			<section class="center">
			
				<div class="center">
					You have <b>{activeReferralCount}</b>
					{@when test="{activeReferralCount} == 1"} active referral{:else} active referrals{/when} for a {~s}
					monthly discount of <b style="color:green;">{discountPercentage}%</b>.
				</div> 
				
				{?referrals}
				
					<article class="item" style="padding: 0;margin-bottom: 25px; margin-top:20px;">
						<table class="striped-table" style="width: 100%;margin-left: 0;">
							<tr>
								<th>Referral</th>
								<th>Date</th>
								<th>Status</th>
								<th>Discount</th>
							</tr>
							
							{#referrals}
								<tr>
									<td class="center">
										{index}
								   	</td>
								   	<td class="center">
										{timeCreatedDate}
								   	</td>
									<td class="center">
										{subscriptionStatus}
								   	</td>
									<td class="center">
										{?discount}
											<span class="icon-s s-check"></span>
										{:else}
											<b>-</b>
										{/discount}
								   	</td>
								</tr>
							{/referrals}
						</table>
					</article>
				{/referrals}
				
				<article class="center item" style="background: #E4ECF1; margin-top: 20px; padding: 15px;">
					Earn <b>20%</b> off for each member who subscribes through this link. <br/>
					Referrals will receive a <b>50%</b> discount on their first payment as well.
					
					<div class="cf" style="margin-top:15px;">
						<div class="left" style="width:300px; margin-left: 20px;">
						
							<input type="text" style="width: 300px; text-align: center; padding: 5px; color: #045299;" 
								value="https://www.tracktacular.com{referralUrl}" readonly onClick="this.select();"/>
						
						</div>
						<div class="right" style="width:100px; margin-right: 10px;">
							
							<a href="https://twitter.com/share" class="twitter-share-button" data-size="large"  
								data-url="https://www.tracktacular.com{referralUrl}" data-text="Like to keep track of everything in your life? Try @Tracktacular:" data-count="none">Tweet</a>
								<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
						
						</div>
					</div>
						
				</article> 
				
			</section>
			
			</div>
			
			<div class="box-shadow preferences">		
	
				<h2>Tracker Settings</h2>
				
				<section>
						
					<form action="{formAction}" method="post">			
						<input type="hidden" name="preferences" value="preferences"/>
				
							<div class="center">
								<b>Enabled:</b> Displays the tracker in the navigation links to the left.<br/>
								<b>Tutorial:</b> Displays a tutorial box at the bottom of tracker pages.<br/>
								<b>Public:</b> Allows members and non-members to see your tracker data.<br/>
							</div>
							
							<article class="item" style="padding: 0;margin-bottom: 5px; margin-top:25px;">
								<table class="striped-table" style="width: 100%;margin-left: 0;">
									<tr>
										<th>Tracker</th>
										<th>Enabled</th>
										<th>Tutorial</th>
										<th>Public</th>
									</tr>
									
									{#trackers}
										<tr>
											<td class="left" style="padding: 5px 10px;">
												<span class="icon-s s-{trackerName}" style="margin: 0 5px;"></span> {trackerTitle}
											</td>
											<td class="center">
												<input id="{trackerEnabledName}" 
										   			name="{trackerEnabledName}"
										   			{?trackerEnabled}checked="checked"{/trackerEnabled}  
										   			value="true" type="checkbox" > {~s}
										   	</td>
										   	<td class="center">
												<input id="{trackerTutorialName}" 
										   			name="{trackerTutorialName}"
										   			{?trackerTutorial}checked="checked"{/trackerTutorial}  
										   			value="true" type="checkbox" > {~s}
										   	</td>
											<td class="center">
												<input id="{trackerPublicName}" 
										   			name="{trackerPublicName}"
										   			{?trackerPublic}checked="checked"{/trackerPublic}  
										   			value="true" type="checkbox" > {~s}
										   	</td>
										</tr>
									{/trackers}
								</table>
							</article>
							
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						<input style="margin-top: 25px; margin-bottom: 0;" class="preferences-save dialog-form-button ui-state-default ui-priority-primary ui-corner-all" name="savePreferences" value="Save Preferences" type="submit">				
					</form>
				
				</section>
			
			</div>
			
			<div class="box-shadow preferences">		
			
				<h2>Email Settings</h2>
				
				<section>
						
					<form action="{formAction}" method="post">			
						<input type="hidden" name="preferences" value="preferences"/>
				
							<div class="center">
								<input id="preferences.emailAlerts" name="preferences.emailAlerts" 
									{?emailAlerts}checked="checked"{/emailAlerts} value="true" type="checkbox" > {~s}
								<label for="preferences.emailAlerts">
									Send my Tracktacular Report via email when tracker alerts are found.
								</label>
								{?emailNotVerified}
									<div class="subscript">(You must have a verified email account to receive Tracktacular Reports)</div>
								{/emailNotVerified}
							</div>
							
							<div class="center" style="margin-top: 15px;">
								<input id="preferences.blogNotification" name="preferences.blogNotification" 
									{?blogNotification}checked="checked"{/blogNotification} value="true" type="checkbox" > {~s}
								<label for="preferences.blogNotification">
									Send me notifications via email for upcoming blog entries.
								</label>
							</div>
							
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						<input style="margin-top: 25px; margin-bottom: 0;" class="preferences-save dialog-form-button ui-state-default ui-priority-primary ui-corner-all" name="savePreferences" value="Save Preferences" type="submit">				
					</form>
				
				</section>
			
			</div>
			
			<div class="box-shadow preferences">		
			
				<h2>Exporting Data</h2>
					
				<section class="center">
					You can export your data to keep a personal backup, or for use in other programs such as Microsoft Excel. {~s}
					Click here to export your tracker data as a csv file: 
					
					<br/>
					<br/>
					
					<form action="{formAction}" method="post">
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						<input type="hidden" name="_eventName" value="exportData" />
						<input type="hidden" name="exportAction.formToken" value="{formToken}" />
						<input type="hidden" name="exportAction.sessionToken" value="{sessionToken}" />
						<input type="submit" name="exportData" value="Export Data" 
							class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all"/>
					</form>
					
				</section>
				
			</div>
			
			<!-- Google Code for Account Conversion Page -->
			<script type="text/javascript">
			/* <![CDATA[ */
			var google_conversion_id = 992011214;
			var google_conversion_language = "en";
			var google_conversion_format = "3";
			var google_conversion_color = "ffffff";
			var google_conversion_label = "WAMbCJqyxQcQzseD2QM";
			var google_conversion_value = 0;
			/* ]]> */
			</script>
			<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
			</script>
			<noscript>
			<div style="display:inline;">
			<img height="1" width="1" style="border-style:none;" alt="" src="//www.googleadservices.com/pagead/conversion/992011214/?value=0&amp;label=WAMbCJqyxQcQzseD2QM&amp;guid=ON&amp;script=0"/>
			</div>
			</noscript>
			
	{/contentBody}
	