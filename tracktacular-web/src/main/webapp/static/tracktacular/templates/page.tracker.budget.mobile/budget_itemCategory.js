		
	<li class="ui-li ui-li-static li-item-header-{depth}">
		<div class="item-wrapper">
		    <div class="item-left">
		    	{#. menuButtonTemplate="budget_itemCategoryMenu"}
					{>menuButton/}		
				{/.}
		    </div>
	    	<div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle {?income} income {:else} expense {/income}">{netItemAmount}</div>
	 		</div>
	 		<div class="item-right">	
	 			{?categoryCollapsible}	
					{#. targetId="category-content-{id}" collapsed=categoryCollapsed}
						{>toggleCollapse/}  {~s}
					{/.}
						
					{<toggleCollapseForm}
						<form action="{formAction}" method="post">
							<input type="hidden" name="itemCategory" value="{id}" />
							<input type="hidden" name="_eventName" value="toggleItemCategoryCollapse" />
							<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						</form>
					{/toggleCollapseForm}
				{/categoryCollapsible}
			</div>
		</div>	
	</li>
	
	<li id="category-content-{id}"  class="{?categoryCollapsed} none {/categoryCollapsed}">
		<ul class="container">
	
			{#items}
		        {>budget_item/}
		    {/items}
		    
		    {#childCategories}
		        {>budget_itemCategory/}
		    {/childCategories}
	    
	    </ul>
    
    </li>
    