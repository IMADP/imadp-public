
	{#contentBody}
	
		<nav>
			
			{#. addButtonTemplate="journal_journalDialog" addButtonTitle="Add Journal"}
				{>addButton/}		
			{/.}
					
			{#activeJournals}
				<div class="option" id="navigation-chart-{id}">
		
					<span class="option-icon navigation-chart" 
						data-chart-data="{chartData|json}"
					    data-link-id="navigation-chart-link-{id}">
					</span>
						
					<a id="navigation-chart-link-{id}" href="{viewJournalUrl}"> {name} </a>
							
				</div>				 
	        {/activeJournals}
	        
	    </nav>
	    
		{#activeJournals}
            {>journal_journal/}
        {/activeJournals}		
        
    	{>journal_tutorial/}
        	
	{/contentBody} 