package com.cjteam.xiao.stat.service;

import com.cjteam.xiao.stat.model.ChannelsDailyStat;
import com.cjteam.xiao.stat.model.ComprehensiveStatInfo;
import com.cjteam.xiao.stat.model.UserStat;

import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-16
 */
public interface StatisticsService {
    ChannelsDailyStat channelStat(String appId);
    ChannelsDailyStat channelStat(String appId,String channelCode);

    UserStat statUser(String appId,String userId);

    List<Object[]> integralScoreDailyStat(String appId);

    List<Object[]> integralScoreMonthlyStat(String appId);
    List<Object[]> integralScoreMonthlyStat(String appId,String channelCode);

    List<Object[]> integralScoreCategoryStat(String appId);

    List<ComprehensiveStatInfo> comprehensiveStat(String appId);
}
