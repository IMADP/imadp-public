
	{#contentBody}
			
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>Blood Pressure</h2>				
		
				<h4>
					{bloodPressureCount} Blood Pressure {@when test="{bloodPressureCount} == 1"} Entry {:else} Entries {/when}
				</h4>
			</div>
			 
			<div class="right">
				{#. addButtonTemplate="blood_bloodPressureDialog" addButtonTitle="Add Entry" addButtonTemplateContextId="newBloodPressure"}
					{>addButton/}		
				{/.}
			</div>	
			
	    </header>
		
		{?showHistoryChart}	       
		    <article class="item" id="blood-pressure-history-chart">
		    	<header>			
					<span class="icon-s-blank"></span>
				</header>				
				<h3 class="center">Blood Pressure History</h3> 
				<span class="blood-pressure-report-history" data-chart-data="{allBloodPressures|json}"></span>
			</article>
	   {/showHistoryChart}
	    
		{#bloodPressures}
            {>blood_bloodPressure/}
        {/bloodPressures}
		
        {#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
        
        {>blood_tutorial/}	
	        	
	{/contentBody}