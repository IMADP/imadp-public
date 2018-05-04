	
	{@when test="{collapsed}"}			
		<a href="#" class="icon-button ui-btn ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-up-c to-toggle"
			data-toggle-css="icon-s {?uncollapsedIcon} {uncollapsedIcon} {:else} s-minus {/uncollapsedIcon}"
			data-toggle-target-id="{targetId}" 
			data-toggle-prevent-form="{preventForm}" 
			data-toggle-form-success-ids="{successIds}"
			data-toggle-linked-target-id="{linkedTargetId}">
				{+toggleCollapseForm/}
				<span class="icon-s {?collapsedIcon} {collapsedIcon} {:else} s-plus {/collapsedIcon}"></span>
		</a>
	{:else}
		<a href="#" class="icon-button ui-btn ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-up-c to-toggle"
			data-toggle-css="icon-s {?collapsedIcon} {collapsedIcon} {:else} s-plus {/collapsedIcon}"
			data-toggle-target-id="{targetId}" 
			data-toggle-prevent-form="{preventForm}" 
			data-toggle-form-success-ids="{successIds}"
			data-toggle-linked-target-id="{linkedTargetId}">
				{+toggleCollapseForm/}
				<span class="icon-s {?uncollapsedIcon} {uncollapsedIcon} {:else} s-minus {/uncollapsedIcon}"></span>
		</a>
	{/when}	