
	{#. menuTitle="Song Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Song" icon="s-app-edit" template="music_songDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Song" objectName="song" successIds="content-messages, content-body"} 
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}