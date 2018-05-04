
	{#. dialogTitle="Save Routine" event="saveRoutine" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="routine" value="{id}" />							
			
		Add a new routine
		
		<label for="routine.startDate" class="primary required">Start Date</label>  
		{#. object="routine.startDate"}{>inputDate/}{/.} 
		
		<label for="routine.name" class="primary required">Name</label>
		{#. object="routine.name" max="35"}{>inputText/}{/.}
		
		<label for="routine.description" class="primary">Description</label>
		{#. object="routine.description" max="256"}{>inputText/}{/.}
		
		<div style="margin-top: 25px">
			<label for="routine.alertRecurrence.length" class="primary inline">Create an alert if a workout hasn't been entered in {~s}</label>
			{#. object="routine.alertRecurrence.length" min="0" style=" width: 60px;"}{>inputNumber/}{/.}
			{#. object="routine.alertRecurrence.type" options=recurrenceTypes optionName="name" optionValue="value"}{>inputSelect/}{/.}
		</div>
		
		<label for="routine.notes" class="primary">Notes</label>
		{#. object="routine.notes"}{>inputNotes/}{/.}
				
	{/dialogFormBody}