
	{#contentBody}
			
		{#. addButtonTemplate="cholesterol_cholesterolDialog" addButtonTitle="Add Entry" addButtonTemplateContextId="newCholesterol"}
			{>addButton/}		
		{/.}
		
		{#cholesterols}
            {>cholesterol_cholesterol/}
        {/cholesterols}
	   
	    {>pageNavigator/}
        
	{/contentBody}