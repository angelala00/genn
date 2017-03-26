package com.cjteam.xiao.stat.model;

import com.cjteam.xiao.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-16
 */
public class UserStat implements Serializable {
    private User user;
    private Long onlineDays = 0L;
    private Long averageEarndScore = 0L;
    private Long totalyEarnedScore = 0L;
    private Long totalyExchangedMoney = 0L;
    List<Object[]> earndSocreOnChannels;
    List<Object[]> exchangeedMoneyOnProducts;
    private Long highSocreEarnedOneDay = 0L;
    private Long highExchangedMoneyOneDay = 0L;

    public UserStat() {
    }

    public UserStat(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getOnlineDays() {
        return onlineDays;
    }

    public void setOnlineDays(Long onlineDays) {
        if (null != onlineDays)
            this.onlineDays = onlineDays;
    }

    public Long getAverageEarndScore() {
        if (onlineDays <= 1)
            return getTotalyEarnedScore();
        return getTotalyEarnedScore() / getOnlineDays();
    }

    public void setAverageEarndScore(Long averageEarndScore) {
        if (null != averageEarndScore)
            this.averageEarndScore = averageEarndScore;
    }

    public Long getTotalyEarnedScore() {
        return totalyEarnedScore;
    }

    public void setTotalyEarnedScore(Long totalyEarnedScore) {
        if (null != totalyEarnedScore)
            this.totalyEarnedScore = totalyEarnedScore;
    }

    public Long getTotalyExchangedMoney() {
        return totalyExchangedMoney;
    }

    public void setTotalyExchangedMoney(Long totalyExchangedMoney) {
        if (null != totalyExchangedMoney)
            this.totalyExchangedMoney = totalyExchangedMoney;
    }

    public List<Object[]> getEarndSocreOnChannels() {
        return earndSocreOnChannels;
    }

    public void setEarndSocreOnChannels(List<Object[]> earndSocreOnChannels) {
        this.earndSocreOnChannels = earndSocreOnChannels;
    }

    public List<Object[]> getExchangeedMoneyOnProducts() {
        return exchangeedMoneyOnProducts;
    }

    public void setExchangeedMoneyOnProducts(List<Object[]> exchangeedMoneyOnProducts) {
        this.exchangeedMoneyOnProducts = exchangeedMoneyOnProducts;
    }

    public Long getHighSocreEarnedOneDay() {
        return highSocreEarnedOneDay;
    }

    public void setHighSocreEarnedOneDay(Long highSocreEarnedOneDay) {
        if (null != highSocreEarnedOneDay)
            this.highSocreEarnedOneDay = highSocreEarnedOneDay;
    }

    public Long getHighExchangedMoneyOneDay() {
        return highExchangedMoneyOneDay;
    }

    public void setHighExchangedMoneyOneDay(Long highExchangedMoneyOneDay) {
        if (null != highExchangedMoneyOneDay)
            this.highExchangedMoneyOneDay = highExchangedMoneyOneDay;
    }

    @Override
    public String toString() {
        return "UserStat{" +
                "user=" + user +
                ", onlineDays=" + onlineDays +
                ", averageEarndScore=" + getAverageEarndScore() +
                ", totallyEarnedScore=" + totalyEarnedScore +
                ", totallyExchangedMoney=" + totalyExchangedMoney +
                ", earndSocreOnChannels=" + earndSocreOnChannels +
                ", exchangeedMoneyOnProducts=" + exchangeedMoneyOnProducts +
                ", highSocreEarnedOneDay=" + highSocreEarnedOneDay +
                ", highExchangedMoneyOneDay=" + highExchangedMoneyOneDay +
                '}';
    }
}
