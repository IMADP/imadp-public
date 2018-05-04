
	{#. menuTitle="Episode Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Episode" icon="s-app-edit" template="tv_episodeDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Episode" objectName="episode" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
							
	{/menuBody}