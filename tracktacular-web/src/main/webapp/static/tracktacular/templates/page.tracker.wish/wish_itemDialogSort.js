	
	{#. dialogTitle="Sort Items" event="sortItems" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input id="sorted-items" type="hidden" name="sortedItems" />
		
		Drag and drop to sort your items. 
		
		<ul class="sortable" data-sortable-input-target-id="sorted-items">
			{#items}
               <li id="{id}" class="ui-state-default">
	               	<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>	               
					{name} 
               	</li>
            {/items}	
		</ul>
				
	{/dialogFormBody}