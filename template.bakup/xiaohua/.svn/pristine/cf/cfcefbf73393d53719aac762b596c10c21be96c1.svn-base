<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>${entity.user.nickName}</h1>
    <c:if test="${!entity.user.isBlack}"><a href="black/${entity.user.id}"><fmt:message key="user.black"/></a></c:if>
    <c:if test="${entity.user.isBlack}"><a href="white/${entity.user.id}"><fmt:message key="user.white"/></a></c:if>
</div>
<div class="content">
    <div class="pure-g grid-example">
        <div class="pure-u-1-3">
            <div class="l-box">
                <h3>BaseInfo</h3>

                <p>
                    nickName:<a>${entity.user.nickName}</a><br/>
                    mobilePhone:<a>${entity.user.mobilePhone}</a><br/>
                    isBlack:<a>${entity.user.isBlack}</a><br/>
                    registTime:<a>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.user.createTime}"/> </a><br/>
                    totaoScore : <a>${entity.user.surplus}</a>
                </p>
            </div>
        </div>

        <div class="pure-u-2-3">
            <div class="l-box">
                <h3>User Identity</h3>

                <p>
                    No.:<a>${entity.user.id}</a><br/>
                    userId:<a>${entity.user.userId}</a><br/>
                    mac:<a>${entity.user.mac}</a><br/>
                    token:<a>${entity.user.token}</a><br/>
                    openUdid:<a>${entity.user.openUdid}</a>
                </p>
            </div>
        </div>
        <div class="pure-u-1-3">
            <div class="l-box">
                <h3>Integrals & Exchanges</h3>
                <p>
                    onlineDays.:<a>${entity.onlineDays}</a><br/>
                    averageEarndScore:<a>${entity.averageEarndScore}</a><br/>
                    totallyEarnedScore:<a>${entity.totalyEarnedScore}</a><br/>
                    totallyExchangedMoney:<a>${entity.totalyExchangedMoney}</a><br/>
                    highSocreEarnedOneDay:<a>${entity.highSocreEarnedOneDay}</a>    <br/>
                    highExchangedMoneyOneDay:<a>${entity.highExchangedMoneyOneDay}</a>
                </p>
            </div>
        </div>
        <div class="pure-u-2-3">
            <div class="l-box">
                <h3>Other Important Info</h3>

                <p>
                    <fmt:message key="label.integral.record"/> :
                                                                    <a target="_blank"  class="pure-button pure-button-primary pure-button-xsmall"  href="/admin/integrals/user-integrals/${entity.user.userId}/0">
                                                                   <fmt:message key="here"/>
                                                                   </a></p>
                <p><fmt:message key="label.integral.exchange"/>  :
                                                                <a target="_blank"  class="pure-button pure-button-primary pure-button-xsmall"  href="/admin/exchanges/user-exchanges/${entity.user.userId}/0">
                                                                    <fmt:message key="here"/>
                                                                </a><br/>
                </p>
            </div>
        </div>
    </div>
    <div class="pure-g">
        <div class="pure-u-1-2">
            <div class="l-box">
                <h3><fmt:message key="stat.user.exchange"/></h3>
                <div id="statExchange" style="width: 100%;height: 500px;"></div>
                <h4></h4>
            </div>
        </div>

        <div class="pure-u-1-2">
            <div class="l-box">
                <h3><fmt:message key="stat.user.integral"/></h3>
                <div id="statIntegral" style="width: 100%;height: 500px;"></div>
                <h4></h4>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/amcharts/amcharts.js"></script>
<script type="text/javascript" src="/resources/js/amcharts/serial.js"></script>
<script type="text/javascript" src="/resources/js/amcharts/pie.js"></script>
<script type="text/javascript">
    var exchangedMoney = [];
    <c:forEach var="productExchange" items="${entity.exchangeedMoneyOnProducts}" varStatus="status">
    var data = {};
    data.product = "${productExchange[0]}";
    data.score = ${productExchange[1]};
    exchangedMoney.push(data);
    </c:forEach>

    var earndScore = [];
    <c:forEach var="channelScore" items="${entity.earndSocreOnChannels}" varStatus="status">
    var data = {};
    data.type = "${channelScore[0]}";
    data.score = ${channelScore[1]};
    earndScore.push(data);
    </c:forEach>

    var chart1;
    var chart2;
    var legend1;
    var legend2;
    AmCharts.ready(function () {
        // PIE CHART
        chart1 = new AmCharts.AmPieChart();
        chart1.dataProvider = exchangedMoney;
        chart1.titleField = "product";
        chart1.valueField = "score";
        chart1.outlineColor = "#FFFFFF";
        chart1.outlineAlpha = 0.8;
        chart1.outlineThickness = 2;
        chart1.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";

        // LEGEND
        legend1 = new AmCharts.AmLegend();
        legend1.align = "center";
        legend1.markerType = "circle";
        chart1.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
        chart1.addLegend(legend1);

        // WRITE
        chart1.write("statExchange");
        clearAdlink();
    });
    AmCharts.ready(function () {
        // PIE CHART
        chart2 = new AmCharts.AmPieChart();
        chart2.dataProvider = earndScore;
        chart2.titleField = "type";
        chart2.valueField = "score";
        chart2.outlineColor = "#FFFFFF";
        chart2.outlineAlpha = 0.8;
        chart2.outlineThickness = 2;
        chart2.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";

        legend2 = new AmCharts.AmLegend();
        legend2.align = "center";
        legend2.markerType = "circle";
        chart2.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
        chart2.addLegend(legend2);
        // WRITE
        chart2.write("statIntegral");
        clearAdlink();
    });
</script>
</body>
</html>
