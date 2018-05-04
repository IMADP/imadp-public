
	{! Restaurant Meal !}
	<div class="cf" style="padding: 10px 0 0 0" id="restaurant-meal-{id}">
			
		<div class="left icons-2 align-right" style="padding-top: 3px;">			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}
			{>menuButton/}
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 432px;">	
						
				<div class="item-leader-left title" style="max-width: 305px;">
					<h4>
						{#. objectName="meal" paramName="name"}
							{>editable/}
						{/.}
					</h4>	
				</div>
					
				{#. objectName="meal"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
		</div>
		
		{>restaurant_mealMenu/}
		
	</div>