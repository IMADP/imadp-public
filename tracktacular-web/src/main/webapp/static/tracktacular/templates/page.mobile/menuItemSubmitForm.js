	
	<li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
		<div class="ui-btn-inner ui-li">
			<div class="ui-btn-text">
				<a href="#" class="to-menu-item-submit-form ui-link-inherit" data-menu-item-submit-form-success-ids="{successIds}"
					{?confirm} data-menu-item-submit-form-confirm="true" {/confirm}>
				
					<div class="none">
						{+menuItemSubmitFormBody/}
					</div>
					
					{itemTitle}
					
					<img style="padding-left: 5px;" class="menu-busy-indicator none" 
						src="/static/tracktacular/img/page/busy-indicator-small.gif" alt="Loading"/>
					
				</a>
			</div>
			<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
		</div>
	</li>