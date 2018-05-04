	
	{#. dialogTitle="Sort Entries" event="sortEntries" successIds="content-messages, exercise-{id}"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="routine" value="{routineId}" />
		<input id="sorted-entries" type="hidden" name="sortedEntries" />
		
		Drag and drop to sort your entries. 
		
		<ul class="sortable" data-sortable-input-target-id="sorted-entries">
			{#entries}
               <li id="{id}" class="ui-state-default">
	               	<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
	               
					{time} {~s}
					
					{?trackRepetitions}
						- Reps: {repetitions}  {~s}
					{/trackRepetitions}
						
					{?trackWeight}
						- Weight: {weight}  {~s}
					{/trackWeight}
						
					{?trackDistance}
						- Distance: {distance}  {~s}
					{/trackDistance}
						
					{?trackDuration}
						- Duration: {hours}h {minutes}m {seconds}s {~s}
					{/trackDuration}
						
					{?trackCalories}
						- Calories: {calories} {~s}
					{/trackCalories}

               	</li>
            {/entries}	
		</ul>
				
	{/dialogFormBody}