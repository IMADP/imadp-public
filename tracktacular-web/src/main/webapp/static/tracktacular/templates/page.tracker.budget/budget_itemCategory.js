		
	<div id="category-{id}" class="{?root} item-category-root-panel {?hidden}none{/hidden} {/root}" style="margin: 40px 0 0 60px;">
		
		<div class="table">
		
			<div class="cell icons-2 align-right">	
				
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
				
				{>menuButton/}
			</div>
			
			<div class="cell" style="padding-left: 5px;">
			
				<h3>
					{#. objectName="itemCategory" paramName="name" successIds="budget-chart-{budgetId}"}
						{>editable/}
					{/.}
				</h3>
				
			</div>
			
		</div>
		
		<div id="category-content-{id}" class="{?categoryCollapsed} none {/categoryCollapsed}">
		
			 <ul style="margin-bottom: 0; padding-left: 0px;">
				{#items}
	                <li>{>budget_item/}</li>
	            {/items}
			</ul>
			
			{#childCategories}
	            {>budget_itemCategory/}
	        {/childCategories}
		    
			{?childCategories}
	            <div style="padding-top: 25px;"></div>
	        {/childCategories}
		    
		</div>
		
		<div class="item-leader item-leader-total-{depth} cf" style="margin-top: 5px; margin-left: 12px;">	
							
			<div class="item-leader-left title">
				<b>Total</b>
			</div>
				
			<div class="item-leader-right title center">
				<span class="{?income} income {:else} expense {/income}">
					{netItemAmount}
				</span> 
			</div>
			
		</div>
				
		{>budget_itemCategoryMenu/}
		
	</div>
