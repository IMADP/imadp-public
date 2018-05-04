
	{#contentBody}
			
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>Body</h2>				
		
				<h4>
					{bodyCount} Body {@when test="{bodyCount} == 1"} Entry {:else} Entries {/when}
				</h4>
			</div>
			 
			<div class="right">
				{#. addButtonTemplate="body_bodyDialog" addButtonTitle="Add Entry" addButtonTemplateContextId="newBody"}
					{>addButton/}		
				{/.}
			</div>	
			
	    </header>
		
		{?showHistoryChart}	       
		    <article class="item" id="body-history-chart">
		    	<header>			
					<span class="icon-s-blank"></span>
				</header>				
				<h3 class="center" style="padding-bottom: 20px;">Body History</h3> 
				<span class="body-report-history" data-chart-data="{allBodies|json}"></span>
			</article>
	    {/showHistoryChart}
	    
		{#bodies}
            {>body_body/}
        {/bodies}
			
	    {#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
        
        {>body_tutorial/}	
	
	{/contentBody}