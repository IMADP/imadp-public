
	{#. dialogTitle="Save Entry" event="saveEntry" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="routine" value="{routineId}" />							
		<input type="hidden" name="entry.exercise" value="{exerciseId}" />							
		<input type="hidden" name="entry" value="{id}" />							
			
		Add a new entry
		
		{?trackRepetitions}
			<label for="entry.repetitions" class="primary">Repetitions</label>
			{#. object="entry.repetitions" min="0"}{>inputNumber/}{/.}
		{/trackRepetitions}
		
		{?trackWeight}
			<label for="entry.weight" class="primary">Weight</label>
			{#. object="entry.weight" min="0"}{>inputNumber/}{/.}
		{/trackWeight}
			
		{?trackDuration}
			<label for="entry.hours" class="primary">Hours</label>
			{#. object="entry.hours" min="0"}{>inputNumber/}{/.}
		
			<label for="entry.minutes" class="primary">Minutes</label>
			{#. object="entry.minutes" min="0"}{>inputNumber/}{/.}
		
			<label for="entry.seconds" class="primary">Seconds</label>
			{#. object="entry.seconds" min="0"}{>inputNumber/}{/.}
		{/trackDuration}
			
		{?trackDistance}
			<label for="entry.distance" class="primary">Distance</label>
			{#. object="entry.distance" min="0"}{>inputNumber/}{/.}
		{/trackDistance}
			
		{?trackCalories}
			<label for="entry.calories" class="primary">Calories</label>
			{#. object="entry.calories" min="0"}{>inputNumber/}{/.}
		{/trackCalories}
		
	{/dialogFormBody}