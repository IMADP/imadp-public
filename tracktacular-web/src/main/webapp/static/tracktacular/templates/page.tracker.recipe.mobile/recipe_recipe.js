	
		
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="recipe_recipeMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{name}</div>
		    	 	<div class="subtitle">{description}</div>
    	 		</div>
    	 		<div class="item-right">
    	 				
			    </div>
			</div>	
		</li>
		
		<li class="ui-li ui-li-static li-item">
			
	 		<div class="title center"><b>Ingredients</b></div>
			
			{#ingredientItems}
				<ul >
					{?name}
						<lh><div style="padding-top: 10px"><strong>{name}</strong></div></lh>
					{/name}							
					{#items}
						<li style="list-style: disc; padding: 2px 0;">{.}</li>
					{/items}
				</ul>				
			{/ingredientItems}
			
			<div class="title center" style="padding-top:15px;"><b>Directions</b></div>
			
			{#directionItems}
				<ol >	
					{?name}
						<lh><div style="padding-top: 10px"><strong>{name}</strong></div></lh>
					{/name}							
					{#items}
						<li style="list-style: decimal; padding: 2px 0;">{.}</li>
					{/items}
				</ol>				
			{/directionItems}
			
			{#. display="true"}
	    		{>notes/}	
			{/.}
				
			{>persistableStateDetails/}
		 	
		</li>
        	
    </ul>