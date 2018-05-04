
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow" id="body-{id}" >
		
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="body_bodyMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">Body Entry</div>
		 		 	<div class="subtitle">{date}</div>
		 		</div>
		 		<div class="item-right">
		 			{>notesButton/}
				</div>
			</div>	
		</li>
			
		<li class="ui-li ui-li-static li-item center">
			
			{?height}<div><b>Height: </b> {height} </div>{/height}
			{?neck}<div><b>Neck: </b> {neck} </div>{/neck}
			{?chest}<div><b>Chest: </b> {chest} </div>{/chest}
			{?biceps}<div><b>Biceps: </b> {biceps} </div>{/biceps}
			{?forearms}<div><b>Forearms: </b> {forearms} </div>{/forearms}
			{?wrists}<div><b>Wrists: </b> {wrists} </div>{/wrists}
			{?waist}<div><b>Waist: </b> {waist} </div>{/waist}
			{?hips}<div><b>Hips: </b> {hips} </div>{/hips}
			{?thighs}<div><b>Thighs: </b> {thighs} </div>{/thighs}
			{?calves}<div><b>Calves: </b> {calves} </div>{/calves}
			{?ankles}<div><b>Ankles: </b> {ankles} </div>{/ankles}
			{?feet}<div><b>Feet: </b> {feet} </div>{/feet}
			{?weight}<div><b>Weight: </b> {weight} </div>{/weight}
			{?bodyFat}<div><b>Body Fat: </b> {bodyFat}% </div>{/bodyFat}
			
    	 	{>notes/}
			
			{>persistableStateDetails/}
			
		</li>
	</ul>