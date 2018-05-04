
	{#. dialogTitle="Save Goal" event="saveGoal" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="goal" value="{id}" />							
		
		Add a new goal
		
		<label for="goal.name" class="primary required">Name</label>
		<label for="goal.name" class="secondary">Enter a short nickname for this goal (ie: Weight Loss, Save Money)</label>
		{#. object="goal.name" max="35"}{>inputText/}{/.}
		
		<label for="goal.description" class="primary required">Description</label>
		<label for="goal.description" class="secondary">Enter a specific description of your goal (ie: Lose 15 pounds by September)</label>
		{#. object="goal.description" max="256"}{>inputText/}{/.}
		
		<label for="goal.startDate" class="primary required">Start Date</label>  
		{#. object="goal.startDate"}{>inputDate/}{/.}
		
		<label for="goal.targetDate" class="primary required">Target Date</label>  
		{#. object="goal.targetDate"}{>inputDate/}{/.}
			
		<label for="goal.notes" class="primary">Notes</label>
		{#. object="goal.notes"}{>inputNotes/}{/.}
				
	    <div style="padding-top: 15px">
			How would like to track your progress for this goal?
		</div>
		
		<div style="padding-top: 15px">
			{#. object="goal.progressTracker" value="TIME"}{>inputRadio/}{/.}
			<label for="goal.progressTracker.TIME" style="display:inline;"> Automatically based on elapsed time </label>
		</div>
		
		<div style="padding-top: 15px">
			{#. object="goal.progressTracker" value="OBJECTIVE"}{>inputRadio/}{/.}
			<label for="goal.progressTracker.OBJECTIVE" style="display:inline;"> Calculated by objectives completed </label> 
		</div>
		
		<div style="padding-top: 15px">
			{#. object="goal.progressTracker" value="MANUAL"}{>inputRadio/}{/.}
			<label for="goal.progressTracker.MANUAL" style="display:inline;"> Manual progress entry </label>
		</div>
		
		<label for="goal.progress" class="primary">Manual Progress</label>  
		<label for="goal.progress" class="secondary">Percentage only used if manual progress entry was selected</label>
		{#. object="goal.progress" min="0" max="100"}{>inputNumber/}{/.}
		
	{/dialogFormBody}