	
	{#. dialogTitle="Sort Items" event="sortItems" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="budget" value="{budgetId}" />
		<input id="sorted-items" type="hidden" name="sortedItems" />
		
		<p>Drag and drop to sort your items.</p> 
		
		<ul class="sortable" data-sortable-input-target-id="sorted-items">
			{#items}
               <li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
            {/items}	
		</ul>
				
	{/dialogFormBody}