tracktacular.trackers.recipe.js = {
		
	onPageLoad: function() {
		
	},
	
	onPageUpdate: function($context) {

		// chart icons for root chapters
		$('.navigation-chart', $context).each(function() {
			recipe_drawNavigationChart($(this))
		});

	}
	
}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $navigationChart
 */
function recipe_drawNavigationChart($navigationChart) {
	var chartData = $navigationChart.data('chartData');
	var chapterId = chartData.chapterId;
	var backgroundColor = '#7092BE';
	var activeColor = '#A9CF46';
	var data = new Array();
	
	if(chartData.allChapters.length == 0)
		data.push({
				color: '#D9D9D9',
				y: 1
			});	
			
	$.each(chartData.allChapters, function(index, value) {	
		
		if(value.recipeCount == 0)
		{
			data.push({
				color: '#D9D9D9',
				y: 1
			});	
		}
		else
		{
			data.push({
				color: chapterId == 'all' || chapterId == value.chapterId ? activeColor : backgroundColor,
				y: value.recipeCount
			});	
		}	
			
	});
	
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
					toViewSelected($('#'+$navigationChart.data("linkId")));
				}
			},
			data: data
		}]
	});
}
