
	{#. dialogTitle="Save Goal" event="saveGoal" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="goal" value="{id}" />							
		<input type="hidden" id="goalProgress" name="goal.progress" value="{@when test="'MANUAL' == '{progressTracker}'"} {progress} {:else}0{/when}" />							
		
		Add a new goal
		
		<label for="goal.name" class="primary required">Name</label>
		<label for="goal.name" class="secondary">Enter a short nickname for this goal (ie: Weight Loss, Save Money)</label>
		{#. object="goal.name" max="35"}{>inputText/}{/.}
		
		<label for="goal.description" class="primary required">Description</label>
		<label for="goal.description" class="secondary">Enter a specific description of your goal (ie: Lose 15 pounds by September)</label>
		{#. object="goal.description" max="256"}{>inputText/}{/.}
		
		<div class="cf">
			<div class="half">
				<label for="goal.startDate" class="primary required">Start Date</label>  
				{#. object="goal.startDate"}{>inputDate/}{/.}
			</div>
			<div class="half">
				<label for="goal.targetDate" class="primary required">Target Date</label>  
				{#. object="goal.targetDate"}{>inputDate/}{/.}
			</div>
		</div>	
		
		<label for="goal.notes" class="primary">Notes</label>  
		{#. object="goal.notes"}{>inputNotes/}{/.}
				
	    <br/>
		<br/>
		
		How would like to track your progress for this goal?
		
		<br/>		
		<br/>		
		
		{#. object="goal.progressTracker" value="TIME"}{>inputRadio/}{/.}
		<label for="goal.progressTracker.TIME" class="secondary inline"> Automatically based on elapsed time </label>
		
		<br/>
		
		{#. object="goal.progressTracker" value="OBJECTIVE"}{>inputRadio/}{/.}
		<label for="goal.progressTracker.OBJECTIVE" class="secondary inline"> Calculated by objectives completed </label> 
		
		<br/>
		
		{#. object="goal.progressTracker" value="MANUAL"}{>inputRadio/}{/.}
		<label for="goal.progressTracker.MANUAL" class="secondary inline"> Manual progress entry </label>
		
		<br/>
		
		<div class="center"><span id="goalProgressDisplay"></span>%</div>
		
		<div id="slider" class="slider" 
			data-slider-min="0" 
			data-slider-max="100" 
			data-slider-step="1" 
			data-slider-input-target-id="goalProgress" 
			data-slider-display-target-id="goalProgressDisplay"></div> 
		
		<br/>
		
	{/dialogFormBody}