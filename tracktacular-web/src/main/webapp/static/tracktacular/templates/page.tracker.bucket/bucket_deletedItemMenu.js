	
	{#. menuTitle="Item Options"}
		{>menu/}		
	{/.}
				
	{<menuBody}			
	
		{#. itemTitle="Activate Item" objectName="item" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Perma-Delete Item" objectName="item" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
		
	{/menuBody}