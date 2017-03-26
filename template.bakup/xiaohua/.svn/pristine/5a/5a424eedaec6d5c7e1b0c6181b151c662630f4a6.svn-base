package com.cjteam.xiao.service;


import com.cjteam.xiao.model.ExchangeUser;

import java.util.Date;

/**
 * Created by ChenLong on 14-2-27.
 */
public interface ScoreCollector {
    void addScore(String appId, String timeStamp, String point, String channelCode, String orderId, String adId, String adName);

    void doMoneyExchange();

    void doMoneyExchange(ExchangeUser exchangeUser);

    void addScore(String appId, String timeStamp, String point, String channelCode, String orderId);

    void addScore(String appId, Date createTime, Integer score, String code, String orderId, String adId, String adName);
}
