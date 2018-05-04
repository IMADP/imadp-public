
	{#. menuTitle="Options"}
		{>menu/}		
	{/.}
		
	{<menuBody}			
		
		{#. itemTitle="Edit Cholesterol" icon="s-app-edit" template="cholesterol_cholesterolDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Cholesterol" objectName="cholesterol" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
	{/menuBody}	