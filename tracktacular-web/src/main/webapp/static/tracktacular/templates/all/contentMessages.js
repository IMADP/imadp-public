
	{#contentMessages}
		
		{#errorMessages messageLevel="error"}
			{>contentMessage/}
		{/errorMessages}		
				
		{#warnMessages messageLevel="warn"}
			{>contentMessage/}
		{/warnMessages}		
				
		{#infoMessages messageLevel="info"}
			{>contentMessage/}
		{/infoMessages}	
				
		{#successMessages messageLevel="success"}
			{>contentMessage/}
		{/successMessages}	
		
	{/contentMessages}