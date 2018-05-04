tracktacular.trackers.exercise.js = {
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// chart icons for routines
		$('.navigation-chart', $context).each(function() {
			exercise_drawNavigationChart($(this))
		});

		// report for active routines
		$('.exercise-report-active', $context).each(function() {
			exercise_exerciseReportActive($(this));		
		});
		
		// report for completed routines
		$('.exercise-report-completed', $context).each(function() {
			exercise_exerciseReportCompleted($(this));		
		});
		
	}
	
}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function exercise_drawNavigationChart($navigationChart) {
	var chartData = $navigationChart.data('chartData');
	var workoutCount = chartData.workoutCount || 0;
	
	var chartData = [{
		color: workoutCount ? '#7092BE' : '#D9D9D9',
		y: workoutCount ? workoutCount : 1
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

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function exercise_exerciseReportActive($report) {
	exercise_drawExerciseReport($report.data('chartData'), $report.get(0));
}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function exercise_exerciseReportCompleted($report) {
	exercise_drawExerciseReport($report.data('chartData'), $report.get(0));
}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function exercise_drawExerciseReport(chartData, renderTo) {	
	var exerciseReportStatisticsCollection = chartData.exerciseStatisticsCollection;
	var colors = Highcharts.getOptions().colors;
	var data = new Array();
	var categories = new Array();
	
	$.each(exerciseReportStatisticsCollection, function(index, value) {
		categories.push(value.exerciseName);
		data.push({
			y: value.exerciseCount,
			color: colors[index],
			statistics: value
		});
	});
	
	new Highcharts.Chart({	
		chart: {	
			renderTo: renderTo,	
			type: 'column'	
		},	
		credits: {
			enabled: false
		},
		title: {
			text: 'Exercises in your routine'
		},
		subtitle: {
			text: 'Click to view individual exercise statistics'
		},
		tooltip: {
			enabled: false
		},
		legend: {
			enabled: false
		},
		xAxis: {	
			categories: categories,
			title: {	
				text: null
			}
		},
		yAxis: {	
			title: {	
				text: 'Exercise frequency'	
			}	
		},	
		plotOptions: {	
			column: {
				cursor: 'pointer',
				pointWidth: 50,
				point: {
					events: {
						click: function() {
							exercise_drawExerciseReportEntries(chartData, this.options.statistics, renderTo);
						}
					}
				},
			},	
			dataLabels: {	
				enabled: true,	
				style: {	
					fontWeight: 'bold'	
				},
				formatter: function() {	
					return this.y +'%';	
				}	
			}	
		},
		series: [{	
			data: data	
		}],	
		exporting: {	
			enabled: false	
		}
	});	

}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function exercise_drawExerciseReportEntries(chartData, statistics, renderTo) {	
	var colors = Highcharts.getOptions().colors;
	
	var categories = ['Calories', 'Distance', 'Duration', 'Repetitions', 'Weight'];
	
	var data = [{
		y: statistics.entryCaloriesCount,
		color: colors[0],
		entryName: 'Calories',
		statistics: statistics.caloriesStatistics
	},{
		y: statistics.entryDistanceCount,
		color: colors[1],
		entryName: 'Distance',
		statistics: statistics.distanceStatistics
	},{
		y: statistics.entryDurationCount,
		color: colors[2],
		entryName: 'Duration',
		statistics: statistics.durationStatistics
	},{
		y: statistics.entryRepetitionCount,
		color: colors[3],
		entryName: 'Repetitions',
		statistics: statistics.repetitionsStatistics
	},{
		y: statistics.entryWeightCount,
		color: colors[4],
		entryName: 'Weight',
		statistics: statistics.weightStatistics
	}];
	
	new Highcharts.Chart({	
		chart: {	
			renderTo: renderTo,	
			type: 'column',
			events: {
				click: function() {
					exercise_drawExerciseReport(chartData, renderTo);
				}
			}
		},	
		credits: {
			enabled: false
		},
		title: {
			text: 'Entry counts for exercise: ' + statistics.exerciseName
		},
		subtitle: {
			text: 'Click an entry to view statistics or click the background to reset the chart'
		},
		tooltip: {
			enabled: false
		},
		legend: {
			enabled: false
		},
		xAxis: {	
			categories: categories,
			title: {	
				text: null
			}	
		},
		yAxis: {	
			title: {	
				text: 'Number of entries'	
			}	
		},	
		plotOptions: {	
			column: {
				cursor: 'pointer',
				point: {
					events: {
						click: function() {
							exercise_drawExerciseReportEntriesStatistics(
							chartData, this.options.statistics, this.options.entryName, statistics.exerciseName, renderTo);
						}
					}
				},
			},	
			dataLabels: {	
				enabled: true,	
				style: {	
					fontWeight: 'bold'	
				},
				formatter: function() {	
					return this.y +'%';	
				}	
			}	
		},
		series: [{	
			data: data	
		}],	
		exporting: {	
			enabled: false	
		}
	});	

}


/**
 * Initializes the report section.
 * 
 * @param $report
 */
function exercise_drawExerciseReportEntriesStatistics(chartData, statistics, entryName, exerciseName, renderTo) {	
	var colors = Highcharts.getOptions().colors;

	var categories = ['Min', 'Max', 'Average'];
	var minData = new Array();
	var maxData = new Array();
	var meanData = new Array();

	$.each(statistics, function(index, value) {
		minData.push([value.workoutDate, value.min]);
		maxData.push([value.workoutDate, value.max]);
		meanData.push([value.workoutDate, value.mean]);
	});

	new Highcharts.Chart({
		chart: {
			renderTo: renderTo,
			type: 'spline',
			events: {
				click: function() {
					exercise_drawExerciseReport(chartData, renderTo);
				}
			}
		},
		title: {
			text: entryName + ' statistics for exercise: ' + exerciseName
		},
		subtitle: {
			text: 'Click the background to reset the chart'
		},
		credits: {
			enabled: false
		},
		xAxis: {
			type: 'datetime'
		},
		yAxis: {
			type: entryName == 'Duration' ? 'datetime' : null,
			title: {
				text: null
			}
		},
		tooltip: {
			formatter: function() {
				var data = this.y;
				
				if(entryName == 'Duration')
				{
					var tm = new Date(data);
					var hours = tm.getUTCHours(); 
					var minutes = tm.getUTCMinutes(); 
					var seconds = tm.getUTCSeconds(); 
					data = hours + 'h ' + minutes + 'm ' + seconds + 's';
				}
				
				return '<b>'+ this.series.name +': '+data+'</b><br/>'+
				Highcharts.dateFormat('%b %e', this.x);
			}
		},
		series: [{
			name: 'Min',
			data: minData
		}, {
			name: 'Max',
			data: maxData
		}, {
			name: 'Average',
			data: meanData
		}]
	});

}