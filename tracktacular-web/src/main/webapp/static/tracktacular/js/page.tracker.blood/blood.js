tracktacular.trackers.blood.js = {
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// blood-pressure chart
		$('.blood-pressure-chart-systolic', $context).each(function() {
			blood_bloodPressureChartSystolic($(this));		
		});
		
		// blood-pressure chart
		$('.blood-pressure-chart-diastolic', $context).each(function() {
			blood_bloodPressureChartDiastolic($(this));		
		});
		
		// report for history
		$('.blood-pressure-report-history', $context).each(function() {
			blood_bloodPressureReportHistory($(this));		
		});
		
	}
	
}

var systolicPlotBands = [{
		color: '#BEF091',
		from: 0,
		to: 120,
		label: {
			text: 'Normal',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FFF984',
		from: 120,
		to: 139,
		label: {
			text: 'At Risk',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FFB559',
		from: 139,
		to: 159,
		label: {
			text: 'Stage 1',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FF9D9D',
		from: 159,
		to: 200,
		label: {
			text: 'Stage 2 Hypertension',
			style: {
				color: '#606060'
			}
		}
	}];

var diastolicPlotBands = [{
		color: '#BEF091',
		from: 0,
		to: 80,
		label: {
			text: 'Normal',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FFF984',
		from: 80,
		to: 89,
		label: {
			text: 'At Risk',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FFB559',
		from: 89,
		to: 99,
		label: {
			text: 'Stage 1',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FF9D9D',
		from: 99,
		to: 200,
		label: {
			text: 'Stage 2 Hypertension',
			style: {
				color: '#606060'
			}
		}
	}];

/**
 * Initializes the systolic bloodPressure chart.
 * 
 * @param $report
 */
function blood_bloodPressureChartSystolic($report) {
	var systolic = $report.data('systolic');

	if(systolic < 80)
		systolic = 80;
	
	if(systolic > 200)
		systolic = 200;
	
	new Highcharts.Chart({
        chart: {
            renderTo: $report.get(0),
            type: 'column',
            height: 65,
            width: 500
        },
        title: {
            text: null
        },
        subtitle: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        tooltip: {
			enabled: false
		},
		legend: {
            enabled: false
        },
        xAxis: {
            plotBands: systolicPlotBands,
            min: 80,
            max: 200
        },
        yAxis: {
            min: 0,
            max: 1,
            title: {
                text: null
            },
            labels: {
                formatter: function () {
                    return null;
                }
            }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0,
                pointWidth : 6
            }
        },
        series: [{
            data: [[systolic, 10]]
        }]
    });
}

/**
 * Initializes the diastolic bloodPressure chart.
 * 
 * @param $report
 */
function blood_bloodPressureChartDiastolic($report) {
	var diastolic = $report.data('diastolic');

	if(diastolic < 50)
		diastolic = 50;
	
	if(diastolic > 130)
		diastolic = 130;
	
	new Highcharts.Chart({
        chart: {
            renderTo: $report.get(0),
            type: 'column',
            height: 65,
            width: 500
        },
        title: {
            text: null
        },
        subtitle: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        tooltip: {
			enabled: false
		},
		legend: {
            enabled: false
        },
        xAxis: {
            plotBands: diastolicPlotBands,
            min: 50,
            max: 130
        },
        yAxis: {
            min: 0,
            max: 1,
            title: {
                text: null
            },
            labels: {
                formatter: function () {
                    return null;
                }
            }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0,
                pointWidth : 6
            }
        },
        series: [{
            data: [[diastolic, 10]]
        }]
    });
}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function blood_bloodPressureReportHistory($report) {
	var chartData = $report.data('chartData');
	var renderTo = $report.get(0);
	
	var systolicData = new Array();
	var diastolicData = new Array();

	$.each(chartData, function(index, value) {
		systolicData.push([value.dateMillis, value.systolic]);
		diastolicData.push([value.dateMillis, value.diastolic]);
	});
	
	systolicData.reverse();
	diastolicData.reverse();
	
	chartData = {
		systolicData: systolicData,
		diastolicData: diastolicData
	};
	
	blood_bloodPressureReportHistoryChart(chartData, renderTo);
}

/**
 * Initializes the Total Cholesterol detail chart.
 * 
 * @param $report
 */
function blood_bloodPressureReportHistoryChart(chartData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50
        },
        title: {
			text: null
		},
		subtitle: {
			text: 'Click on a line for healthy ranges'
		},
		credits: {
            enabled: false
        },
        legend: {
            enabled: true
        },
         xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: 'Blood Pressure'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            min: 0           
        },
        tooltip: {
            formatter: function() {
                return '<b>'+this.series.name+':</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        plotOptions: {
			series: {
				cursor: 'pointer',
				point: {
					events: {
						click: function() {
							if(this.series.name == 'Systolic Blood Pressure')
								blood_bloodPressureSystolicDetailChart(chartData, renderTo);
							
							if(this.series.name == 'Diastolic Blood Pressure')
								blood_bloodPressureDiastolicDetailChart(chartData, renderTo);
						}	
					}
				},
				marker: {
					lineWidth: 1
				}
			}
		},

		series: [{
			name: 'Systolic Blood Pressure',
			data: chartData.systolicData
		}, {
			name: 'Diastolic Blood Pressure',
			data: chartData.diastolicData
		}]
    });
}

/**
 * Initializes the Systolic detail chart.
 * 
 * @param $report
 */
function blood_bloodPressureSystolicDetailChart(chartData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50,
			events: {
				click: function() {
					blood_bloodPressureReportHistoryChart(chartData, renderTo);
				}
			}
        },
        title: {
			text: 'Systolic Statistics'
		},
		subtitle: {
			text: 'Click the background to reset the chart'
		},
		credits: {
            enabled: false
        },
        legend: {
            enabled: false
        },
         xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: 'Systolic'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: systolicPlotBands,
            min: 80,
            max: 200            
        },
        tooltip: {
            formatter: function() {
                return '<b>Systolic:</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        series: [{
            data: chartData.systolicData
        }]
    });
}

/**
 * Initializes the Diastolic detail chart.
 * 
 * @param $report
 */
function blood_bloodPressureDiastolicDetailChart(chartData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50,
			events: {
				click: function() {
					blood_bloodPressureReportHistoryChart(chartData, renderTo);
				}
			}
        },
        title: {
			text: 'Diastolic Statistics'
		},
		subtitle: {
			text: 'Click the background to reset the chart'
		},
		credits: {
            enabled: false
        },
        legend: {
            enabled: false
        },
         xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: 'Diastolic'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: diastolicPlotBands,
            min: 50,
            max: 130            
        },
        tooltip: {
            formatter: function() {
                return '<b>Diastolic:</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        series: [{
            data: chartData.diastolicData
        }]
    });
}