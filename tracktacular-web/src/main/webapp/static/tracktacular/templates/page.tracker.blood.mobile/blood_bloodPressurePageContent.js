
	{#contentBody}
		{#. addButtonTemplate="blood_bloodPressureDialog" addButtonTitle="Add Entry" addButtonTemplateContextId="newBloodPressure"}
			{>addButton/}		
		{/.}
		
		{#bloodPressures}
            {>blood_bloodPressure/}
        {/bloodPressures}
        
        {>pageNavigator/}
        
    {/contentBody}