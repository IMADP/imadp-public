
	{#contentBody}
		
		<h2>Deleted Blood Pressure</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedBloodPressureCount} Deleted Blood Pressure {@when test="{deletedBloodPressureCount} == 1"} Entry {:else} Entries {/when}
		</h4>				
			
		{#deletedBloodPressures deleted="true"}
			{>blood_bloodPressure/}
		{/deletedBloodPressures}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
		
	{/contentBody}