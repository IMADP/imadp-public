	
	{#. menuTitle="Book Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Book" icon="s-app-edit" template="book_bookDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Add Chapter" icon="s-add" template="book_chapterDialog" contextId="{newChapter.id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Delete Book" objectName="book" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}