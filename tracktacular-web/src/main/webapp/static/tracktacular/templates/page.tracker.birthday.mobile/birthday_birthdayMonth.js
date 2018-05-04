
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{monthFullName}</div>
		 		 	<div class="subtitle">{birthdayCount} {@when test="'{birthdayCount}' == 1"} Birthday {:else} Birthdays {/when}</div>
		 		</div>
		 		<div class="item-right">
		 			{?collapsible}
			 			{#. targetId="month-{id}" collapsed=notCurrentMonth}
			    			{>toggleCollapse/}
			    		{/.}
		    		{/collapsible}
	    		</div>
			</div>	
		</li>
		
		<li id="month-{id}" style="margin:0; padding: 0;" {@when test="{notCurrentMonth}"} class="none" {/when}>
			<ul style="margin:0; padding: 0;">
				{#birthdays}		
					{>birthday_birthday/}
				{/birthdays}
			</ul>
		</li>
			
	</ul>