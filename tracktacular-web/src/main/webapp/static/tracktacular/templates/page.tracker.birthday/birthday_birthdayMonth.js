		
	<article class="item birthday-month-panel {?hidden}none{/hidden}" id="birthday-month-{id}" >
			
		<header>			
			<span class="icon-s-blank"></span>
		</header>
		
		<h3 class="center">{monthFullName}</h3>  
		
		<h4 class="center">
			{birthdayCount} {@when test="'{birthdayCount}' == 1"} Birthday {:else} Birthdays {/when}
		</h4>  
			
		<div>
			{#birthdays}		
				{>birthday_birthday/}
			{/birthdays}	
		</div>
		
	</article>