package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.DailyChannelIntegralStat;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.repository.DailyChannelIntegralStatRepository;
import com.cjteam.xiao.repository.UserRepository;
import com.cjteam.xiao.service.DailyChannelIntegralStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class DailyChannelIntegralStatServiceImpl implements DailyChannelIntegralStatService {
    private static final Logger log = LoggerFactory.getLogger(DailyChannelIntegralStatServiceImpl.class);

    @Override
    public DailyChannelIntegralStat checkScore(Integer id, String channelCode, Date date) {
        User user = userRepository.findOne(id);
        if (user != null) {
            DailyChannelIntegralStat dailyChannelIntegralStats = dailyChannelIntegralStatRepository.getOneByAppIdAndUserIdAndChannel(user.getAppId(),user.getUniqueUserId(), channelCode, date);
            return dailyChannelIntegralStats;
        } else {
            log.warn("checkScore method user id:" + id + " is not exist");
            return null;
        }
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    DailyChannelIntegralStatRepository dailyChannelIntegralStatRepository;
}
