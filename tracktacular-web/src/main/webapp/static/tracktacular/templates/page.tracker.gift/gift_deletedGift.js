		
	<article class="item">
			
		<header>	
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="gift" paramName="name" successIds="gift-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		{?received}
			<h4 class="center">
				{#. objectName="gift" paramName="occasion" successIds="gift-{id}"}
					{>editable/}
				{/.} {~s}
				
				gift from {~s}
			
				{#. objectName="gift" paramName="sender" successIds="gift-{id}"}
					{>editable/}
				{/.} on {~s}
			
				{date} {~s}			
			</h4>
		{:else}
			<h4 class="center">
				{#. objectName="gift" paramName="occasion" successIds="gift-{id}"}
					{>editable/}
				{/.} {~s}
				
				gift to {~s}
			
				{#. objectName="gift" paramName="receiver" successIds="gift-{id}"}
					{>editable/}
				{/.} on {~s}
			
				{date} {~s}			
			</h4>
		{/received}
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>gift_deletedGiftMenu/}
		
	</article>