	
	{?messagesFound}
		<div id="{messageLevel}-messages" class="message-box message-{messageLevel} cf">
			<div class="message-icon">
				<span class="icon-l l-{messageLevel}"></span>
			</div>
			<div class="message-content">
				{#messages}
		    		<div>{.}</div>
		    	{/messages}
			</div>
			<div class="message-close">
				<a href="#" title="Close Message" class="to-toggle icon-s s-x" data-toggle-target-id="{messageLevel}-messages"></a>
			</div>
		</div>
	{/messagesFound}