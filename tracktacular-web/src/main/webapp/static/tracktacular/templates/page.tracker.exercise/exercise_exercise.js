			
	<article class="item" id="exercise-{id}" style="padding-bottom: 0;">
			
		<header>		 
			{>menuButton/}
			{>notesButton/}    		
		</header>
				
		<h3 class="center" style="padding-bottom: 15px; margin-top: -10px;">	
			{#. objectName="exercise" paramName="name"}
				{>editable/}
			{/.} 			
		</h3>  
		
		{>notes/} 
				
		{?entries}
			
			<table class="striped-table">
				
				<tr>
					
					{^publicMode}
						<th>Entry</th>
					{/publicMode}
					
					<th>Timestamp</th>
					
					{?trackRepetitions}
						<th>Repetitions</th>
					{/trackRepetitions}
						
					{?trackWeight}
						<th>Weight</th>
					{/trackWeight}
						
					{?trackDistance}
						<th>Distance</th>
					{/trackDistance}
						
					{?trackDuration}
						<th>Duration</th>
					{/trackDuration}
						
					{?trackCalories}
						<th>Calories</th>
					{/trackCalories}						
				</tr>
			
				{#entries}
		            {>exercise_entry/}
		        {/entries}
		        
			</table>
		
		{/entries}	
		
		{>exercise_exerciseMenu/}	
				
	</article>
	