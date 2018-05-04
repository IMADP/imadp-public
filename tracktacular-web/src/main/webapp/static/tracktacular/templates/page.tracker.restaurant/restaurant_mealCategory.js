	
	<div id="meal-category-{nameSlug}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{name}
				</h3>
				<h4 style="padding-left: 2px;">
					{mealCount} {@when test="{mealCount} == 1"} Meal {:else} Meals {/when}
				</h4>
            </div>
             
        </div>	
        
    	{#meals}
    	
        	{! Meal !}
			<div class="cf" style="padding: 15px 45px 0 65px" id="meal-{id}">
					
				<div class="left icons-2 align-right" style="padding-top: 3px;">			
					{>notesButton/}
					{>menuButton/}
				</div>
				
				<div class="left">
				
					<div class="item-leader cf" style="width: 470px;">	
								
						<div class="item-leader-left title" style="max-width: 340px;">
							{#. objectName="meal" paramName="name"}
								{>editable/}
							{/.}	
						</div>
							
						<div class="item-leader-right center">
							{?completed}
								{>rating:rating/}
							{:else}
								<span class="icon-s s-question"></span> 
							{/completed}
						</div>
						
					</div>
					
					<div class="subtitle" style="margin-left: 10px; max-width: 470px;">
						{restaurantName} in {restaurantCity}, {restaurantState}
					</div>
					
					{>notes/}
					
				</div>
				
				{>restaurant_mealCategoryMenu/}
				
			</div>
        {/meals}
	
	</div>
