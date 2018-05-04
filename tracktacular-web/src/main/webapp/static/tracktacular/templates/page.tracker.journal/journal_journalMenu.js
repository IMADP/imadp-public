
	{#. menuTitle="Journal Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{?activeState}
			{#. itemTitle="Edit Journal" icon="s-app-edit" template="journal_journalDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/activeState}
		
		{#. itemTitle="Activate Journal" objectName="journal" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Complete Journal" objectName="journal" successIds="content-messages, content-body"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Journal" objectName="journal" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Journal" objectName="journal" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
							
	{/menuBody}	