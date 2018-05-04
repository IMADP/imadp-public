	
	{#. dialogTitle="Reset Password" event="resetPassword" successIds="content-messages"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<div>
			Trouble signing in? We recommend first trying to login with either your username or your email address. {~s}
			If you can't login with either, you can have your password reset and sent to the email address associated with your account.
		</div>
		
		<label for="credentialsResetPassword.email" class="primary required">Email</label>  
	    <input type="text" id="credentialsResetPassword.email" name="credentialsResetPassword.email"  />	
		<div id="credentialsResetPassword.email-error" class="none error"></div> 
		
		<input type="hidden" name="resetPasswordAction.formToken" value="{formToken}" />
		<input type="hidden" name="resetPasswordAction.sessionToken" value="{sessionToken}" />
		
	{/dialogFormBody}