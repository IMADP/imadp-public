	
	{#. dialogName="Sort Meals" event="sortMeals" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input id="sorted-meals" type="hidden" name="sortedMeals" />
		
		<p>Drag and drop to sort your meals.</p> 
		
		<ul class="sortable" data-sortable-input-target-id="sorted-meals">
			{#meals}
               <li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
            {/meals}	
		</ul>
		
	{/dialogFormBody}