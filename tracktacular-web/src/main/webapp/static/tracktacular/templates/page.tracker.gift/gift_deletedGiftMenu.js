
	{#. menuTitle="Gift Options"}
		{>menu/}		
	{/.}
				
	{<menuBody}			
	
		{#. itemTitle="Activate Gift" objectName="gift" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Perma-Delete Gift" objectName="gift" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
		
	{/menuBody}