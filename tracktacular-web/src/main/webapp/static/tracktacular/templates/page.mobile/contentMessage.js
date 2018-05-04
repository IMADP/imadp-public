	
	{?messagesFound}
		<article id="{messageLevel}-messages" class="bold message-{messageLevel} item ui-body ui-body-d ui-corner-all">
	    	
	    	<div class="item-wrapper">
	    	
			    <div class="item-left">
			    	
				</div>
				
			    <div class="item-center">
		    	 	{#messages}
			    		<div>{.}</div>
			    	{/messages}
			    </div>
			    
			    <div class="item-right">
			   		<a href="#" title="Close Message" class="to-toggle icon-button ui-btn ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-hover-c ui-btn-up-c"
						data-toggle-target-id="{messageLevel}-messages">
						<span class="ui-btn-inner">
							<span class="ui-btn-text">
								<span class="icon-s s-x"></span>
							</span>
						</span>
					</a>
		        </div> 
		        
			</div>
		<article>
	{/messagesFound}
	
	
	