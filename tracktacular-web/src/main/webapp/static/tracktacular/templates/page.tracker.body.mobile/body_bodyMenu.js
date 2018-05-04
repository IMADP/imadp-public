	
	{#. menuTitle="Options"}
		{>menu/}		
	{/.}
		
	{<menuBody}			
		
		{#. itemTitle="Edit Body" icon="s-app-edit" template="body_bodyDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Body" objectName="body" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
	{/menuBody}	