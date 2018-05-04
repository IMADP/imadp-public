
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow" id="body-{id}" >
		
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="blood_bloodPressureMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">Blood Pressure Entry</div>
		 		 	<div class="subtitle">{date}</div>
		 		</div>
		 		<div class="item-right">
		 			{>notesButton/}
				</div>
			</div>	
		</li>
			
		<li class="ui-li ui-li-static li-item center">
			
			{?systolic}<div><b>Systolic: </b> {systolic} </div>{/systolic}
			{?diastolic}<div><b>Diastolic: </b> {diastolic} </div>{/diastolic}
			
    	 	{>notes/}
			
			{>persistableStateDetails/}
			
		</li>
	</ul>