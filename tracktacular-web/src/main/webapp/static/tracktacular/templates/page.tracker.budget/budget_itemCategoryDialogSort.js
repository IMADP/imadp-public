	
	{#. dialogTitle="Sort Categories" event="sortItemCategories" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="budget" value="{budgetId}" />
		<input id="sorted-categories" type="hidden" name="sortedCategories" />
		
		<p>Drag and drop to sort your categories.</p> 
		
		{?root}
			<ul class="sortable" data-sortable-input-target-id="sorted-categories">
				{#categories}
	               <li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
	            {/categories}	
			</ul>
		{:else}
			<ul class="sortable" data-sortable-input-target-id="sorted-categories">
				{#neighboringCategories}
	               <li id="{neighborId}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{neighborName}</li>
	            {/neighboringCategories}	
			</ul>
		{/root}
		
	{/dialogFormBody}