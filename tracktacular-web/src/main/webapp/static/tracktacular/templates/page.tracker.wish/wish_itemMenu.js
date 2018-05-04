	
	{#. menuTitle="Item Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Item" icon="s-app-edit" template="wish_itemDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?itemsSortable}		
			{#. itemTitle="Sort Items" icon="s-sort" template="wish_itemDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/itemsSortable}
		
		{#. itemTitle="Complete Item" objectName="item" successIds="content-body, content-messages"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Item" objectName="item" successIds="content-body, content-messages"}
			{>menuItemToStateDeleted/}
		{/.}
										
	{/menuBody}