
	{#contentBody}
			
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>{name}</h2>				
		
				<h4>
					{entryCount} {@when test="{entryCount} == 1"} Entry {:else} Entries {/when}
				</h4>
			</div>
			 
			<div class="right">
			
				<div class="option">
					<a href="{sortUrl}">
						<span class="option-icon">
							<span class="icon-l l-{?descending}asc{:else}desc{/descending}"></span>
						</span>
						{?descending}Oldest{:else}Newest{/descending} First
					</a>
				</div>
				
				{#. addButtonTemplate="journal_entryDialog" addButtonTemplateContextId="newEntry" addButtonTitle="Add Entry"}
					{>addButton/}		
				{/.}
				
			</div>	
			
	    </header>		
			
		{#entries entryCollapsed="true"}
            {>journal_entry/}
        {/entries}
        
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
		
		{>journal_tutorial/}
		
	{/contentBody}