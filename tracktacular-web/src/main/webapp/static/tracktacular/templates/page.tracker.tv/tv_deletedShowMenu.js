
	{#. menuTitle="Show Options"}
		{>menu/}		
	{/.}
				
	{<menuBody}			
	
		{#. itemTitle="Activate Show" objectName="show" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Perma-Delete Show" objectName="show" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
		
	{/menuBody}