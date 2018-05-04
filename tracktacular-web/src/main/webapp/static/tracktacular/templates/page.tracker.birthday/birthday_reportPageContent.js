
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no upcoming birthdays.       
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{upcomingBirthdayCount} Upcoming {@when test="'{upcomingBirthdayCount}' == 1"} Birthday {:else} Birthdays {/when} in {currentMonthName} & {nextMonthName}		
		</header>
		
		{?alerts}
			{#currentBirthdays}
		         <header class="report-alert">	
		         	<span class="icon-s s-alert"></span> {firstName} {middleName} {lastName} turned {age} today!
				</header>
	        {/currentBirthdays}
	        
	        {#alertedBirthdays}
		         <header class="report-alert">	
		         	<span class="icon-s s-alert"></span> {firstName} {middleName} {lastName} turns {age} in {>recurrencePeriod:alertRecurrence/}!
				</header>
	        {/alertedBirthdays}
		{/alerts}
		
		<div style="padding: 10px 0 35px 0;">
			{#upcomingBirthdays publicMode="true"}
	           {>birthday_birthday/}
	        {/upcomingBirthdays}
        </div>
       
	{/trackerReportEnabled}