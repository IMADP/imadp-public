
	{#. dialogTitle="Save Exercise" event="saveExercise" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="routine" value="{routineId}" />							
		<input type="hidden" name="exercise.workout" value="{workoutId}" />							
		<input type="hidden" name="exercise" value="{id}" />							
			
		Add a new exercise 
		
		<label for="exercise.name" class="primary required">Name</label>
		{#. object="exercise.name" max="70"}{>inputText/}{/.}
		
		<label for="exercise.trackCalories" class="primary required" style="padding-bottom:5px;">
			What would you like to track for this exercise?
		</label>
		 
		<div id="exercise.trackCalories-error" class="none error" style="padding-bottom: 10px"></div> 
				
		{#. object="exercise.trackRepetitions"}{>inputCheckbox/}{/.}
		<label for="exercise.trackRepetitions" style="display: inline">Track <strong>Repetitions</strong></label>
		
		<br/>
		
		{#. object="exercise.trackWeight"}{>inputCheckbox/}{/.}
		<label for="exercise.trackWeight" style="display: inline">Track <strong>Weight</strong></label>
		
		<br/>
		
		{#. object="exercise.trackDistance"}{>inputCheckbox/}{/.}
		<label for="exercise.trackDistance" style="display: inline">Track <strong>Distance</strong></label>
		
		<br/>
		
		{#. object="exercise.trackDuration"}{>inputCheckbox/}{/.}
		<label for="exercise.trackDuration" style="display: inline">Track <strong>Duration</strong></label>
		
		<br/>
		
		{#. object="exercise.trackCalories"}{>inputCheckbox/}{/.}
		<label for="exercise.trackCalories" style="display: inline">Track <strong>Calories</strong></label>
		
		<label for="exercise.notes" class="primary">Notes</label>  
		{#. object="exercise.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}