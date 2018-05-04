
	{#. menuTitle="Birthday Options"}
		{>menu/}		
	{/.}
			
	{<menuBody}			

		{#. itemTitle="Edit Birthday" icon="s-app-edit" template="birthday_birthdayDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Delete Birthday" objectName="birthday" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}

	{/menuBody}