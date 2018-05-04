
	{#. dialogTitle="Save Objective" event="saveObjective" successIds="goal-{goalId}, content-messages"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="objective" value="{id}" />							
		<input type="hidden" name="objective.goal" value="{goalId}" />							
		
		Add a new objective 
		
		<label for="objective.name" class="primary required">Name</label>
		{#. object="objective.name" max="256"}{>inputText/}{/.}
		
		<label for="objective.targetDate" class="primary required">Target Date</label>  
		{#. object="objective.targetDate"}{>inputDate/}{/.}
		
		<label for="objective.notes" class="primary">Notes</label>
		{#. object="objective.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}