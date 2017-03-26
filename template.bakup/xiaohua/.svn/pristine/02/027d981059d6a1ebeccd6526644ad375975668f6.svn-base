package com.cjteam.xiao.stat.service.impl;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.stat.model.ChannelsDailyStat;
import com.cjteam.xiao.stat.model.ComprehensiveStatInfo;
import com.cjteam.xiao.stat.model.UserStat;
import com.cjteam.xiao.stat.repository.ChannelDailyStatRepository;
import com.cjteam.xiao.stat.service.StatisticsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by ChenLong
 * Date: 13-11-16
 */
@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {
    private static final Logger log = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Override
    public ChannelsDailyStat channelStat(String appId) {
        ChannelsDailyStat result = null;
        List<Object[]> statResult = channelDailyStatRepository.queryDailyStatForChannel(appId);
        if (CollectionUtils.isNotEmpty(statResult)) {
            result = new ChannelsDailyStat();
            result.setChannelDailyStats(statResult);
        }
        return result;
    }

    @Override
    public UserStat statUser(String appId,String userId) {
        User user = userService.getOne(appId,userId);
        Assert.notNull(user, "user not exist.");
        log.debug("stat user {} ", user);
        UserStat userStat = new UserStat(user);

        BigInteger integerTmp = null;
        integerTmp = channelDailyStatRepository.countUserIntegralDays(user.getAppId(),user.getUniqueUserId());
        if (null != integerTmp)
            userStat.setOnlineDays(integerTmp.longValue());
        integerTmp = null;

        BigDecimal tmp = null;
        tmp = channelDailyStatRepository.sumTotalyEarnedScore(user.getAppId(),user.getUniqueUserId());
        if (null != tmp)
            userStat.setTotalyEarnedScore(tmp.longValue());
        tmp = null;

        tmp = channelDailyStatRepository.sumTotalyExchangedMoney(user.getAppId(),user.getUniqueUserId());
        if (null != tmp)
            userStat.setTotalyExchangedMoney(tmp.longValue());
        tmp = null;


        tmp = channelDailyStatRepository.highSocreEarnedOneDay(user.getAppId(),user.getUniqueUserId());
        if (null != tmp)
            userStat.setHighSocreEarnedOneDay(tmp.longValue());
        tmp = null;
        tmp = channelDailyStatRepository.highExchangedMoneyOneDay(user.getAppId(),user.getUniqueUserId());
        if (null != tmp)
            userStat.setHighExchangedMoneyOneDay(tmp.longValue());
        tmp = null;

        userStat.setEarndSocreOnChannels(channelDailyStatRepository.statEarndSocreOnChannels(user.getAppId(),user.getUniqueUserId()));
        userStat.setExchangeedMoneyOnProducts(channelDailyStatRepository.statExchangedMoneyOnProducts(user.getAppId(),user.getUniqueUserId()));

        log.debug("user {} stated result is {}", user, userStat);
        return userStat;
    }

    @Override
    public List<Object[]> integralScoreDailyStat(String appId) {
        return channelDailyStatRepository.integralDailyStat(appId);
    }
    @Override
    public List<Object[]> integralScoreMonthlyStat(String appId) {
        return channelDailyStatRepository.integralMonthlyStat(appId);
    }

    @Override
    public List<Object[]> integralScoreCategoryStat(String appId){
        return channelDailyStatRepository.integralScoreCategoryStat(appId);
    }

    @Override
    public List<ComprehensiveStatInfo> comprehensiveStat(String appId) {
        List<Object[]> channelsReturnedScore = channelDailyStatRepository.sumChannelsReturnedScoreByDate(appId);
        List<Object[]> userCosts = channelDailyStatRepository.userCostsByDate(appId);
        List<Object[]> alipayDrawouts = channelDailyStatRepository.alipayDrawoutsByDate(appId);
        Map<String, ComprehensiveStatInfo> mapContainer = new HashMap<String, ComprehensiveStatInfo>(30);
        String dateKey;
        ComprehensiveStatInfo tmp = null;
        if (CollectionUtils.isNotEmpty(channelsReturnedScore)) {
            for (Object[] value : channelsReturnedScore) {
                dateKey = String.valueOf(value[0]);
                if (!mapContainer.containsKey(dateKey))
                    mapContainer.put(dateKey, new ComprehensiveStatInfo(dateKey));
                tmp = mapContainer.get(dateKey);
                tmp.setEarnedScore(Integer.valueOf(String.valueOf(value[1])));
            }
        }
        if (CollectionUtils.isNotEmpty(userCosts)) {
            for (Object[] value : userCosts) {
                dateKey = String.valueOf(value[0]);
                if (!mapContainer.containsKey(dateKey))
                    mapContainer.put(dateKey, new ComprehensiveStatInfo(dateKey));
                tmp = mapContainer.get(dateKey);
                tmp.setUserCosts(Integer.valueOf(String.valueOf(value[1])));
            }
        }
        if (CollectionUtils.isNotEmpty(alipayDrawouts)) {
            for (Object[] value : alipayDrawouts) {
                dateKey = String.valueOf(value[0]);
                if (!mapContainer.containsKey(dateKey))
                    mapContainer.put(dateKey, new ComprehensiveStatInfo(dateKey));
                tmp = mapContainer.get(dateKey);
                tmp.setAlipayDrawouts(Double.valueOf(String.valueOf(value[1])));
            }
        }
        ComprehensiveStatInfo[] comprehensiveStatInfosTmp = new ComprehensiveStatInfo[mapContainer.values().size()];
        mapContainer.values().toArray(comprehensiveStatInfosTmp);
        List<ComprehensiveStatInfo> result = Arrays.asList(comprehensiveStatInfosTmp);
        Collections.sort(result, new Comparator<ComprehensiveStatInfo>() {
            @Override
            public int compare(ComprehensiveStatInfo left, ComprehensiveStatInfo right) {
                Date leftDate = DateTime.parse(left.getDate()).toDate();
                Date rightDate = DateTime.parse(right.getDate()).toDate();

                return leftDate.compareTo(rightDate);
            }
        });
        return result;
    }

    @Override
    public ChannelsDailyStat channelStat(String appId, String channelCode) {
        if(StringUtils.isBlank(channelCode))
            return null;
        ChannelsDailyStat result = null;
        List<Object[]> statResult = channelDailyStatRepository.queryDailyStatForChannel(appId,channelCode);
        if (CollectionUtils.isNotEmpty(statResult)) {
            result = new ChannelsDailyStat();
            result.setChannelDailyStats(statResult);
        }
        return result;
    }

    @Override
    public List<Object[]> integralScoreMonthlyStat(String appId, String channelCode) {
        if(StringUtils.isBlank(channelCode))
            return null;
        return channelDailyStatRepository.integralMonthlyStat(appId,channelCode);
    }

    @Autowired
    ChannelDailyStatRepository channelDailyStatRepository;
    @Autowired
    UserService userService;
}
