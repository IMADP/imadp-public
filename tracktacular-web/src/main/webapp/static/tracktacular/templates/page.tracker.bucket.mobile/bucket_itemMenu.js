
	{#. menuTitle="Item Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Item" icon="s-app-edit" template="bucket_itemDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Delete Item" objectName="item" successIds="content-body, content-messages"}
			{>menuItemToStateDeleted/}
		{/.}
										
	{/menuBody}
	