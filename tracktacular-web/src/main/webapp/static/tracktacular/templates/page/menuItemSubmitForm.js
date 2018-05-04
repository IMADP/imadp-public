	
	<div class="menu-option">
		
		{?confirm}
		
			<a href="#" class="to-toggle" data-toggle-target-next="true">
				<span class="icon-s {icon}"></span> {itemTitle}
			</a>
			<div class="menu-confirmation">
				<span>Are you sure?</span>				
				<a href="#" class="to-menu-item-submit-form" 
					data-menu-item-submit-form-scroll-to-id="{scrollToId}"
					data-menu-item-submit-form-success-ids="{successIds}">
					
					<div class="none">
						{+menuItemSubmitFormBody/}
					</div>
					
					Yes
				</a> / {~s}
				<a href="#" class="to-toggle" data-toggle-target-parent="true">No</a>				
			</div>			
			
		{:else}
		
			<a href="#" class="to-menu-item-submit-form" 
				data-menu-item-submit-form-scroll-to-id="{scrollToId}"
				data-menu-item-submit-form-success-ids="{successIds}">
				
				<div class="none">
					{+menuItemSubmitFormBody/}
				</div>
		
				<span class="icon-s {icon}"></span> {itemTitle}
			</a>
			
		{/confirm}
		
	</div>
	