
		
	<li id="entry-{id}" class="ui-li ui-li-static li-item to-highlight"  data-highlight-target-id="entry-{id}">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="exercise_entryMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="subtitle">
	    	 	
	    	 		{?trackRepetitions}
						<div><b>Repetitions: </b> {repetitions}</div>
					{/trackRepetitions}
						
					{?trackWeight}
						<div><b>Weight: </b> {weight}</div>
					{/trackWeight}
						
					{?trackDistance}
						<div><b>Distance: </b>{distance}</div>
					{/trackDistance}
						
					{?trackDuration}
						<div><b>Duration: </b> 
							{?hours}{hours}h {/hours}
							{?minutes}{minutes}m {/minutes}
							{?seconds}{seconds}s {/seconds}
						</div>
					{/trackDuration}
						
					{?trackCalories}
						<div><b>Calories: </b> {calories}</div>
					{/trackCalories}
					
	    	 	</div>
	    	</div>
		    
		    <div class="item-right">
		    	
	        </div>
		        
		</div>
	 	
	</li>