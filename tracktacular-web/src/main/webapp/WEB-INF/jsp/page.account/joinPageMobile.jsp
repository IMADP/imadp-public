<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Join Us"
	>

	<s:layout-component name="contentHeading">		
		Join	
	</s:layout-component>
	
	<s:layout-component name="contentBody">
	
		<s:form beanclass="com.tracktacular.web.page.account.JoinActionBean" class="dialog-form" >
				
			<div class="dialog-form-body">
			
				<p>Join Tracktacular for a free 30 day trial and organize your life!</p>
			
				<s:errors />
				
				<i:action name="joinAction"/>
				<s:hidden name="mobile" value="true"/>
				
				<s:label class="primary required" for="account.credentialsUsername.username">Username</s:label>				
				<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-d">
					<s:text id="account.credentialsUsername.username" name="account.credentialsUsername.username" value="${actionBean.account.credentialsUsername.username}"/>		
				</div>
				
				<s:label class="primary required" for="account.credentialsEmail.email">Email</s:label>				
				<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-d">
					<s:text id="account.credentialsEmail.email" name="account.credentialsEmail.email" value="${actionBean.account.credentialsEmail.email}"/>		
				</div>
				
				<s:label class="primary required" for="account.credentialsPassword.password">Password</s:label>				
				<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-d">
					<s:password id="account.credentialsPassword.password" name="account.credentialsPassword.password" value=""/>	
				</div>
				
				<s:label class="primary required" for="account.credentialsPassword.confirmPassword">Confirm Password</s:label>				
				<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-d">
					<s:password id="account.credentialsPassword.confirmPassword" name="account.credentialsPassword.confirmPassword" value=""/>	
				</div>
							
				<s:label for="account.verification" class="primary">Verification</s:label>
				<s:label for="account.verification" class="secondary">Please leave this field blank for human verification</s:label>
				<div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-d">
					<s:text id="account.verification" name="account.verification">${actionBean.account.verification}</s:text>
				</div>
				
				<br/>
				
				<s:checkbox checked="true" id="account.acceptTerms" name="account.acceptTerms" />
				<s:label style="display:inline;" class="inline" for="account.acceptTerms">I agree to the 
					<s:link data-external="true" beanclass="com.tracktacular.web.page.TermsAndConditionsActionBean">Terms</s:link>
					and
					<s:link data-external="true" beanclass="com.tracktacular.web.page.PrivacyPolicyActionBean">Privacy Policy</s:link>
				</s:label>
				
				<div style="margin-top:15px" class="ui-submit ui-btn ui-shadow ui-btn-corner-all ui-btn-up-b">
					<span class="ui-btn-inner">
						<span class="ui-btn-text">Submit</span>
						<span class="ui-icon ui-icon-grid ui-icon-shadow"></span>
					</span>
					<s:submit class="ui-btn-hidden" name="join" value="Submit" />
				</div>
				
			</div>
		</s:form>	
			
	</s:layout-component>
	
</s:layout-render>