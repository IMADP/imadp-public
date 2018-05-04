
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow" id="workout-{id}" >
		
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="exercise_workoutMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{name}</div>
		 		 	<div class="subtitle">{date}</div>
		 		</div>
		 		<div class="item-right">
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
				</div>
			</div>	
		</li>
		
		<li id="exercises-{id}"  class="{?workoutCollapsed} none {/workoutCollapsed}">
			<ul class="container">
		
				{#exercises}
		            {>exercise_exercise/}		
		        {/exercises}
				
				{#notes}
					<li class="ui-li ui-li-static li-item">
						{#. display="true"}
				    		{>notes/}	
						{/.}
					</li>
				{/notes}
		    
		    </ul>
	    
	    </li>
		
	</ul>
	