function clearAdlink() {
    $("tspan:contains('chart by amcharts.com')").each(function (index, ele) {
        $(ele).parent("text").parent("g").remove();
    });
};


function drawComprehensiveStatGraph(chartData) {
    // SERIAL CHART
    var chart = new AmCharts.AmSerialChart();
    chart.dataProvider = chartData;
    chart.categoryField = "date";
    chart.dataDateFormat = "YYYY-MM-DD";
    chart.color = "#FFFFFF";
    chart.marginLeft = 0;

    // AXES
    // category
    var categoryAxis = chart.categoryAxis;
    categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
    categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
    categoryAxis.autoGridCount = false;
    categoryAxis.gridCount = 50;
    categoryAxis.gridAlpha = 0.1;
    categoryAxis.gridColor = "#FFFFFF";
    categoryAxis.axisColor = "#555555";
    // we want custom date formatting, so we change it in next line
    categoryAxis.dateFormats = [
        {
            period: 'DD',
            format: 'DD'
        },
        {
            period: 'WW',
            format: 'MMM DD'
        },
        {
            period: 'MM',
            format: 'MMM'
        },
        {
            period: 'YYYY',
            format: 'YYYY'
        }
    ];

    // as we have data of different units, we create three different value axes
    // Distance value axis
    var earnedScoreAxis = new AmCharts.ValueAxis();
    earnedScoreAxis.title = "Channels Returned Score";
    earnedScoreAxis.gridAlpha = 0;
    earnedScoreAxis.axisAlpha = 0;
    chart.addValueAxis(earnedScoreAxis);

    // latitude value axis
    var userCostsAxis = new AmCharts.ValueAxis();
    userCostsAxis.title = "User Costed Scores";
    userCostsAxis.gridAlpha = 0;
    userCostsAxis.axisAlpha = 0;
//        userCostsAxis.labelsEnabled = false;
    userCostsAxis.position = "right";
    userCostsAxis.offset = 50;
    chart.addValueAxis(userCostsAxis);

    surplusAxis
    var surplusAxis = new AmCharts.ValueAxis();
    surplusAxis.gridAlpha = 0;
    surplusAxis.axisAlpha = 0;
    surplusAxis.title = "Platform's Surplus";
    surplusAxis.axisColor = "#ff5755";
    surplusAxis.color = "#ff5755";
    surplusAxis.titleColor = "#ff5755";
    surplusAxis.totalTextColor = "#ff5755";
//        surplusAxis.labelsEnabled = false;
//        surplusAxis.position = "left";
    surplusAxis.offset = 50;
    chart.addValueAxis(surplusAxis);
    // duration value axis

    var alipayDrawOutsAxis = new AmCharts.ValueAxis();
    alipayDrawOutsAxis.title = "Alipay Withdrawal";
    alipayDrawOutsAxis.gridAlpha = 0;
    alipayDrawOutsAxis.axisAlpha = 0;
//        alipayDrawOutsAxis.inside = true;
    alipayDrawOutsAxis.position = "right";
    chart.addValueAxis(alipayDrawOutsAxis);

    // GRAPHS
    var surplusGraph = new AmCharts.AmGraph();
    surplusGraph.valueField = "surplus";
    surplusGraph.title = "Platform Surplus";
    surplusGraph.type = "line";
    surplusGraph.valueAxis = surplusAxis; // indicate which axis should be used
    surplusGraph.lineColor = "#ff5755";
    surplusGraph.lineThickness = 1;
    surplusGraph.legendValueText = "[[value]] sco";
    surplusGraph.legendPeriodValueText = "total: [[value.sum]] sco";
    surplusGraph.bullet = "round";
    surplusGraph.bulletSizeField = "surplusWeigth"; // indicate which field should be used for bullet size
    surplusGraph.bulletBorderColor = "#ff5755";
    surplusGraph.bulletBorderAlpha = 1;
    surplusGraph.bulletBorderThickness = 2;
    surplusGraph.bulletColor = "#000000";
    surplusGraph.labelPosition = "right";
    surplusGraph.balloonText = "当日利润:  [[value]] sco";
    surplusGraph.showBalloon = true;
    surplusGraph.dashLengthField = "dashLength";
    chart.addGraph(surplusGraph);

    var earnedScoreGraph = new AmCharts.AmGraph();
    earnedScoreGraph.valueField = "earnedScore";
    earnedScoreGraph.title = "Platform Earned Score";
    earnedScoreGraph.type = "column";
    earnedScoreGraph.fillAlphas = 0.8;
    earnedScoreGraph.valueAxis = earnedScoreAxis; // indicate which axis should be used
    earnedScoreGraph.balloonText = "平台赚: [[value]] sco";
    earnedScoreGraph.legendValueText = "[[value]] sco";
    earnedScoreGraph.legendPeriodValueText = "total: [[value.sum]] sco";
    earnedScoreGraph.lineColor = "#FFCC00";
//        earnedScoreGraph.lineColor = "#263138";
    earnedScoreGraph.dashLengthField = "dashLength";
    earnedScoreGraph.alphaField = "alpha";
    chart.addGraph(earnedScoreGraph);

    var userCostsGraph = new AmCharts.AmGraph();
    userCostsGraph.title = "User Costs";
    userCostsGraph.valueField = "userCosts";
    userCostsGraph.type = "line";
    userCostsGraph.valueAxis = userCostsAxis; // indicate which axis should be used
    userCostsGraph.lineColor = "#34A853";
    userCostsGraph.balloonText = "用户消费: [[value]] sco";
    userCostsGraph.legendPeriodValueText = "total: [[value.sum]] sco";
    userCostsGraph.lineThickness = 1;
    userCostsGraph.legendValueText = "[[value]] sco";
    userCostsGraph.bullet = "diamond";
    userCostsGraph.bulletBorderColor = "#34A853";
    userCostsGraph.bulletBorderThickness = 1;
    userCostsGraph.bulletBorderAlpha = 1;
    userCostsGraph.dashLengthField = "dashLength";
    chart.addGraph(userCostsGraph);

    // duration graph
    var alipayDrawOutsGraph = new AmCharts.AmGraph();
    alipayDrawOutsGraph.title = "Alipy Draw Out";
    alipayDrawOutsGraph.valueField = "alipayDrawouts";
    alipayDrawOutsGraph.type = "line";
    alipayDrawOutsGraph.valueAxis = alipayDrawOutsAxis; // indicate which axis should be used
    alipayDrawOutsGraph.lineColor = "#3A90FF";
    alipayDrawOutsGraph.balloonText = "支付宝提现:  [[value]]  ￥";
    alipayDrawOutsGraph.legendPeriodValueText = "total: [[value.sum]] ￥";
    alipayDrawOutsGraph.lineThickness = 1;
    alipayDrawOutsGraph.legendValueText = "[[value]] ￥";
    alipayDrawOutsGraph.bullet = "square";
    alipayDrawOutsGraph.bulletBorderColor = "#786c56";
    alipayDrawOutsGraph.bulletBorderThickness = 1;
    alipayDrawOutsGraph.bulletBorderAlpha = 1;
    alipayDrawOutsGraph.dashLengthField = "dashLength";
    chart.addGraph(alipayDrawOutsGraph);

    // CURSOR
    var chartCursor = new AmCharts.ChartCursor();
    chartCursor.zoomable = false;
    chartCursor.categoryBalloonDateFormat = "DD";
    chartCursor.cursorAlpha = 0;
    chartCursor.valueBalloonsEnabled = true;
    chart.addChartCursor(chartCursor);

    // LEGEND
    var legend = new AmCharts.AmLegend();
    legend.bulletType = "round";
    legend.equalWidths = false;
    legend.valueWidth = 120;
    legend.useGraphSettings = true;
    legend.color = "#FFFFFF";
    chart.addLegend(legend);

    // WRITE
    chart.write("latestMonth");

    clearAdlink();
}

