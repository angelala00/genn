package com.cjteam.xiao.stat.model;

/**
 * Created by ChenLong
 * Date: 13-11-16
 */
public class ChannelDailyStat {
    private String channel;
    private String date;
    private int dailyScore = 0;

    public ChannelDailyStat() {
    }

    public ChannelDailyStat(Object[] values) {
        setChannel(String.valueOf(values[0]));
        setDate(String.valueOf(values[1]));
        setDailyScore(Integer.valueOf(String.valueOf(values[2])));
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDailyScore() {
        return dailyScore;
    }

    public void setDailyScore(int dailyScore) {
        this.dailyScore = dailyScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString()).append(":{");
        sb.append("channel:").append(getChannel()).append(",");
        sb.append("date:").append(getDate()).append(",");
        sb.append("score:").append(getDailyScore());
        sb.append("}");
        return sb.toString();
    }
}
