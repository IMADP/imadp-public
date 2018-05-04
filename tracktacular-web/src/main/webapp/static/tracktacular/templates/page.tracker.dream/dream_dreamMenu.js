	
	{#. menuTitle="Dream Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{?activeState}
			{#. itemTitle="Edit Dream" icon="s-app-edit" template="dream_dreamDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/activeState}
		
		{#. itemTitle="Activate Dream" objectName="dream" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Direct Link" itemLink=dreamLink}
			{>menuItemLink/}
		{/.}
		
		{#. itemTitle="Delete Dream" objectName="dream" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Dream" objectName="dream" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
							
	{/menuBody}	