function drawChannelsEarnedStatGraph(channelsDataContainer,chartData) {
    var chart;
    // SERIAL CHART
    chart = new AmCharts.AmSerialChart();
//                    chart.pathToImages = "http://www.amcharts.com/lib/3/images/";
    chart.marginTop = 10;
    chart.marginRight = 0;
    chart.dataProvider = chartData;
    chart.categoryField = "date";
    chart.dataDateFormat = "YYYY-MM-DD";
    chart.pathToImages = "/resources/img/";

    // AXES
    // category
    var categoryAxis = chart.categoryAxis;
    categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
    categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
    categoryAxis.inside = true;
    categoryAxis.gridAlpha = 0;
    categoryAxis.tickLength = 0;
    categoryAxis.axisAlpha = 0;

    // value
    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.dashLength = 4;
    valueAxis.axisAlpha = 0;
    chart.addValueAxis(valueAxis);

    $(channelsDataContainer).each(function (index, channel) {
       var lineColor = channel.channelInfo.color;
       var valueField = channel.channelInfo.code;
       var balloonText = channel.channelInfo.name;

        var line = new AmCharts.AmGraph();
//        line.lineColor = lineColor;
//        line.negativeLineColor =lineColor;
//        line.bulletBorderColor = lineColor;

        line.bullet = "round";
        line.bulletSize = 8;

        line.bulletBorderThickness = 2;
        line.bulletBorderAlpha = 1;
        line.connect = false; // this makes the graph not to connect data points if data is missing
        line.lineThickness = 2;
        line.valueField = valueField;
        line.balloonText = balloonText + " <b><span style='font-size:14px;'>[[value]] sco</span></b>";
        line.legendPeriodValueText = "total: [[value.sum]] score";
        line.title = balloonText;
        line.legendValueText = "[[value]]  score";
        chart.addGraph(line);
    });

    // CURSOR
    var chartCursor = new AmCharts.ChartCursor();
    chartCursor.cursorAlpha = 0;
    chartCursor.cursorPosition = "mouse";
    chartCursor.categoryBalloonDateFormat = "YYYY-MM-DD";
    chartCursor.graphBulletSize = 2;
    chartCursor.pan = true; // set it to fals if you want the cursor to work in "select" mode
    chart.addChartCursor(chartCursor);

    // LEGEND
    var legend = new AmCharts.AmLegend();
    legend.bulletType = "round";
    legend.equalWidths = false;
    legend.valueWidth = 120;
    legend.useGraphSettings = false;
    legend.color = "#000000";
    legend.marginTop = 20;
    legend.paddingTop = 50;
    legend.offset = 50;
    chart.addLegend(legend);


    // SCROLLBAR
    var chartScrollbar = new AmCharts.ChartScrollbar();
    chart.addChartScrollbar(chartScrollbar);
    chart.write("dailyChannelEarned");
    clearAdlink();
}

