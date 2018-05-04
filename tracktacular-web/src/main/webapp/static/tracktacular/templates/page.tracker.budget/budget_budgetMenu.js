
	{#. menuTitle="Budget Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{?activeState}
			{#. itemTitle="Edit Budget" icon="s-app-edit" template="budget_budgetDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/activeState}
		
		{?showItems}	
			{#. itemTitle="Add Category" icon="s-add" template="budget_itemCategoryDialog" contextId="newItemCategory"}
				{>menuItemDialogForm/}
			{/.}
		{/showItems}
		
		{#. itemTitle="Activate Budget" objectName="budget" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Complete Budget" objectName="budget" successIds="content-messages, content-body"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Budget" objectName="budget" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Budget" objectName="budget" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
							
	{/menuBody}	