	
	{! Need to add name slug to tags because duplicates appear !}
	<div id="ratings{@when test="'{sortProperty}' == 'tag'"}-{nameSlug}{/when}-{id}" class="item-leader-right center">
	
		{^publicMode}
		
			{?completed}
				<div class="editable" 
					data-editable-success-ids="ratings{@when test="'{sortProperty}' == 'tag'"}-{nameSlug}{/when}-{id}"
					data-editable-select-options="{ratings|json}" 
					data-editable-select-value="{rating}" 
					data-editable-id="{id}" 
					data-editable-property="{objectName}.rating">
					{>rating:rating/}
				</div>
			{:else}
				<span class="icon-s s-question editable" 
					data-editable-success-ids="ratings{@when test="'{sortProperty}' == 'tag'"}-{nameSlug}{/when}-{id}"
					data-editable-select-options="{ratings|json}" 
					data-editable-id="{id}" 
					data-editable-property="{objectName}.rating"></span>
			{/completed}
			
			{+ratingControlContent/}
			
		{:else}
		
			{?completed}
				{>rating:rating/}
			{:else}
				<span class="icon-s s-question"></span> 
			{/completed}
			
			{+ratingControlContent/}
			
		{/publicMode}
		
	</div>