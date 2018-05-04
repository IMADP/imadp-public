	<article class="item" id="recipe-{id}" >
			
		<header>
		
			{>menuButton/}
			
			{?recipeCollapsed}
				{#. targetId="recipe-content-{id}" collapsed="true"}
	    			{>toggleCollapse/}
	    		{/.}
    		{/recipeCollapsed}
			
			<span class="right">
    			{?publicMode}
    				{?favorite} <span class="icon-s s-star-on"></span> {:else} <span class="icon-s s-star-off"></span> {/favorite}
    			{:else}
    				{#. collapsed="{favorite}" collapsedIcon="s-star-on" uncollapsedIcon="s-star-off"}
	    				{>toggleCollapse/}  {~s}
		    		{/.}
		    		
		    		{<toggleCollapseForm}
		    			<form action="{formAction}" method="post">
							<input type="hidden" name="recipe" value="{id}" />
							<input type="hidden" name="_eventName" value="toggleRecipeFavorite" />
							<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						</form>
					{/toggleCollapseForm}	
    			{/publicMode}
    		</span>
    		
		</header>
				
		<h3 class="center">	
			{#. objectName="recipe" paramName="name"}
				{>editable/}
			{/.}
		</h3>  
		
		<h4 class="center">
			{#. objectName="recipe" paramName="description"}
				{>editable/}
			{/.}
		</h4>  
		
		<div id="recipe-content-{id}" class="recipe-content {?recipeCollapsed}none{/recipeCollapsed}" > 
			<div class="title center" style="padding-top:25px;"><b>Ingredients</b></div>
			
			<div class="recipe-ingredient-list" style="padding: 0 40px 0 40px;">
				{#ingredientItems}
					<ul >
						{?name}
							<lh><strong>{name}</strong></lh>
						{/name}							
						{#items}
							<li style="list-style: disc; padding: 2px 0;">{.}</li>
						{/items}
					</ul>				
				{/ingredientItems}
			</div>
		
			<div class="title center" style="padding-top:25px;"><b>Directions</b></div>
			
			<div class="recipe-direction-list" style="padding: 0 40px 0 40px;">
				{#directionItems}
					<ol >	
						{?name}
							<lh><strong>{name}</strong></lh>
						{/name}							
						{#items}
							<li style="list-style: decimal; padding: 2px 0;">{.}</li>
						{/items}
					</ol>				
				{/directionItems}
			</div>
		
			{#. display="true"}
				{>notes/}
			{/.}
			
		</div>
		
		{?tags}
			<div class="tags">
				{#tags}
					<span class="tag">{name}</span>
				{/tags}
			</div>
		{/tags}
		
		{>persistableStateDetails/}
		
		{>recipe_recipeMenu/}
				
	</article>