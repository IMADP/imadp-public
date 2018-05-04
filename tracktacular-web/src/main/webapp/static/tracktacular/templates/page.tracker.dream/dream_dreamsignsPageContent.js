
	{#contentBody}
	
		<h2>Dreamsigns</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{dreamsignTagCloud.tagCloudItemCount} {@when test="{dreamsignTagCloud.tagCloudItemCount} == 1"} Dreamsign {:else} Dreamsigns {/when}
		</h4>				
		
		{>tagCloud:dreamsignTagCloud/}
	    
	    {#dreamsignSelected}
	    	
	    	<h2>Dreamsign: {dreamsign}</h2>				
	
			<h4 style="padding-top: 5px; padding-left:60px;">
				{dreamsignCount} {@when test="{dreamsignCount} == 1"} Dream {:else} Dreams {/when}
			</h4>
			
	    	{#dreamsigns dreamCollapsed="true"}
				{>dream_dream/}
			{/dreamsigns}
		
			{#pageNavigator}
				{>pageNavigator/}
			{/pageNavigator}
			
		{/dreamsignSelected}
			  
	{/contentBody}