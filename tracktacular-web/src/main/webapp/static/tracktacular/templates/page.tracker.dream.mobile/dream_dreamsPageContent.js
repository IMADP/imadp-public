
	{#contentBody}
		
		{#. addButtonTemplate="dream_dreamDialog" addButtonTitle="Add Dream" addButtonTemplateContextId="newDream"}
			{>addButton/}		
		{/.}
		
		
		{#dreams}
			{>dream_dream/}
		{/dreams}
	
	    {>pageNavigator/}
        
	{/contentBody}