package com.cjteam.xiao.stat.model;

import org.joda.time.DateTime;

/**
 * Created by ChenLong
 * Date: 13-11-22
 */
public class ComprehensiveStatInfo {
    String date;
    Integer earnedScore = 0;  // all channels returned score,platform earned
    Integer userCosts = 0;      //user charge for mobile or  qq ,and draw outs Integero alipay no.
    Double alipayDrawouts = 0.0;     // special for alipay draw outs stat

    public ComprehensiveStatInfo() {
    }

    public ComprehensiveStatInfo(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate(DateTime date) {
        this.date = date.toString("yyyy-MM-dd");
    }

    public Integer getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(Integer earnedScore) {
        if (null != earnedScore)
            this.earnedScore = earnedScore;
    }

    public Integer getUserCosts() {
        return userCosts;
    }

    public void setUserCosts(Integer userCosts) {
        if (null != userCosts)
            this.userCosts = userCosts;
    }

    public Double getAlipayDrawouts() {
        return alipayDrawouts;
    }

    public void setAlipayDrawouts(Double alipayDrawouts) {
        if (null != alipayDrawouts)
            this.alipayDrawouts = alipayDrawouts;
    }

    public Integer getSurplus() {
        return getEarnedScore() - getUserCosts();
    }

    public Integer getSurplusWeigth() {
        int re = getSurplus() / 100;
        return re < 0 ? 1 : re > 10 ? 10 : re;
    }


    @Override
    public String toString() {
        return "ComprehensiveStatInfo{" +
                "date='" + date + '\'' +
                ", earnedScore=" + earnedScore +
                ", userCosts=" + userCosts +
                ", alipayDrawouts=" + alipayDrawouts +
                ", surplus=" + getSurplus() +
                ", surplusWeigth=" + getSurplusWeigth() +
                '}';
    }
}
