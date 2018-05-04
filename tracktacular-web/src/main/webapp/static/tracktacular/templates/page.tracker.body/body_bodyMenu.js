

	{#. menuTitle="Options"}
		{>menu/}		
	{/.}
		
	{<menuBody}			
		
		{?deleted}
		
			{#. itemTitle="Activate Body" objectName="body" successIds="content-messages, content-body"}
				{>menuItemToStateActive/}
			{/.}
			
			{#. itemTitle="Perma-Delete" objectName="body" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
			
		{:else}
		
			{#. itemTitle="Edit Body" icon="s-app-edit" template="body_bodyDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
					
			{#. itemTitle="Delete Body" objectName="body" successIds="content-messages, content-body"}
				{>menuItemToStateDeleted/}
			{/.}
			
		{/deleted}
		
	{/menuBody}	