package com.cjteam.xiao.service;

import com.cjteam.xiao.model.DailyChannelIntegralStat;

import java.util.Date;


public interface DailyChannelIntegralStatService {

    DailyChannelIntegralStat checkScore(Integer id, String channelCode, Date date);
}
