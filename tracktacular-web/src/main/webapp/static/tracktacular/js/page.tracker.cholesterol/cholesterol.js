tracktacular.trackers.cholesterol.js = {
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// cholesterol chart
		$('.cholesterol-chart-ldl', $context).each(function() {
			cholesterol_cholesterolChartLdl($(this));		
		});
		
		// cholesterol chart
		$('.cholesterol-chart-hdl', $context).each(function() {
			cholesterol_cholesterolChartHdl($(this));		
		});
		
		// cholesterol chart
		$('.cholesterol-chart-triglycerides', $context).each(function() {
			cholesterol_cholesterolChartTriglycerides($(this));		
		});
		
		// cholesterol chart
		$('.cholesterol-chart-total', $context).each(function() {
			cholesterol_cholesterolChartTotal($(this));		
		});
		
		// report for history
		$('.cholesterol-report-history', $context).each(function() {
			cholesterol_cholesterolReportHistory($(this));		
		});
		
	}
	
}

var ldlPlotBands = [{
		color: '#BEF091',
		from: 0,
		to: 130,
		label: {
			text: 'Good',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FFF984',
		from: 130,
		to: 160,
		label: {
			text: 'At Risk',
			style: {
				color: '#606060'
			}
		}
	}, {
		color: '#FF9D9D',
		from: 160,
		to: 200,
		label: {
			text: 'Bad',
			style: {
				color: '#606060'
			}
		}
	}];

var hdlPlotBands = [{
    color: '#FF9D9D',
    from: 0,
    to: 40,
    label: {
        text: 'Bad',
        style: {
            color: '#606060'
        }
    }
}, {
    color: '#FFF984',
    from: 40,
    to: 50,
    label: {
        text: 'At Risk',
        style: {
            color: '#606060'
        }
    }
}, {
    color: '#BEF091',
    from: 50,
    to: 100,
    label: {
        text: 'Good',
        style: {
            color: '#606060'
        }
    }
}];

var triglyceridesPlotBands = [{
    color: '#BEF091',
    from: 0,
    to: 150,
    label: {
        text: 'Good',
        style: {
            color: '#606060'
        }
    }
}, {
    color: '#FFF984',
    from: 150,
    to: 200,
    label: {
        text: 'At Risk',
        style: {
            color: '#606060'
        }
    }
}, {
    color: '#FF9D9D',
    from: 200,
    to: 500,
    label: {
        text: 'Bad',
        style: {
            color: '#606060'
        }
    }
}];

var totalPlotBands = [{
    color: '#BEF091',
    from: 0,
    to: 200,
    label: {
        text: 'Good',
        style: {
            color: '#606060'
        }
    }
}, {
    color: '#FFF984',
    from: 200,
    to: 240,
    label: {
        text: 'At Risk',
        style: {
            color: '#606060'
        }
    }
}, {
    color: '#FF9D9D',
    from: 240,
    to: 400,
    label: {
        text: 'Bad',
        style: {
            color: '#606060'
        }
    }
}];
	
/**
 * Initializes the ldl cholesterol chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolChartLdl($report) {
	var ldl = $report.data('ldl');

	if(ldl > 200)
		ldl = 200;
	
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
            plotBands: ldlPlotBands,
            min: 0,
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
            data: [[ldl, 10]]
        }]
    });
}

/**
 * Initializes the hdl cholesterol chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolChartHdl($report) {
	var hdl = $report.data('hdl');

	if(hdl > 100)
		hdl = 100;
	
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
            plotBands: hdlPlotBands,
            min: 0,
            max: 100
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
            data: [[hdl, 10]]
        }]
    });
}

/**
 * Initializes the triglycerides cholesterol chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolChartTriglycerides($report) {
	var triglycerides = $report.data('triglycerides');

	if(triglycerides > 500)
		triglycerides = 500;
	
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
            plotBands: triglyceridesPlotBands,
            min: 0,
            max: 500
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
            data: [[triglycerides, 10]]
        }]
    });
}

/**
 * Initializes the total cholesterol chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolChartTotal($report) {
	var total = $report.data('total');

	if(total > 400)
		total = 400;
	
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
            plotBands: totalPlotBands,
            min: 0,
            max: 400
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
            data: [[total, 10]]
        }]
    });
}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function cholesterol_cholesterolReportHistory($report) {
	var chartData = $report.data('chartData');
	var renderTo = $report.get(0);
	
	var ldlData = new Array();
	var hdlData = new Array();
	var triglyceridesData = new Array();
	var totalData = new Array();

	$.each(chartData, function(index, value) {
		ldlData.push([value.dateMillis, value.ldlCholesterol]);
		hdlData.push([value.dateMillis, value.hdlCholesterol]);
		triglyceridesData.push([value.dateMillis, value.triglycerides]);
		totalData.push([value.dateMillis, value.totalCholesterol]);
	});
	
	ldlData.reverse();
	hdlData.reverse();
	triglyceridesData .reverse();
	totalData.reverse();
	
	chartData = {
		ldlData: ldlData,
		hdlData: hdlData,
		triglyceridesData: triglyceridesData,
		totalData: totalData,
	};
	
	cholesterol_cholesterolReportHistoryChart(chartData, renderTo);
}

/**
 * Initializes the Total Cholesterol detail chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolReportHistoryChart(chartData, renderTo) {
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
                text: 'Total Cholesterol'
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
							if(this.series.name == 'LDL Cholesterol')
								cholesterol_cholesterolLdlDetailChart(chartData, renderTo);
							
							if(this.series.name == 'HDL Cholesterol')
								cholesterol_cholesterolHdlDetailChart(chartData, renderTo);
							
							if(this.series.name == 'Triglycerides')
								cholesterol_cholesterolTriglyceridesDetailChart(chartData, renderTo);
							
							if(this.series.name == 'Total Cholesterol')
								cholesterol_cholesterolTotalDetailChart(chartData, renderTo);
						}	
					}
				},
				marker: {
					lineWidth: 1
				}
			}
		},

		series: [{
			name: 'LDL Cholesterol',
			data: chartData.ldlData
		}, {
			name: 'HDL Cholesterol',
			data: chartData.hdlData
		}, {
			name: 'Triglycerides',
			data: chartData.triglyceridesData
		}, {
			name: 'Total Cholesterol',
			data: chartData.totalData
		}]
    });
}

/**
 * Initializes the LDL Cholesterol detail chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolLdlDetailChart(chartData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50,
			events: {
				click: function() {
					cholesterol_cholesterolReportHistoryChart(chartData, renderTo);
				}
			}
        },
        title: {
			text: 'LDL Cholesterol Statistics'
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
                text: 'LDL Cholesterol'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: ldlPlotBands,
            min: 0,
            max: 200            
        },
        tooltip: {
            formatter: function() {
                return '<b>LDL Cholesterol:</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        series: [{
            data: chartData.ldlData
        }]
    });
}

/**
 * Initializes the HDL Cholesterol detail chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolHdlDetailChart(chartData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50,
			events: {
				click: function() {
					cholesterol_cholesterolReportHistoryChart(chartData, renderTo);
				}
			}
        },
        title: {
			text: 'HDL Cholesterol Statistics'
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
                text: 'HDL Cholesterol'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: hdlPlotBands,
            min: 0,
            max: 100            
        },
        tooltip: {
            formatter: function() {
                return '<b>HDL Cholesterol:</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        series: [{
            data: chartData.hdlData
        }]
    });
}

/**
 * Initializes the Triglycerides detail chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolTriglyceridesDetailChart(chartData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50,
			events: {
				click: function() {
					cholesterol_cholesterolReportHistoryChart(chartData, renderTo);
				}
			}
        },
        title: {
			text: 'Triglycerides Statistics'
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
                text: 'Triglycerides'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: triglyceridesPlotBands,
            min: 0,
            max: 500            
        },
        tooltip: {
            formatter: function() {
                return '<b>Triglycerides:</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        series: [{
            data: chartData.triglyceridesData
        }]
    });
}

/**
 * Initializes the Total Cholesterol detail chart.
 * 
 * @param $report
 */
function cholesterol_cholesterolTotalDetailChart(chartData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50,
			events: {
				click: function() {
					cholesterol_cholesterolReportHistoryChart(chartData, renderTo);
				}
			}
        },
        title: {
			text: 'Total Cholesterol Statistics'
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
                text: 'Total Cholesterol'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: totalPlotBands,
            min: 0,
            max: 400            
        },
        tooltip: {
            formatter: function() {
                return '<b>Total Cholesterol:</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        series: [{
            data: chartData.totalData
        }]
    });
}