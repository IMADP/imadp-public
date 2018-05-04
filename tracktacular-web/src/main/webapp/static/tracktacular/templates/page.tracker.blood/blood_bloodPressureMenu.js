
	{#. menuTitle="Options"}
		{>menu/}		
	{/.}
		
	{<menuBody}			
		
		{?deleted}
		
			{#. itemTitle="Activate Blood Pressure" objectName="bloodPressure" successIds="content-messages, content-body"}
				{>menuItemToStateActive/}
			{/.}
			
			{#. itemTitle="Perma-Delete" objectName="bloodPressure" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
			
		{:else}
		
			{#. itemTitle="Edit Blood Pressure" icon="s-app-edit" template="blood_bloodPressureDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
					
			{#. itemTitle="Delete Blood Pressure" objectName="bloodPressure" successIds="content-messages, content-body"}
				{>menuItemToStateDeleted/}
			{/.}
			
		{/deleted}
		
	{/menuBody}	