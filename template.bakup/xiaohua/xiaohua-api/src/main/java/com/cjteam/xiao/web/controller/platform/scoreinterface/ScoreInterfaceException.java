package com.cjteam.xiao.web.controller.platform.scoreinterface;

import com.cjteam.xiao.model.Integral;
import org.joda.time.DateTime;

/**
 * Created by ChenLong on 2014/4/3.
 */
public class ScoreInterfaceException extends Exception {
    private String appId;

    private String  channelCode;
    private String userSpecialKey;

    private String adId;
    private String adName;

    private String orderId;
    private String orderTime;

    private String score;

    private String message;
    private ScoreInterfaceEnum causeType;

    public ScoreInterfaceException(Integral integral, ScoreInterfaceEnum causeType) {

        setAppId(integral.getAppId());

        setChannelCode(integral.getChannel()==null?"":integral.getChannel().getCode());
        setUserSpecialKey(integral.getUser()==null?"":integral.getUser().getSpecialKey());
        setAdId(integral.getAdId());
        setAdName(integral.getAdName());
        setOrderId(integral.getOrderId());
        setOrderTime(new DateTime(integral.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss"));
        setScore(String.valueOf(integral.getScore()));

        setCauseType(causeType);
    }


    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getUserSpecialKey() {
        return userSpecialKey;
    }

    public void setUserSpecialKey(String userSpecialKey) {
        this.userSpecialKey = userSpecialKey;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ScoreInterfaceEnum getCauseType() {
        return causeType;
    }

    public void setCauseType(ScoreInterfaceEnum causeType) {
        this.causeType = causeType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
