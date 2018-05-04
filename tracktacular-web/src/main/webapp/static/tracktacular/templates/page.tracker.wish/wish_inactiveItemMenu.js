		
	{#. menuTitle="Item Options"}
		{>menu/}		
	{/.}
				
	{<menuBody}			
	
		{#. itemTitle="Activate Item" objectName="item" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Complete Item" objectName="item" successIds="content-body, content-messages"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Item" objectName="item" successIds="content-body, content-messages"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Item" objectName="item" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
		
	{/menuBody}