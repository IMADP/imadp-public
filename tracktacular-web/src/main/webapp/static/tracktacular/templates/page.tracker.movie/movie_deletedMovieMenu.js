
	{#. menuTitle="Movie Options"}
		{>menu/}		
	{/.}
				
	{<menuBody}			
	
		{#. itemTitle="Activate Movie" objectName="movie" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Perma-Delete Movie" objectName="movie" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
		
	{/menuBody}