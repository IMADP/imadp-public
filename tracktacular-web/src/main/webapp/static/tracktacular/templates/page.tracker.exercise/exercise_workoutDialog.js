
	{#. dialogTitle="Save Workout" event="saveWorkout" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="routine" value="{routineId}" />							
		<input type="hidden" name="workout.routine" value="{routineId}" />							
		<input type="hidden" name="workout" value="{id}" />							
			
		Add a new workout
		
		<label for="workout.date" class="primary required">Date</label>  
		{#. object="workout.date"}{>inputDateTime/}{/.} 
		
		<label for="workout.name" class="primary required">Name</label>
		{#. object="workout.name" max="35"}{>inputText/}{/.}
		
		<label for="workout.notes" class="primary">Notes</label>
		{#. object="workout.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}