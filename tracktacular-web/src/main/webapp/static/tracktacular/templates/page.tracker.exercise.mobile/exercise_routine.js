
			
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="exercise_routineMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{name}</div>
		    	 	<div class="subtitle">{description}</div>
		    	 	<div class="subscript">
		    	 		
		    	 	</div>
		    	 	{>notes/}
    	 		</div>
    	 		<div class="item-right">
    	 			{>notesButton/}		
			    </div>
			</div>	
		</li>
		
		<li class="center ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
			<div class="center ui-btn-inner ui-li">
				<div class="ui-btn-text">
					<a href="{viewRoutineUrl}" class="center ui-link-inherit">
						<div class="title">{workoutCount} {@when test="{workoutCount} == 1"} Workout {:else} Workouts {/when}</div>
						{>persistableStateDetails/}
					</a>
				</div>
				<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
				
			</div>
		</li>
        	
    </ul>