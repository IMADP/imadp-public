tracktacular.trackers.goal.js = {	
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// chart icons for goals
		$('.navigation-chart', $context).each(function() {
			goal_drawNavigationChart($(this))
		});

		// report goals
		$('.goal-chart', $context).each(function() {
			goal_drawGoalChart($(this))
		});

		// report for active timeline
		$('.goal-report-active-timeline', $context).each(function() {
			goal_goalReportActiveTimeline($(this));		
		});
		
	}
	
}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $navigationChart
 */
function goal_drawNavigationChart($navigationChart) {
	var chartData = $navigationChart.data('chartData');
	var progress = chartData.progress;
	var remaining = 100 - progress;

	var chartData = [{
		color: '#7092BE',
		y: remaining
	},
	{
		color: '#A9CF46',
		y: progress
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
					toViewSelected($('#'+$navigationChart.data("linkId")));
				}
			},
			data: chartData
		}]
	});
}

/**
 * Initializes the report section.
 * 
 * @param $chartIcon
 */
function goal_drawGoalChart($chartIcon) {
	var progress = $chartIcon.data('progress');
	var startDate = $chartIcon.data('startDate');
	var targetDate = $chartIcon.data('targetDate');

	new Highcharts.Chart({
		chart: {
			renderTo: $chartIcon.get(0),
			defaultSeriesType: 'bar',
			spacingLeft: 45,
			spacingRight: 45,
			spacingTop: 20,
			spacingBottom: 20,
			height:80,
			width:650
		},
		colors: 
			['#4572A7','#89A54E']
		,
		title: {
			text: null
		},
		subtitle: {
			text: null
		},
		credits: {
			enabled: false
		},
		yAxis: {
			min: 0,
			max: 100,
			gridLineWidth: 0,
			title: {
				text: null
			},
			labels: {
				formatter: function(){
					return null;
				}
			}
		},
		xAxis: [{
			gridLineWidth: 0,
			title: {
				text: 'Start'
			},
			labels: {
				formatter: function(){
					return null;
				}
			}
		},
		{
			gridLineWidth: 0,
			opposite: true,
			title: {
				text: 'End'
			},
			labels: {
				formatter: function(){
					return null;
				}
			}
		}

		],
		legend: {
			enabled: false
		},
		tooltip: {
			formatter: function() {
				return this.series.name;
			}
		},
		plotOptions: {
			bar: {
				pointStart: 0
			},
			series: {
				stacking: 'normal',
				dataLabels: {
					enabled: true,
					color: 'white',
					align: 'center',
	                y: 5,
	                style: {
						fontWeight:'bold',
						fontSize: '12',
						fontFamily: 'Verdana'
					},
					formatter: function(){
						return this.series.index == 1 && this.point.y > 6 ? this.point.y + '%': null;
					}	
				}
			}
		},
		series: [{
			pointWidth: 20,
			color: '#7092BE',
			name: '<b>Target Date:</b>' + targetDate,
			data: [100 - progress]
		}, {
			pointWidth: 28,
			color: '#A9CF46',
			name: '<b>Start Date:</b>' + startDate,
			data: [progress]
		}]
	});

}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function goal_goalReportActiveTimeline($report) {	
	var chartData = $report.data('chartData');
	var currentDate = chartData.currentDate;
	var minDate = chartData.minDate;
	var maxDate = chartData.maxDate;
	var goals = chartData.goals;

	var goalNames = new Array(goals.length);
	var startTimes = new Array(goals.length);
	var completedTimes = new Array(goals.length);
	var remainingTimes = new Array(goals.length);

	for(var i = 0; i < goals.length; i++)
	{
		goalNames[i] = goals[i].name;
		startTimes[i] = goals[i].startDateInMillis;

		completedTimes[i] = {
				name: 'Current Date: ' + currentDate + ' (<b>'+goals[i].progress+'%</b>)',
				y: goals[i].timeCompletedInMillis
		};

		remainingTimes[i] = {
				name: 'Target Date: ' + goals[i].targetDate,
				y: goals[i].timeRemainingInMillis
		};
	}

	new Highcharts.Chart({
		chart: {
			renderTo: $report.get(0),
			defaultSeriesType: 'bar',
			marginRight: 35,
			zoomType: 'y',
			height: 30 + (50 * goals.length)
		},
		credits: {
			enabled: false
		},			
		title: {
			text: null
		},
		xAxis: {
			maxPadding:  0,
			categories: goalNames
		},
		legend: {
			enabled: false
		},
		yAxis: {
			type: 'datetime',
			min: minDate,
			max: maxDate,
			endOnTick: true,
			title: {
				text: null
			}
		},
		tooltip: {
			formatter: function() {
				return this.point.name;
			}
		},
		plotOptions: {
			series: {
				stacking: 'normal'
			}
		},
		series: [{
			pointWidth: 15,
			color: '#7092BE',
			name: 'Remaining',
			data: remainingTimes
		}, {
			pointWidth: 20,
			color: '#A9CF46',
			name: 'Progress',
			data: completedTimes
		}, {
			pointWidth: 0,
			color: 'transparent',
			shadow:false,
			borderWidth:0,
			name: ' ',
			data: startTimes
		}]
	});

}