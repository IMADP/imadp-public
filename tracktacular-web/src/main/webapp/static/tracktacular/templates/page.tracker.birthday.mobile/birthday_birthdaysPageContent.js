
	{#contentBody}
	
		{#. addButtonTemplate="birthday_birthdayDialog" addButtonTitle="Add Birthday"}
			{>addButton/}		
		{/.}
		
		{#birthdayMonths}
            {>birthday_birthdayMonth/}
        {/birthdayMonths}
        
        {>pageNavigator/}
        
	{/contentBody}