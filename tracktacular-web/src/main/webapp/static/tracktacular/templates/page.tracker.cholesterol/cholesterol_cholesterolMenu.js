
	{#. menuTitle="Options"}
		{>menu/}		
	{/.}
		
	{<menuBody}			
		
		{?deleted}
		
			{#. itemTitle="Activate Cholesterol" objectName="cholesterol" successIds="content-messages, content-body"}
				{>menuItemToStateActive/}
			{/.}
			
			{#. itemTitle="Perma-Delete" objectName="cholesterol" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
			
		{:else}
		
			{#. itemTitle="Edit Cholesterol" icon="s-app-edit" template="cholesterol_cholesterolDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
					
			{#. itemTitle="Delete Cholesterol" objectName="cholesterol" successIds="content-messages, content-body"}
				{>menuItemToStateDeleted/}
			{/.}
			
		{/deleted}
		
	{/menuBody}	