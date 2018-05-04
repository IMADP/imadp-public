
	{#. menuTitle="Birthday Options"}
		{>menu/}		
	{/.}
				
	{<menuBody}			
	
		{#. itemTitle="Activate Birthday" objectName="birthday" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Perma-Delete Birthday" objectName="birthday" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
		
	{/menuBody}