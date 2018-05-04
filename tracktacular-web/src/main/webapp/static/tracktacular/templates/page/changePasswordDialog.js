	
	{#. dialogTitle="Change Password" event="changePassword" successIds="content-messages"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<div>Change your Tracktacular password here.</div>
	        
        <label for="credentialsPasswordChange.oldPassword" class="primary required">Old Password</label>  
	    <input type="password" id="credentialsPasswordChange.oldPassword" name="credentialsPasswordChange.oldPassword"  />	
		<div id="credentialsPasswordChange.oldPassword-error" class="none error"></div> 
		
		<label for="credentialsPasswordChange.credentialsPassword.password" class="primary required">New Password</label>  
	    <input type="password" id="credentialsPasswordChange.credentialsPassword.password" name="credentialsPasswordChange.credentialsPassword.password"  />	
		<div id="credentialsPasswordChange.credentialsPassword.password-error" class="none error"></div> 
		
		<label for="credentialsPasswordChange.credentialsPassword.confirmPassword" class="primary required">Confirm New Password</label>  
	    <input type="password" id="credentialsPasswordChange.credentialsPassword.confirmPassword" name="credentialsPasswordChange.credentialsPassword.confirmPassword"  />	
		<div id="credentialsPasswordChange.credentialsPassword.confirmPassword-error" class="none error"></div> 
		
	{/dialogFormBody}