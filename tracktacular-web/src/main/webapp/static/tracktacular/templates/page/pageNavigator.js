		
	{?visible}
		<div class="page-navigator box-shadow">		
			<div class="cf center">
			
				<div class="controls left align-left">
				
					{#firstPageLink}
						{?disabled}
							<span>First</span> 
						{:else}
							<a title="Go to the first page" href="{url}">First</a>
						{/disabled}
					{/firstPageLink}
					<span>|</span>
					{#previousPageLink}
						{?disabled}
							<span>Previous</span> 
						{:else}
							<a title="Go to the previous page" href="{url}">Previous</a>
						{/disabled}
					{/previousPageLink}
					
				</div>
				
				<div class="links left">
				
					{#pageLinks}				
						{?disabled}
							<span>{label}</span> 
						{:else}
							<a title="Go to page {label}" href="{url}">{label}</a>
						{/disabled}
					{/pageLinks}
				
				</div>
				
				<div class="controls left align-right">
				
					{#nextPageLink}
						{?disabled}
							<span>Next</span> 
						{:else}
							<a title="Go to the next page" href="{url}">Next</a>
						{/disabled}
					{/nextPageLink}
					<span>|</span>
					{#lastPageLink}
						{?disabled}
							<span>Last</span> 
						{:else}
							<a title="Go to the last page" href="{url}">Last</a>
						{/disabled}
					{/lastPageLink}
					
				</div>
				
			</div>		
		</div>
	{/visible}
		