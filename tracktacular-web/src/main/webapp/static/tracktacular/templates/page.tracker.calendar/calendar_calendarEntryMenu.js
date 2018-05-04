
	{#. menuTitle="Entry Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
		
		{?activeState}
			{#. itemTitle="Edit Entry" icon="s-app-edit" template="calendar_calendarEntryDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/activeState}
				
		{#. itemTitle="Activate Entry" objectName="calendarEntry" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Delete Entry" objectName="calendarEntry" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Entry" objectName="calendarEntry" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
							
	{/menuBody}