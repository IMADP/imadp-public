tracktacular.trackers.game.js = {

	onPageLoad: function() {

	},

	onPageUpdate: function($context) {

		// chart icons for games
		$('.game-chart-icon', $context).each(function() {
			game_drawGameChart($(this));		
		});

	}

}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function game_drawGameChart($chartIcon) {
	var gameType =  $chartIcon.data('chartGameType');
	var gameColor = $chartIcon.data('chartGameColor');
	
	chartData = [{
		color: gameColor,
		y: 1
	}];
	
	new Highcharts.Chart({
		chart: {
			margin: [-3,0,0,-3],
			spacingTop: 0,
			spacingRight: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			backgroundColor: null,
			plotBackgroundColor: null,
			renderTo: $chartIcon.get(0),
			width: 36,
			height: 36
		},
		credits: {
			enabled: false
		},
		title: {
			text: null
		},
		tooltip: {
			enabled: false
		},
		plotOptions: {
			series: {
				states: {
					hover: {
						enabled: false
					}
				}
			},
			pie: {
				allowPointSelect: false,
				cursor: 'pointer',
				shadow: false,
				dataLabels: {
					enabled: false
				}
			}
		},
		series: [{
			type: 'pie',
			events: {
				click: function(event) {
					window.location = $('#'+$chartIcon.data("linkId")).attr('href');
				}
			},
			data: chartData
		}]
	});
}