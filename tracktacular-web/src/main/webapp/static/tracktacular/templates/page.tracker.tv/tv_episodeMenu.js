
	{#. menuTitle="Episode Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Episode" icon="s-app-edit" template="tv_episodeDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?episodesSortable}		
			{#. itemTitle="Sort Episodes" icon="s-sort" template="tv_episodeDialogSort" contextId="{showId}"}
				{>menuItemDialogForm/}
			{/.}
		{/episodesSortable}
		
		{#. itemTitle="Delete Episode" objectName="episode" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
							
	{/menuBody}