function pageNav(pageNo) {
    $("input[name='pageNo']").val(pageNo);
    $("#queryForm button").click();
}

function submitForm(targetUrl) {
    if (targetUrl && targetUrl != null && targetUrl != '') {
        $("#queryForm").attr("action", targetUrl);
    }
    $("#queryForm").submit();
}

function batchOperForm() {
    $('input[name="id"][type="checkbox"]:checked').each(function(index,data){
    });
}

function batchSelector(ele) {
    var value = ele.checked;
    $('input[name="id"][type="checkbox"]').each(function (index, data) {
        data.checked=value;
    });
}


function mergePayAccount(accountNumber) {
    for (var index = 1; index <= accountNumber; index++) {
        var drawouts = $("tr[payaccountindex=" + index + "]");
        var drawoutsCount = drawouts.length;
//        console.info("drawouts count is " + drawoutsCount);
        var firstLine = drawouts[0];
        var amountSum = 0;
        for (var countIndex = 0; countIndex < drawoutsCount; countIndex++) {
            amountSum = amountSum + new Number($($(drawouts[countIndex]).children()[5]).text());
        }
        for (var countIndex = 1; countIndex < drawoutsCount; countIndex++) {
            $($(drawouts[countIndex]).children()[9]).remove();
        }
//        console.info("tr[payaccountindex="+index+"],sumAmount is "+amountSum);
        $($(firstLine).children()[9]).attr("rowspan", drawoutsCount).append("<input type='checkbox' onclick='mergePayBatchSelect("+index+")' />" + amountSum);
    }
}

