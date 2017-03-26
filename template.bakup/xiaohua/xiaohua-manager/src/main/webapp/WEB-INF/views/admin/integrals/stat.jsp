<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1></h1>
</div>
<div class="content">
    <h2 class="content-subhead">Daily Stat</h2>

    <div class="pure-g">
        <div class="pure-u-1">
            <div id="dailyStatchartdiv" style="width: 100%; height: 500px;"></div>
        </div>
    </div>
    <h2 class="content-subhead">Monthly Stat</h2>

    <div class="pure-g">
        <div class="pure-u-1">
            <div id="monthlyStatchartdiv" style="width: 100%; height: 500px;"></div>
        </div>
    </div>
    <h2 class="content-subhead">Total Stat</h2>

    <div class="pure-g">
        <div class="pure-u-1">
            <div id="totalStatchartdiv" style="width: 100%; height: 562px;"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/amcharts/amcharts.js"></script>
<script type="text/javascript" src="/resources/js/amcharts/serial.js"></script>
<script type="text/javascript" src="/resources/js/amcharts/pie.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            method: "get",
            url: "/channels-used/?_t" + new Date().getTime(),
            success: function (channels) {
                var channelsDataContainer = new Array();
                $(channels).each(function (index, channel) {
                    var channelDataContainer = {};
                    channelDataContainer.channelInfo = channel;
                    channelDataContainer.stat = getChannelDailyStatData(channel.code);
                    channelsDataContainer.push(channelDataContainer);
                });
                var chartDailyData = packageChartData(channelsDataContainer);
                amGraphChart(channelsDataContainer, chartDailyData, "dailyStatchartdiv");

                monthlyChart(channels);
            }
        });
    });

    function monthlyChart(channels) {
        var channelsDataContainer = new Array();
        $(channels).each(function (index, channel) {
            var channelDataContainer = {};
            channelDataContainer.channelInfo = channel;
            channelDataContainer.stat = getChannelMothlyStatData(channel.code);
            channelsDataContainer.push(channelDataContainer);
        });
        console.info(channelsDataContainer)
        var chartMonthlyData = packageChartData(channelsDataContainer);
        console.info(chartMonthlyData);
        amGraphChart(channelsDataContainer, chartMonthlyData, "monthlyStatchartdiv");
    }

    // Make chart 2D/3D
    function setDepth() {
        if (document.getElementById("rb1").checked) {
            chart.depth3D = 0;
            chart.angle = 0;
        } else {
            chart.depth3D = 20;
            chart.angle = 30;
        }
        chart.validateNow();
    }

    var chart2;
    var legend;

    var chartData2 = [
    ];
    <c:forEach var="item" items="${entity2}" varStatus="status">
    var data = {};
    data.channel = '${item[0]}';
    data.score =${item[1]};
    chartData2.push(data);
    </c:forEach>

    AmCharts.ready(function () {
        // PIE CHART
        chart2 = new AmCharts.AmPieChart();
        chart2.dataProvider = chartData2;
        chart2.titleField = "channel";
        chart2.valueField = "score";
        chart2.outlineColor = "#FFFFFF";
        chart2.outlineAlpha = 0.8;
        chart2.outlineThickness = 2;
        chart2.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
        legend = new AmCharts.AmLegend();
        legend.align = "center";
        legend.markerType = "circle";
        legend.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
        chart2.addLegend(legend);
        // WRITE
        chart2.write("totalStatchartdiv");
        clearAdlink();
    });
</script>
</body>
</html>
