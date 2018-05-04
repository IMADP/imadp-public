
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no given or received gifts.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{givenGiftCount} {@when test="{givenGiftCount} == 1"} Gift {:else} Gifts {/when} Given and  {~s}
			{receivedGiftCount} {@when test="{receivedGiftCount} == 1"} Gift {:else} Gifts {/when} Received	
		</header>
		
		<div style="margin: 15px 0 35px 0; text-align:center;">
			
			{#giftsGiven}
				{>gift_giftChart:giftsByOccasion/}
				
				{>gift_giftChart:giftsByReceiver/}
			{/giftsGiven}
			
			{#giftsReceived}
				{>gift_giftChart:giftsByOccasion/}
				
				{>gift_giftChart:giftsBySender/}
			{/giftsReceived}
			
       	</div>       	
       	
	{/trackerReportEnabled}