	
	{@when test="{collapsed}"}			
		<a href="#" class="to-toggle icon-s {?collapsedIcon} {collapsedIcon} {:else} s-plus {/collapsedIcon}" title="Expand/Collapse"
			style="{style}"
			data-toggle-css="to-toggle icon-s {?uncollapsedIcon} {uncollapsedIcon} {:else} s-minus {/uncollapsedIcon}"
			data-toggle-target-id="{targetId}" 
			data-toggle-target-class="{targetClass}" 
			data-toggle-prevent-form="{preventForm}" 
			data-toggle-form-success-ids="{successIds}"
			data-toggle-linked-target-id="{linkedTargetId}">
				{+toggleCollapseForm/}
		</a>
	{:else}
		<a href="#" class="to-toggle icon-s {?uncollapsedIcon} {uncollapsedIcon} {:else} s-minus {/uncollapsedIcon}" title="Expand/Collapse"
			style="{style}"
			data-toggle-css="to-toggle icon-s {?collapsedIcon} {collapsedIcon} {:else} s-plus {/collapsedIcon}"
			data-toggle-target-id="{targetId}" 
			data-toggle-target-class="{targetClass}" 
			data-toggle-prevent-form="{preventForm}" 
			data-toggle-form-success-ids="{successIds}"
			data-toggle-linked-target-id="{linkedTargetId}">
				{+toggleCollapseForm/}
		</a>
	{/when}	