
	{#. menuTitle="Show Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Show" icon="s-app-edit" template="tv_showDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Add Episode/Season" icon="s-add" template="tv_episodeDialog" contextId="{newEpisode.id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?episodesSortable}		
			{#. itemTitle="Sort Episodes/Seasons" icon="s-sort" template="tv_episodeDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/episodesSortable}
				
		{#. itemTitle="Delete Show" objectName="show" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}