
	{#contentBody}
	
		<nav>
			
			{?publicMode}
				<div class="option"></div>
			{:else}
				{#. addButtonTemplate="birthday_birthdayDialog" addButtonTitle="Add Birthday"}
					{>addButton/}		
				{/.}
			{/publicMode}		
				
			{>navigationChart/}
	
			{#birthdayMonths}
				{@idx}
					{@when test="{.} == 6"}
						<div class="option"></div>{~s}
						<div class="option"></div>
					{/when}
				{/idx}
	            {>navigationChart/}
	        {/birthdayMonths}
	    </nav>
	    
		{#birthdayMonths}
            {>birthday_birthdayMonth/}
        {/birthdayMonths}
				
        {>birthday_tutorial/}	
	
	{/contentBody} 