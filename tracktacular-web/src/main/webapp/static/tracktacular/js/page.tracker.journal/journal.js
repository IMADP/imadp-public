tracktacular.trackers.journal.js = {
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// chart icons for journals
		$('.navigation-chart', $context).each(function() {
			journal_drawNavigationChart($(this))
		});
		
	}
	
}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function journal_drawNavigationChart($navigationChart) {
	var chartData = $navigationChart.data('chartData');
	var entryCount = chartData.entryCount || 0;
	
	var chartData = [{
		color: entryCount ? '#7092BE' : '#D9D9D9',
		y: entryCount ? entryCount : 1
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
			renderTo: $navigationChart.get(0),
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
					window.location = $('#'+$navigationChart.data("linkId")).attr("href");
				}
			},
			data: chartData
		}]
	});
}