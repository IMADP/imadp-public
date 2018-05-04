	
	<section id="birthday-{id}" style="margin-top: 25px;">
		
		<div class="cf" style="padding: 0 45px">
			
			<div class="left icons-2 align-right" style="padding-top: 3px;">
				{>notesButton/}
				{>menuButton/}
			</div>
			
			<div class="left">
			
				<div class="item-leader cf">	
							
					<div class="item-leader-left title {?past}strike{/past}" style="max-width: 370px;">
					
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
						
					</div>
						
					<div class="item-leader-right center">
						<div class="title">{date}</div>
					</div>
					
					<br/>
						
					<div class="cf subtitle">
						<div class="left" style="padding-left: 20px">
							{ageOrdinal} Birthday on {longDate}
						</div>
						<div class="right center" style="padding-left: 20px; width: 95px;">
							{horoscope}
						</div>
					</div>
					
				</div>
			
			</div>
			
		</div>
		
		{>notes/}
				
		{>birthday_birthdayMenu/}
								
	</section>
	