	
	{#. dialogTitle="Sort Episodes" event="sortEpisodes" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input id="sorted-episodes" type="hidden" name="sortedEpisodes" />
		
		<p>Drag and drop to sort your episodes/seasons.</p> 
		
		<ul class="sortable" data-sortable-input-target-id="sorted-episodes">
			{#episodes}
               <li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{title}</li>
            {/episodes}	
		</ul>
		
	{/dialogFormBody}