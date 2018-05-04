
	{! Recipe !}
	<div class="cf" style="padding: 15px 45px 0 100px" id="recipe-{id}">
			
		<div class="left icons-2 align-right" style="padding-top: 3px;">
			
			{#. menuTargetId="menu-{id}"}
				{>menuButton/}
			{/.}  {~s}
			
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
			
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 450px;">	
						
				<div class="item-leader-left title" style="max-width: 390px;">
					{#. objectName="recipe" paramName="name"}
						{>editable/}
					{/.}	
				</div>
					
				<div class="item-leader-right center">
					<a href="{viewRecipeUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">View</a>
				</div>
				
			</div>
			
			<div class="subtitle" style="margin-left: 10px;">
				{#. objectName="recipe" paramName="description"}
					{>editable/}
				{/.}
			</div>
		
		</div>
		
		{>recipe_recipeSummaryMenu/}
		
	</div>