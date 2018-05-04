	
	{#. dialogTitle="Change Email" event="changeEmail" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<div>
			Change your Tracktacular email address here. <br/>
	        Please note you'll need to re-verify after it's been changed.
        </div>
        
        <label for="credentialsEmail.email" class="primary required">Email</label>  
	    <input type="text" id="credentialsEmail.email" name="credentialsEmail.email"  />	
		<div id="credentialsEmail.email-error" class="none error"></div> 
		
	{/dialogFormBody}