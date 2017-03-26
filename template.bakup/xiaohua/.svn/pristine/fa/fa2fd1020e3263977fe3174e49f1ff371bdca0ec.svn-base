package com.cjteam.xiao.manager;

import com.cjteam.xiao.model.Exchange;
import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.service.ExchangeService;
import com.cjteam.xiao.service.IntegralService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by ChenLong on 14-2-23.
 */
@Component
public class BroadCastsManager {
    private static final int newSize = 5;
    private static final int newMaxCount = 10;
    private static Date latestFetchTime = null;
    private static final LinkedList<String> casts = new LinkedList<String>();

    public List<String> fetchNews(String appId) {

        Date latestTime = getLatestFetchTime();
        if (CollectionUtils.isNotEmpty(casts) 
            && DateTime.now().withFieldAdded(DurationFieldType.minutes(), -5).isBefore(latestTime.getTime())) {
            //5 分钟内不更新
            return casts;
        }
        List<Exchange> newlyExchanges = exchangeService.fetchNews(appId, newSize, latestTime);
        List<Integral> newlyIntegrals = integralService.fetchNews(appId, newSize, latestTime);

        updateLatestFetchTime();

        if (CollectionUtils.isNotEmpty(newlyExchanges))
            packageExchangeNews(newlyExchanges);
        if (CollectionUtils.isNotEmpty(newlyIntegrals))
            packageIntegralNews(newlyIntegrals);

        if (CollectionUtils.isNotEmpty(casts) && casts.size() > newMaxCount) {
            List<String> tmps = casts.subList(casts.size() + 1 - newMaxCount, casts.size());
            casts.clear();
            casts.addAll(tmps);
        }
        return casts;
    }

    private void packageExchangeNews(List<Exchange> newlyExchanges) {
        for (Exchange exchange : newlyExchanges) {
            if (newMaxCount == casts.size()) {
//                casts.removeFirst();
            }
            casts.addLast(parseNews(exchange));
        }
    }

    private String parseNews(Exchange exchange) {
        StringBuilder sb = new StringBuilder();
        sb.append(exchange.getUser().getNickName());
        if (StringUtils.equals("QB", exchange.getProduct().getProductType().getCode())) {
            sb.append(" 兑换了 ");
            sb.append(exchange.getProduct().getInfo());
        } else if (StringUtils.equals("MOBILE", exchange.getProduct().getProductType().getCode())) {
            sb.append(" 充值了手机话费  ");
            sb.append(exchange.getProduct().getInfo());
        } else if (StringUtils.equals("ALIPAY", exchange.getProduct().getProductType().getCode())) {
            sb.append(" 支付宝提现  ");
            sb.append(exchange.getProduct().getName());
        }
        return sb.toString();
    }

    private void packageIntegralNews(List<Integral> newlyIntegrals) {
        for (Integral integral : newlyIntegrals) {
//            if (newMaxCount == casts.size()) {
//                casts.removeFirst();
//            }
            casts.addLast(parseNews(integral));
        }
    }

    private String parseNews(Integral integral) {
        StringBuilder sb = new StringBuilder();
        sb.append(integral.getUser().getNickName());
        sb.append(" 刚刚获得了 ");
        sb.append(integral.getScore());
        sb.append("金币");
        return sb.toString();
    }

    public static Date getLatestFetchTime() {
        if (latestFetchTime == null) {
            updateLatestFetchTime();
            return getLatestFetchTime();
        } else {
            return latestFetchTime;
        }
    }

    public static Date updateLatestFetchTime() {
        if (latestFetchTime == null) {
            DateTime dateTime = DateTime.now().withFieldAdded(DurationFieldType.days(), -30);
            latestFetchTime = dateTime.toDate();
            return latestFetchTime;
        } else {
            Date copyDate = latestFetchTime;
            latestFetchTime = new Date();
            return copyDate;
        }
    }

    @Autowired
    ExchangeService exchangeService;
    @Autowired
    IntegralService integralService;
}
