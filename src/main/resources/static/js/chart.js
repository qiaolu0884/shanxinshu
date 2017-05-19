$(function(){
	var dayCount = new Array(15);
	var monthCount = new Array(12);
	$.ajax({
		type: "POST",
		url: "/index/days",
		data: {},
	    dataType :"json",
	    async: false,
		success: function(data) {
			dayCount = data
		}
	});
	$.ajax({
		type: "POST",
		url: "/index/months",
		data: {},
	    dataType :"json",
	    async: false,
		success: function(data) {
			monthCount = data
		}
	});
    var xdate=[];
	function setXAxis(length){
		for (var i = length-1; i >=1; i--) {
			var xValue= new Date(new Date().setDate(new Date().getDate()-i)).getDate();
			xdate.push(xValue+'日');
		}
	}
	var xmonth = new Array(12);
	$.ajax({
		type: "POST",
		url: "/index/getMonth",
	    dataType :"json",
	    async: false,
		success: function(data) {
			xmonth = data;
		}
	});
	setXAxis(16);
	$('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '近15天交易数据统计  （单位：万）', 
            align: 'left',
        },
        subtitle: {
            text: ' '
        },
        xAxis: {
            categories: xdate
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            }
        },
        tooltip: {
            shared: false,
            useHTML: false
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        legend: {
            enabled: false
        },
        navigation: {
            buttonOptions: {
                enabled: false 
            }
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '交易额',
            data: dayCount
        }]
    });
    
    $('#container1').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '每月交易数据统计  （单位：万）',
            align: 'left',
        },
        subtitle: {
            text: ' '
        },
        xAxis: {
            categories: xmonth
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            }
        },
        tooltip: {
            shared: false,
            useHTML: false
        },
        plotOptions: {
            column: {
                pointPadding: 0.3,
                borderWidth: 0
            }
        },
        credits: {
            enabled: false
        },
        legend: {
            enabled: false
        },
        navigation: {
            buttonOptions: {
                enabled: false 
            }
        },
        series: [{
            name: '交易额',
            data: monthCount
        }]
    });
})