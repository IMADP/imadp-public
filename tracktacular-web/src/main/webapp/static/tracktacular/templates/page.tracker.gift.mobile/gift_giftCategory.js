
	<li class="ui-li ui-li-static li-item-header-1 li-item-header-no-icon">
		<div class="item-wrapper">
		    <div class="item-left"></div>
	    	<div class="item-center">
	    	 	<div class="title">{name}</div>
	 		 	<div class="subtitle">{giftCount} {@when test="{giftCount} == 1"} Gift {:else} Gifts {/when}</div>
	 		</div>
	 		<div class="item-right"></div>
		</div>	
	</li>
		
	{#gifts}
    	{>gift_gift/}
    {/gifts}