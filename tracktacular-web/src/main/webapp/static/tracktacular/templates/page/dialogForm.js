
	{#contentBody}
		{#dialogFormData}
	
			<form class="dialog-form" action="{formAction}" method="post" 
				data-dialog-form-success-ids="{successIds}">
			
				<div class="box-shadow">
					
					<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
						<span class="left ui-dialog-title" id="ui-dialog-title-2">{dialogTitle}</span>
						<a href="#" class="ui-dialog-titlebar-close ui-corner-all dialog-form-close" role="button">
							<span class="right icon-s s-x"></span>
						</a>
					</div>
					
					<div class="dialog-form-body">
					
						<div id="validation-errors" class="message-box message-error cf none">
							<div class="message-icon">
								<span class="icon-l l-error"></span>
							</div>
							<div class="message-content">
								<div>There were errors with your submission</div>
						    </div>
						</div>
						
						{+dialogFormBody/}
						
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						<input type="hidden" name="_eventName" value="{event}" />
					
					</div>
					
					<hr>
					
					<div class="dialog-form-footer cf">
						<div class="dialog-form-footer-img">
							<img id="dialog-form-busy" class="none" alt="Loading"
								src="/static/tracktacular/img/page/busy-indicator-form.gif" >
						</div>
						<div class="dialog-form-footer-button">
							<button class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">
								Submit
							</button>
						</div>
					</div>
					
				</div>
			</form>
		
		{/dialogFormData}
	{/contentBody}