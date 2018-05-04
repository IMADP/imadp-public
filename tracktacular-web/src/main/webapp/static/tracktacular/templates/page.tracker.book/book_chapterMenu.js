
	{#. menuTitle="Chapter Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Chapter" icon="s-app-edit" template="book_chapterDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?chaptersSortable}		
			{#. itemTitle="Sort Chapters" icon="s-sort" template="book_chapterDialogSort" contextId="{bookId}"}
				{>menuItemDialogForm/}
			{/.}
		{/chaptersSortable}
		
		{#. itemTitle="Delete Chapter" objectName="chapter" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
							
	{/menuBody}