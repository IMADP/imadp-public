				
	<article class="item budget-panel {?hidden}none{/hidden}" id="budget-{id}">
			
		<header>
			{>menuButton/}
			{>notesButton/}
		</header>
				
		<h3 class="center">	
			{#. objectName="budget" paramName="name" successIds="navigation-chart-{id}"}
				{>editable/}
			{/.} 			
		</h3>  
		
		<h4 class="center">
			{?description}	
				{#. objectName="budget" paramName="description"}
					{>editable/}
				{/.}    
			{/description}	
		</h4> 	
		
		{>notes/}
				
		<div id="budget-chart-{id}">
			<div style="padding-top: 25px" class="budget-chart" data-chart-data="{chartData|json}"></div>
		</div>
		
		{?showItems}	
			{#categories}
	            {>budget_itemCategory/}
	        {/categories}	
		{/showItems}  		
	
		<div class="title center" style="padding-top: 25px">
			<b>Total Balance: <span class="{?income} income {:else} expense {/income}">{netAmount}</span></b>
		</div>
			
		{^showItems}	
			<div class="center" style="padding-top: 25px;">
				<a href="{viewBudgetUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">View Budget</a>
			</div>
		{/showItems}
			
		{>persistableStateDetails/}
		
		{>budget_budgetMenu/}
				
	</article>