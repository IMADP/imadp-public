
	{#. menuTitle="Options"}
		{>menu/}		
	{/.}
		
	{<menuBody}			
		
		{#. itemTitle="Edit Blood Pressure" icon="s-app-edit" template="blood_bloodPressureDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Blood Pressure" objectName="bloodPressure" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
	{/menuBody}	