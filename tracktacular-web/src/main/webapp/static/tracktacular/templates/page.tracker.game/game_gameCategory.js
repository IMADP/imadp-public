	
	<div id="game-category-{nameSlug}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{name}
				</h3>
				<h4 style="padding-left: 2px;">
					{gameCount} {@when test="{gameCount} == 1"} Game {:else} Games {/when}
				</h4>
            </div>
             
        </div>	
        
    	{#games}
        	{>game_game/}
        {/games}
	
	</div>
