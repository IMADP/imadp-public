			
	<article class="item" id="workout-{id}">
			
		<header>
		 
			{>menuButton/}
			
			{?exercisesSortable}
			
				{#. targetId="exercises-{id}" collapsed=workoutCollapsed}
	    			{>toggleCollapse/}  {~s}
	    		{/.}
	    		
	    		{<toggleCollapseForm}
	    			<form action="{formAction}" method="post">
						<input type="hidden" name="workout" value="{id}" />
						<input type="hidden" name="_eventName" value="toggleWorkoutCollapse" />
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
					</form>
				{/toggleCollapseForm}	
				
			{/exercisesSortable}
			
			{>notesButton/} 
    		
		</header>
				
		<h3 class="center">	
			{#. objectName="workout" paramName="name" successIds="journal-chart-{id}"}
				{>editable/}
			{/.} 			
		</h3>  
		
		<h4 class="center">
			{date}
		</h4> 	
		
		{>notes/} 
				
		<div id="exercises-{id}" class="{?workoutCollapsed}none{/workoutCollapsed}">		
			{#exercises}
	            {>exercise_exercise/}
	        {/exercises}
		</div>
	        	
		{>persistableStateDetails/}
		
		{>exercise_workoutMenu/}		
				
	</article>
