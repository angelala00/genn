package com.cjteam.xiao.stat.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-16
 */
public class ChannelsDailyStat {
    private Date latestStatTime;
    private List<Object[]> channelDailyStats;
    private String header = "Date,DUOMENG,AIPU,YIJIFEN,YOUMI,ADWO,LIMEI";

    public ChannelsDailyStat() {
        channelDailyStats = new ArrayList<Object[]>(10);
        latestStatTime = new Date();
    }

    public Date getLatestStatTime() {
        return latestStatTime;
    }

    public void setLatestStatTime(Date latestStatTime) {
        this.latestStatTime = latestStatTime;
    }

    public List<Object[]> getChannelDailyStats() {
        return channelDailyStats;
    }

    public void setChannelDailyStats(List<Object[]> channelDailyStats) {
        this.channelDailyStats = channelDailyStats;
    }

    public void append(Object[] channelDailyStat) {
        channelDailyStats.add(channelDailyStat);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString()).append(":{");
        sb.append("statTime:").append(getLatestStatTime().toString()).append("[");
        for (Object[] stat : getChannelDailyStats()) {
            sb.append(Arrays.toString(stat));
        }
        sb.append("]}");
        return sb.toString();
    }
}
