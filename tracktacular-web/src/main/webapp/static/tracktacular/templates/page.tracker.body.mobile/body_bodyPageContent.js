
	{#contentBody}
		{#. addButtonTemplate="body_bodyDialog" addButtonTitle="Add Entry" addButtonTemplateContextId="newBody"}
			{>addButton/}		
		{/.}
		
		{#bodies}
		    {>body_body/}
	    {/bodies}
	    
	    {>pageNavigator/}
        
	{/contentBody}
	