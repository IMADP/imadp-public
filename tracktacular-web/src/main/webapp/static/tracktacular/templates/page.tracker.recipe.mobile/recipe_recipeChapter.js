	
	<ul id="recipe-chapter-{id}" class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="recipe_recipeChapterMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{title}</div>
		    	 	<div class="subtitle">{description}</div>
		    	</div>
    	 		<div class="item-right">
    	 			
			    </div>
			</div>
				
		</li>
		
		{#recipes}
        	{>recipe_recipeSummary/}
        {/recipes}
		
	</ul>