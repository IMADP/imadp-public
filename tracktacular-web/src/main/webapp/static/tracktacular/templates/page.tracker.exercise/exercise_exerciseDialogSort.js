	
	{#. dialogTitle="Sort Exercises" event="sortExercises" successIds="content-messages, workout-{id}"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="routine" value="{routineId}" />
		<input id="sorted-exercises" type="hidden" name="sortedExercises" />
		
		Drag and drop to sort your exercises.
		
		<ul class="sortable" data-sortable-input-target-id="sorted-exercises">
			{#exercises}
               <li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
            {/exercises}	
		</ul>
	
	{/dialogFormBody}