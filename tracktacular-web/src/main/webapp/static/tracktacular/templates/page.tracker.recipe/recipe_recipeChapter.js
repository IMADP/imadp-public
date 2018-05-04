	
	<div id="recipe-chapter-{id}" class="recipe-chapter-panel {?hidden}none{/hidden}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left">
            	{>menuButton/}
				
				{?recipesCollapsible}	
					{#. targetId="chapter-content-{id}" collapsed=chapterCollapsed}
		    			{>toggleCollapse/}  {~s}
		    		{/.}
						
		    		{<toggleCollapseForm}
		    			<form action="{formAction}" method="post">
							<input type="hidden" name="recipeChapter" value="{id}" />
							<input type="hidden" name="_eventName" value="toggleRecipeChapterCollapse" />
							<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						</form>
					{/toggleCollapseForm} 	
				{/recipesCollapsible}
			</div>
           	
     	    <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{#. objectName="recipeChapter" paramName="title" successIds="navigation-chart-{id}"}
						{>editable/}
					{/.}
				</h3>
				<h4 style="padding-left: 2px;">
					{#. objectName="recipeChapter" paramName="description"}
						{>editable/}
					{/.}
				</h4>
            </div>
             
        </div>	
        
        {! Recipes !}	
		<div id="chapter-content-{id}" class="recipe-chapter-content {?chapterCollapsed} none {/chapterCollapsed}">
			{#recipes}
	        	{>recipe_recipeSummary/}
            {/recipes}
		</div>
        
        {>recipe_recipeChapterMenu/}
		
	</div>