function mergePayBatchSelect(accountNumber) {
    $("tr[payaccountindex=" + accountNumber + "] input[value]").each(function (index, ele) {
        $(ele).click();
    });
}


function getChannelDailyStatData(channelCode) {
    var reData;
    $.ajax({
        method: "get",
        cache: true,
        async: false,
        url: "/admin/stat/channel-daily/" + channelCode + "/",
        success: function (singleChannelStatData) {
            reData = singleChannelStatData.channelDailyStats;
        }
    });
    return reData;
}

function getChannelMothlyStatData(channelCode) {
    var reData;
    $.ajax({
        method: "get",
        cache: true,
        async: false,
        url: "/admin/stat/channel-monthly/" + channelCode + "/",
        success: function (singleChannelStatData) {
            reData = singleChannelStatData;
        }
    });
    return reData;
}

function packageChartData(sources) {
    var reChartData = new Array();
    $(sources).each(function (index1, resource) {
        $(resource.stat).each(function (index2, stat) {
            var key = resource.channelInfo.code;
            var value = stat[1];
            var date = stat[0];
            pushData(reChartData,key,date,value);
        });

    });
    return reChartData;
}

function pushData(container, key, date, value) {
    var controller = true;
    $(container).each(function (index, ele) {
            if (ele.date == date) {
                $(ele).attr(key, value);
                $(ele).attr("total",value+$(ele).attr("total"))
                controller = false;
                return false;
            }
        }
    );
    if (controller) {
        var newEle = new Object();
        $(newEle).attr(key, value);
        $(newEle).attr("date", date);
        $(newEle).attr("total",value);
        container.push(newEle);
    }
}

function  amGraphChart(channelsDataContainer,chartData,pageLocation) {
    var chart = new AmCharts.AmSerialChart();
    chart.dataProvider = chartData;
    chart.categoryField = "date";
    chart.plotAreaBorderAlpha = 0.2;
//        chart.rotate = true;

    // AXES
    // Category
    var categoryAxis = chart.categoryAxis;
    categoryAxis.gridAlpha = 0.1;
    categoryAxis.axisAlpha = 0;
    categoryAxis.gridPosition = "start";

    // value
    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.stackType = "regular";
    valueAxis.gridAlpha = 0.1;
    valueAxis.axisAlpha = 0;
    chart.addValueAxis(valueAxis);

    $(channelsDataContainer).each(function (index, channel) {
        var lineColor = channel.channelInfo.color;
        var valueField = channel.channelInfo.code;
        var balloonText = channel.channelInfo.name;

        var graph = new AmCharts.AmGraph();
        graph.title = balloonText;
        graph.labelText = "[[value]]";
        graph.valueField = valueField;
        graph.type = "column";
        graph.lineAlpha = 0;
        graph.fillAlphas = 1;
        graph.lineColor = lineColor;
        graph.balloonText = "<b><span style='color:" + lineColor + "'>[[title]]</b></span><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>";
        chart.addGraph(graph);
    });

    var graph = new AmCharts.AmGraph();
    graph.type = "line";
//        graph2.type = "smoothedLine";
    graph.title = "Totally";
    graph.valueField = "total";
    graph.lineThickness = 3;
    graph.bullet = "round";
    graph.bulletBorderThickness = 3;
    graph.bulletBorderAlpha = 1;
    graph.dashLengthField = "dashLengthLine";
    graph.balloonText = "<span style='font-size:13px;'>[[title]] is [[category]]:<b>[[value]]</b> [[additional]]</span>";
    chart.addGraph(graph);

    var legend = new AmCharts.AmLegend();
//        legend.position = "right";
    legend.borderAlpha = 0.3;
    legend.horizontalGap = 10;
    legend.switchType = "v";
    chart.addLegend(legend);

    // WRITE
    chart.write(pageLocation);
    clearAdlink()
}