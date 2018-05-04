
	{#contentBody}
		
		<h2>Deleted Birthdays</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedBirthdaysCount} Deleted {@when test="{deletedBirthdaysCount} == 1"} Birthday {:else} Birthdays {/when}
		</h4>				
	
		{#deletedBirthdays}
			{>birthday_deletedBirthday/}
		{/deletedBirthdays}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}