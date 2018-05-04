
	{#. menuTitle="Chapter Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Chapter" icon="s-app-edit" template="book_chapterDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Chapter" objectName="chapter" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
							
	{/menuBody}