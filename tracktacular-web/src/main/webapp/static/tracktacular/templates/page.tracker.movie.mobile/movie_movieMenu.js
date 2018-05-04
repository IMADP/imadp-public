
	{#. menuTitle="Movie Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Movie" icon="s-app-edit" template="movie_movieDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Delete Movie" objectName="movie" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}