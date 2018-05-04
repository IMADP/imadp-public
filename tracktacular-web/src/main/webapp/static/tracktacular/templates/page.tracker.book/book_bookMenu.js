
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
		
		{?chaptersSortable}		
			{#. itemTitle="Sort Chapters" icon="s-sort" template="book_chapterDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/chaptersSortable}
				
		{#. itemTitle="Delete Book" objectName="book" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}