<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="../common/path.jsp"%>
<html>
<head>
    <title>State Information!</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div class="header">
    <h1>State</h1>
</div>
<div class="content">
    <h2 class="content-subhead">Platform Integrated(Latest Month)</h2>

    <div class="pure-g">
        <div class="pure-u-1">
            <div id="latestMonth" style="width: 99%; height: 362px; background-color:#161616"></div>
        </div>
    </div>
    <%--<h2 class="content-subhead">Platform Integrated(Stat By Month)</h2>
    <div class="pure-g">
        <div class="pure-u-1">
            <div id="byMonth" style="width: 99%; height: 362px; background-color:#161616"></div>
        </div>
    </div>--%>
    <h2 class="content-subhead">Daily Channels Returned Scores Stat</h2>

    <div class="pure-g">
        <div class="pure-u-1">
            <div id="dailyChannelEarned" style="width: 99%; height: 500px;"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path}/resources/js/dygraph-combined.js"></script>
<script type="text/javascript" src="${path}/resources/js/amcharts/amcharts.js"></script>
<script type="text/javascript" src="${path}/resources/js/amcharts/serial.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            method: "get",
            url: "${path}/channels-used/?_t" + new Date().getTime()+"&appId=xiaohua",
            success: function (channels) {
                var channelsDataContainer = new Array();
                $(channels).each(function (index, channel) {
                    var channelDataContainer = {};
                    channelDataContainer.channelInfo = channel;
                    channelDataContainer.stat = getChannelDailyStatData(channel.code);
                    channelsDataContainer.push(channelDataContainer);
                });
//                console.info(channelsDataContainer);
                var chartData = packageChartData(channelsDataContainer);
//                console.info(chartData);
                drawChannelsEarnedStatGraph(channelsDataContainer,chartData);
            }
        });

        $.ajax({
            method: "post",
            url: "${path}/admin/stat/comprehensive?_t" + new Date().getTime()+"&appId=xiaohua",
            success: function (data) {
                var chartData = new Array();
                $(data.comprehensiveStatInfos).each(function (index, comprehensiveStat) {
                    var data = {};
                    data.date = comprehensiveStat.date;
                    data.earnedScore = comprehensiveStat.earnedScore;
                    data.userCosts = comprehensiveStat.userCosts;
                    data.alipayDrawouts = comprehensiveStat.alipayDrawouts;
                    data.surplus = comprehensiveStat.surplus;
                    data.surplusWeigth = comprehensiveStat.surplusWeigth;
                    chartData.push(data);
                });
//                console.info(chartData);
                drawComprehensiveStatGraph(chartData);
            }
        });
    });
</script>
</body>
</html>
