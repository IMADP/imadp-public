tracktacular.trackers.dream.js = {

	onPageLoad: function() {

	},

	onPageUpdate: function($context) {

		// chart icons for goals
		$('.dream-chart-icon', $context).each(function() {
			dream_drawDreamChart($(this));		
		});

		// report for dream timeline
		$('.dream-report-timeline', $context).each(function() {
			dream_dreamReportTimeline($(this));		
		});

	}

}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function dream_drawDreamChart($chartIcon) {
	var dreamTotalCount = $chartIcon.data('chartDreamTotalCount');
	var dreamTypeCount = $chartIcon.data('chartDreamTypeCount');
	var dreamType =  $chartIcon.data('chartDreamType');
	
	// no dreams
	if(dreamTypeCount == 0)
	{
		chartData = [{
			color: '#E9E9E9',
			y: 1
		}];
	}
	
	// all dreams
	else if(dreamType == 'all')
	{		
		var favoriteDreamCount = $chartIcon.data('chartFavoriteDreamCount');
		var lucidDreamCount = $chartIcon.data('chartLucidDreamCount');
	
		chartData = [{
			color: '#7092BE',
			y: dreamTotalCount
		},
		{
			color: '#ffd300',
			y: favoriteDreamCount
		},
		{
			color: '#FF5360',
			y: lucidDreamCount
		}];
	}
	
	// favorite dreams
	else if(dreamType == 'favorite')
	{
		var chartData = [{
			color: '#7092BE',
			y: dreamTotalCount
		},
		{
			color: '#ffd300',
			y: dreamTypeCount
		}];
	}
	
	// lucid dreams
	else if(dreamType == 'lucid')
	{
		var chartData = [{
			color: '#7092BE',
			y: dreamTotalCount
		},
		{
			color: '#FF5360',
			y: dreamTypeCount
		}];
	}
	
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

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function dream_dreamReportTimeline($report) {
	var chartData = $report.data('chartData');		
	var dreamsMonthList = chartData.dreamsMonthList;
	var allDreamsByMonthList = chartData.allDreamsByMonthList;
	var favoriteDreamsByMonthList = chartData.favoriteDreamsByMonthList;
	var lucidDreamsByMonthList = chartData.lucidDreamsByMonthList;

	var dates = new Array(dreamsMonthList.length);

	for(var i = 0; i < dreamsMonthList.length; i++)
		dates[i] = $.datepicker.formatDate(dreamsMonthList.length > 6 ? 'M' : 'MM',
				new Date(dreamsMonthList[i]));

	new Highcharts.Chart({
		chart: {
			renderTo: $report.get(0),
			defaultSeriesType: 'column',
			height: 225
		},
		credits: {
			enabled: false
		},
		title: {
			text: null
		},
		xAxis: {
			maxPadding:  0,
			categories: dates
		},
		yAxis: {
			maxPadding:  0,
			title: {
				text: 'Dream Entries'
			}
		},
		tooltip: {
			formatter: function() {
				return ''+
				this.x +': '+ this.y +' ' + this.series.name;
			}
		},
		plotOptions: {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
		},
		series: [{
			color: '#7092BE',
			name: 'Total',
			data: allDreamsByMonthList
		},
		{
			color: '#ffd300',
			name: 'Favorite',
			data: favoriteDreamsByMonthList	
		},
		{
			color: '#FF5360',
			name: 'Lucid',
			data: lucidDreamsByMonthList
		}]
	});
}