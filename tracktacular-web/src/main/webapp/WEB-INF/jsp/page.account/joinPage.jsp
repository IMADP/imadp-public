<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Join Us"
	>

	<s:layout-component name="contentHeading">		
		Join	
	</s:layout-component>
	
	<s:layout-component name="contentBody">
	
		<section>
		
			<s:form class="dialog-form" beanclass="com.tracktacular.web.page.account.JoinActionBean">
				<div class="box-shadow">
					
					<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
						<span class="left ui-dialog-title" id="ui-dialog-title-2">Join</span>
					</div>
					
					<div class="dialog-form-body">
						<div class="center">
							Join Tracktacular for a free 30 day trial and start organizing your life!
						</div>
				
						<s:errors />
			
						<div class="cf">
							<div class="half">
								<s:label class="primary required" for="account.credentialsUsername.username">Username</s:label>				
								<i:out><s:text id="account.credentialsUsername.username" name="account.credentialsUsername.username" value="${actionBean.account.credentialsUsername.username}"/></i:out>		
							</div>
							<div class="half">
								<s:label class="primary required" for="account.credentialsEmail.email">Email</s:label>				
								<i:out><s:text id="account.credentialsEmail.email" name="account.credentialsEmail.email" value="${actionBean.account.credentialsEmail.email}"/></i:out>		
							</div>
						</div>		
								
						<div class="cf">
							<div class="half">
								<s:label class="primary required" for="account.credentialsPassword.password">Password</s:label>				
								<i:out><s:password id="account.credentialsPassword.password" name="account.credentialsPassword.password" value=""/></i:out>		
							</div>
							<div class="half">
								<s:label class="primary required" for="account.credentialsPassword.confirmPassword">Confirm Password</s:label>				
								<i:out><s:password id="account.credentialsPassword.confirmPassword" name="account.credentialsPassword.confirmPassword" value=""/></i:out>		
							</div>
						</div>		
								
						<s:label for="account.verification" class="primary">Verification</s:label>
						<s:label for="account.verification" class="secondary">Please leave this field blank for human verification</s:label>
						<s:textarea id="account.verification" name="account.verification" rows="1">${actionBean.account.verification}</s:textarea>
						
						<br/>
						<br/>
						
						<s:checkbox checked="true" id="account.acceptTerms" name="account.acceptTerms" />
						<s:label style="display:inline;" class="inline" for="account.acceptTerms">I have read and agree to the 
							<s:link beanclass="com.tracktacular.web.page.TermsAndConditionsActionBean">Terms &amp; Conditions</s:link>
							and
							<s:link beanclass="com.tracktacular.web.page.PrivacyPolicyActionBean">Privacy Policy</s:link>
						</s:label>
						
						<i:action name="joinAction"/>
							
					</div>
					
					<hr>
					
					<div class="dialog-form-footer cf">
						<div class="dialog-form-footer-button">
							<s:submit class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all" name="join" value="Submit" />
						</div>
					</div>
					
				</div>
			</s:form>
			
		</section>
			
	</s:layout-component>
	
</s:layout-render>