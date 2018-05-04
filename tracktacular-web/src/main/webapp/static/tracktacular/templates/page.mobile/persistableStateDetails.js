	

	<section style="padding-top: 15px; color: #6D869F; font-size: 12px;">
	
		<div class="subtitle blue center">Created on {createdDate}</div>
			
		{?archivedState}
			<div class="subtitle blue center">Completed on {completedDate}</div>
		{/archivedState}
		
		{?deletedState}
			<div class="subtitle blue center">Deleted on {deletedDate}</div>
		{/deletedState}
		
	</section>
	