
	{#. menuTitle="Album Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Album" icon="s-app-edit" template="music_albumDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Album" objectName="album" successIds="content-messages, content-body"} 
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}