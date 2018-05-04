		
	<article class="item">
			
		<header>	
			{>menuButton/}
			{>notesButton/}	
		</header>
		
		<h3 class="center">
		
			{?firstName}
				{#. objectName="birthday" paramName="firstName" successIds="birthday-{id}"}
					{>editable/}
				{/.}   
			{/firstName}
			
			{?middleName}
				{#. objectName="birthday" paramName="middleName" successIds="birthday-{id}"}
					{>editable/}
				{/.}   
			{/middleName}
				
			{?lastName}
				{#. objectName="birthday" paramName="lastName" successIds="birthday-{id}"}
					{>editable/}
				{/.}   
			{/lastName}
			
		</h3>
		
		<h4 class="center">{date}</h4>
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>birthday_deletedBirthdayMenu/}
		
	</article>