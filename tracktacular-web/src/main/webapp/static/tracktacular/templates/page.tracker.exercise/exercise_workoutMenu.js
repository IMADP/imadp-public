
	{#. menuTitle="Workout Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Workout" icon="s-app-edit" template="exercise_workoutDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Clone Workout" icon="s-app-add" successIds="content-messages, content-body" scrollToId="main"}
			{>menuItemSubmitForm/}
	
			{<menuItemSubmitFormBody}
				<form action="{formAction}" method="post">
					<input type="hidden" name="routine" value="{routineId}" />
					<input type="hidden" name="workout" value="{id}" />
					<input type="hidden" name="_sourcePage" value="{sourcePage}" />
					<input type="hidden" name="_eventName" value="cloneWorkout" />
					{+menuItemSubmitFormParams/}
				</form>
			{/menuItemSubmitFormBody}	
		{/.}
				
		{#. itemTitle="Direct Link" itemLink=workoutLink}
			{>menuItemLink/}
		{/.}
		
		{#. itemTitle="Add Exercise" icon="s-app-add" template="exercise_exerciseDialog" contextId="{newExercise.id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?exercisesSortable}		
			{#. itemTitle="Sort Exercises" icon="s-sort" template="exercise_exerciseDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/exercisesSortable}
		
		{#. itemTitle="Delete Workout" objectName="workout" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
			
			{<menuItemSubmitFormParams}
				<input type="hidden" name="routine" value="{routineId}" />							
			{/menuItemSubmitFormParams}		
		{/.}
							
	{/menuBody}	