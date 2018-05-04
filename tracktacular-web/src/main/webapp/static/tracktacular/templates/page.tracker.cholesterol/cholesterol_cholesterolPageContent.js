
	{#contentBody}
			
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>Cholesterol</h2>				
		
				<h4>
					{cholesterolCount} Cholesterol {@when test="{cholesterolCount} == 1"} Entry {:else} Entries {/when}
				</h4>
			</div>
			 
			<div class="right">
				{#. addButtonTemplate="cholesterol_cholesterolDialog" addButtonTitle="Add Entry" addButtonTemplateContextId="newCholesterol"}
					{>addButton/}		
				{/.}
			</div>	
			
	    </header>
		
		{?showHistoryChart}	       
		    <article class="item" id="cholesterol-history-chart">
		    	<header>			
					<span class="icon-s-blank"></span>
				</header>				
				<h3 class="center">Cholesterol History</h3> 
				<span class="cholesterol-report-history" data-chart-data="{allCholesterols|json}"></span>
			</article>
	    {/showHistoryChart}
	    
		{#cholesterols}
            {>cholesterol_cholesterol/}
        {/cholesterols}
        
        {#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
        		
        {>cholesterol_tutorial/}	
	        	
	{/contentBody}