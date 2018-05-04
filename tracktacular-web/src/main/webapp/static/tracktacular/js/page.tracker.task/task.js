tracktacular.trackers.task.js = {
		
	onPageLoad: function() {
		
	},
	
	onPageUpdate: function($context) {

		// chart icons for root categories
		$('.navigation-chart', $context).each(function() {
			task_drawNavigationChart($(this))
		});
		
		// report for completed tasks
		$('.task-report-completed-tasks', $context).each(function() {
			task_taskReportCompletedTasks($(this));		
		});

	}
	
}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $navigationChart
 */
function task_drawNavigationChart($navigationChart) {
	var chartData = $navigationChart.data('chartData');	
	var low = chartData.lowPriorityTasks || 0;
	var medium = chartData.mediumPriorityTasks || 0;
	var high = chartData.highPriorityTasks || 0;

	if(low == 0 && medium == 0 && high == 0)
	{
		chartData = [{
			color: '#E9E9E9',
			y: 1
		}];
	}
	else
	{
		chartData = [{
			color: '#F58634',
			y: high
		},
		{
			color: '#A9CF46',
			y: medium
		},
		{
			color: '#5CC7D1',
			y: low
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
 * @param $report
 */
function task_taskReportCompletedTasks($report) {
	var chartData = $report.data('chartData');		
	var tasksMonthList = chartData.tasksMonthList;
	var lowPriorityTasksByMonthList = chartData.lowPriorityTasksByMonthList;
	var mediumPriorityTasksByMonthList = chartData.mediumPriorityTasksByMonthList;
	var highPriorityTasksByMonthList = chartData.highPriorityTasksByMonthList;

	var dates = new Array(tasksMonthList.length);

	for(var i = 0; i < tasksMonthList.length; i++)
		dates[i] = $.datepicker.formatDate(tasksMonthList.length > 6 ? 'M' : 'MM',
				new Date(tasksMonthList[i]));

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
				text: null
			}
		},
		tooltip: {
			formatter: function() {
				return ''+
				this.x +': '+ this.y +' Completed';
			}
		},
		plotOptions: {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
		},
		series: [{
			color: '#F58634',
			name: 'High Priority',
			data: highPriorityTasksByMonthList
		},
		{
			color: '#A9CF46',
			name: 'Medium Priority',
			data: mediumPriorityTasksByMonthList	
		},
		{
			color: '#5CC7D1',
			name: 'Low Priority',
			data: lowPriorityTasksByMonthList
		}]
	});
}