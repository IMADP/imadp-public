	
	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="birthday_birthdayMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{firstName} {middleName} {lastName}</div>
	    	 	<div class="subtitle">{ageOrdinal} Birthday on {longDate}</div>
	    	 	<div class="subscript">{horoscope}</div>
	    	</div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>