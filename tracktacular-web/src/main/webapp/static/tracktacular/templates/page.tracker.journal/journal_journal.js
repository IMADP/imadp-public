			
	<article class="item journal-panel {?hidden}none{/hidden}" id="journal-{id}">
			
		<header>
			{>menuButton/}
			{>notesButton/}
		</header>
				
		<h3 class="center">	
			{#. objectName="journal" paramName="name" successIds="navigation-chart-{id}"}
				{>editable/}
			{/.} 			
		</h3>  
		
		<h4 class="center">
			{?description}	
				{#. objectName="journal" paramName="description"}
					{>editable/}
				{/.}    
			{/description}	
		</h4> 	
		
		{>notes/}
				
		<div class="title center" style="padding-top: 25px">
			<b>{entryCount} {@when test="{entryCount} == 1"} Entry {:else} Entries {/when}</b>
		</div>
		
		{?recentEntries}
			<div class="subtitle center">
				Recent Entries:
			</div>
		{/recentEntries}
		
		{#recentEntries}
			<div class="item-leader cf" style="width: 560px; margin: 10px 40px 0 40px;">							
				<div class="item-leader-left" style="max-width: 360px;" >
					<span class="title">{title}</span>
				</div>						
				<div class="item-leader-right center align-right" style="width: 140px;">
					<span class="subtitle">{date}</span>
				</div>					
			</div>
		{/recentEntries}
		
		<div class="center" style="padding-top: 25px;">
			<a href="{viewJournalUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">View Journal</a>
		</div>		
			
		{>persistableStateDetails/}
		
		{>journal_journalMenu/}
				
	</article>