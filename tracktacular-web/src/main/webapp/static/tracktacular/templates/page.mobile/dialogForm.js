	
	{#contentBody}
		{#dialogFormData}
		
			<form class="dialog-form" action="{formAction}" method="post" 
				data-dialog-form-success-ids="{successIds}">
					
				<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
		
					<li class="ui-li ui-li-static li-item-header">
						<div class="item-wrapper">
							<div class="item-left"></div>
							<div class="item-center">
								<div class="title">{dialogTitle}</div>
							</div>
							<div class="item-right">	
								<a href="#" class="dialog-form-close icon-button ui-btn ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-up-c" data-menu-form-template="" data-menu-form-context-id="">
									<span class="icon-s s-x"></span>
								</a>
							</div>
						</div>
					</li>
					
					<li class="ui-li ui-li-static li-item" style="padding: 15px;">
			
						<div class="dialog-form-body">
						
							{+dialogFormBody/}
							
							<input type="hidden" name="_sourcePage" value="{sourcePage}" />
							<input type="hidden" name="_eventName" value="{event}" />
						
						</div>
						
						<article id="validation-errors" style="padding: 11px 0;" class="center none bold message-error item ui-body ui-body-d ui-corner-all">
							<div>There were errors with your submission</div>
						</article>
						
						<div style="margin: 15px 0 10px 0;" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-b">
							<span class="ui-btn-inner">
								<span id="dialog-form-submit-text" class="ui-btn-text">
									Submit
								</span>
								
								<img style="padding-left: 5px;" id="dialog-form-busy" class="none" 
										src="/static/tracktacular/img/page/busy-indicator-small.gif" alt="Loading"/>
							</span>
							<button class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all ui-state-hover ui-btn-hidden"></button>
						</div>
			
					</li>
					
				</ul>
			
			</form>
		
		{/dialogFormData}
	{/contentBody}