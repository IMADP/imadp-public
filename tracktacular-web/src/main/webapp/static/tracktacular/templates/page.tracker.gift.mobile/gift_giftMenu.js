	
	{#. menuTitle="Gift Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{?received}
			{#. itemTitle="Edit Gift" icon="s-app-edit" template="gift_giftGivenDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{:else}
			{#. itemTitle="Edit Gift" icon="s-app-edit" template="gift_giftReceivedDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/received}
		
		{#. itemTitle="Delete Gift" objectName="gift" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}