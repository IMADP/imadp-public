	
	{#. dialogTitle="Sort Chapters" event="sortChapters" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input id="sorted-chapters" type="hidden" name="sortedChapters" />
		
		<p>Drag and drop to sort your chapters.</p> 
		
		<ul class="sortable" data-sortable-input-target-id="sorted-chapters">
			{#chapters}
               <li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{title}</li>
            {/chapters}	
		</ul>
		
	{/dialogFormBody}