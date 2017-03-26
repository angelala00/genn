package com.cjteam.xiao.service.impl;

import com.google.common.base.Joiner;
import com.cjteam.xiao.dao.IntegralDao;
import com.cjteam.xiao.model.Channel;
import com.cjteam.xiao.model.DailyChannelIntegralStat;
import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.repository.*;
import com.cjteam.xiao.service.*;
import com.cjteam.xiao.util.DMUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenLong Date: 13-9-27
 */
@Service
@Transactional
public class IntegralServiceImpl implements IntegralService {
    private static final Logger LOG = LoggerFactory.getLogger(IntegralServiceImpl.class);
    private static final Logger LOG_INTEGRAL = LoggerFactory.getLogger(IntegralServiceImpl.class.getName() + ".INTEGRAL");

    public void save(Integral integral){
        integral.setCreateTime(new Date());
        this.integralRepository.save(integral) ;
    }

    @Override
    public Page<Integral> queryIntegralsByUser(String appId, String userId, int page, int pageDefaultSize) {
        return integralRepository.findByAppIdAndUser_UserId(appId, userId, new PageRequest(page, pageDefaultSize, Sort.Direction.DESC, "createTime"));
    }

    @Override
    public String addPoints(String appId, String pointsstr, String userId, String channelCode, String orderid, String adid, String adName) {
        return addPoints(appId, pointsstr, userId, channelCode, orderid, adid, adName, null);
    }

    @Override
    public synchronized String addPoints(String appId, String pointsstr, String userId, String channelCode, String orderid, String adid, String adName, Long timestamp) {
        Integer points = new Double(NumberUtils.toDouble(pointsstr)).intValue();
        LOG.debug("points:" + points + "userId:" + userId + "channelCode:" + channelCode + "orderid:" + orderid);
        Assert.hasText(userId, "invalid param userId =" + userId);
        Date integralTime = new Date();
        if (null != timestamp) {
            integralTime.setTime(timestamp);
        }
        Channel channel = channelService.findOneByCode(appId, channelCode);

        User user = userRepository.findByAppIdAndUserId(appId, userId);
        if (user == null) {
            LOG.warn("recordincome method user userid:" + userId + " is not exist");
            LOG_INTEGRAL.warn("{}:用户 {} 不存在,order:{},adName:{},adId:{},channel:{}," +
                            "points:{},date:{}", IntegralSituation.USER_NOTEXIST, userId, orderid, adName, adid, channelCode,
                    points
            );
            return "user is not exist";
        }
        Integral integral = findOneByOrderId(appId, orderid);
        if (integral != null) {// 数据录入重复
            LOG.warn("order:" + orderid + " is exist:");
            LOG_INTEGRAL.warn("{}:用户 {} 获得积分失败，对应的订单ID {} 已经存在;channel:{},adId:{},adName:{},points:{}", IntegralSituation.ORDER_EXIST, user.getUniqueUserId(), orderid
                    , channel.getCode(), adid, adName, points);
            return "OK";
        }

        integral = findByUserIpAndChannelAndAdid(appId,user.getIp(), channel.getCode(), adid);
        if (integral != null) {// 同一个IP每个平台的广告只能得分一次
            LOG.warn("the ip {}  duplicate integral on this ad {} ", user.getUniqueUserId(), adid + StringUtils.trimToEmpty(adName));
            LOG_INTEGRAL.warn("{}:用户 {} 获得积分失败，对应的广告改IP用户已经在该渠道 获得了积分;channel:{},adId:{},adName:{},points:{}", IntegralSituation.IP_ALREADY_AWARD, user.getUniqueUserId(), channel.getCode(), adid, adName, points);
            return "the ip  duplicate integral on this ad";
        }

        integral = findByUserIdAndChannelAndAdId(appId,user.getUniqueUserId(), channel.getCode(), adid);
        if (integral != null) {// 同一广告平台，每个广告只能得分一次
            LOG.warn("the user {}  duplicate integral on this ad {} ", user.getUniqueUserId(), adid + StringUtils.trimToEmpty(adName));
            LOG_INTEGRAL.warn("{}:用户 {} 获得积分失败，对应的广告已经在该渠道 获得了积分;channel:{},adId:{},adName:{},points:{}", IntegralSituation.AD_ALREADY_AWARD, user.getUniqueUserId(), channel.getCode(), adid, adName, points);
            return "the user  duplicate integral on this ad";
        }

        Integral lastIntegral = findOneByUserIdAndChannel(appId, userId, channelCode);
        if (lastIntegral != null && DMUtils.secondsBetween(lastIntegral.getCreateTime(), new Date()) < channel.getTwiceInternal()) {
            LOG.warn("more than one time in a shot time 短时间内积分多次");
            LOG_INTEGRAL.warn("{}:用户 {} 在渠道 {} 获得积分的间隔时间小于 {}; adId:{},adName:{},points:{}", IntegralSituation.CHANNEL_TWICEINTERNAL_LIMIT,
                    user.getUniqueUserId(), channel.getCode(), channel.getTwiceInternal(), adid, adName, points);
            return "太频繁";
        }
        if(StringUtils.isNotBlank(user.getDeviceUdid())) {
            List<Integral> integrals = integralRepository.findByAppIdAndUdidAndAdIdAndStatusTrue(appId, user.getDeviceUdid(), adid);
            if (CollectionUtils.isNotEmpty(integrals)) {
                LOG.warn("该设备号已经完成此广告");
                LOG_INTEGRAL.warn("{}:用户 {} 在渠道 {}  该设备号已经完成此广告 {}; adId:{},adName:{},points:{}", IntegralSituation.UDID_ADID_EXIST,
                        user.getUniqueUserId(), channel.getCode(), user.getDeadline(), adid, adName, points);
                return "该设备号已经完成此广告";
            }
        }

        if (points > channel.getMaxScoreTimes()) {// 单次限制
            LOG.warn("userid:" + userId + " over oncePoints:" + points);
            LOG_INTEGRAL.warn("{}:用户 {} 在渠道 {} 单次获得积分数 {} 超过最大限制 {}; adId:{},adName:{},points:{}", IntegralSituation.CHANNEL_MAXSCORETIMES_LIMIT,
                    user.getUniqueUserId(), channel.getCode(), points, channel.getMaxScoreTimes(), adid, adName, points);
            return "单次积分太多";
        }

        DailyChannelIntegralStat dailyChannelIntegralStat = dailyChannelIntegralStatService.checkScore(user.getId(), channelCode, integralTime);
        if (dailyChannelIntegralStat != null && ((dailyChannelIntegralStat.getScore() + points) > channel.getMaxScoreDaily())) {// 一人一天在某一渠道限制
            LOG.warn("userid:" + userId + " over dayPoints:" + dailyChannelIntegralStat.getScore());
            LOG_INTEGRAL.warn("{}:用户 {} 在渠道 {} 单日获得积分总数 {} 超过最大限制 {}; adId:{},adName:{},points:{}", IntegralSituation.CHANNEL_MAXSCOREDAILY_LIMIT,
                    user.getUniqueUserId(), channel.getCode(), dailyChannelIntegralStat.getScore() + points, channel.getMaxScoreDaily()
                    , adid, adName, points);
//                recordincome(user, channelCode, integralTime, points, orderid, adid, adName, Boolean.FALSE, IntegralSituation.CHANNEL_MAXSCOREDAILY_LIMIT);
            return "单日积分太多";
        }

        //每个渠道单日积分次数限制:
        if (integralRepository.countByAppIdAndUser_UserIdAndChannel_CodeAndStatusTrue(user.getAppId(), userId, channelCode) > channel.getDailyTimes()) {
            LOG.info("{} on channel {} 's integral times exceeded the limit {}", userId, channelCode, channel.getDailyTimes());
            LOG_INTEGRAL.warn("{}:用户 {} 在渠道 {} 单日获得积分次数超过最大限制 {}; adId:{},adName:{},points:{}", IntegralSituation.CHANNEL_DAILYTIMES_LIMIT,
                    user.getUniqueUserId(), channel.getCode(), channel.getDailyTimes(), adid, adName, points);
//                recordincome(user, channelCode, integralTime, points, orderid, adid, adName, Boolean.FALSE, IntegralSituation.CHANNEL_DAILYTIMES_LIMIT);
            return "daily integral times exceeded the limit";
        }

        if (!regionService.check(user.getAppId(), user.getIp(), channelCode, points)) {
            LOG.info("{} on channel {} 's integral times exceeded the limit {}", userId, channelCode, channel.getDailyTimes());
            LOG_INTEGRAL.warn("{}:渠道 {} 单日获得积分总量超过区域的最大限制. adId:{},adName:{},points:{}", IntegralSituation.CHANNEL_REGION_LIMIT,
                    channel.getCode(), adid, adName, points);
            return "daily integral sum  exceeded the region limit";
        }

        if (user.getIsBlack()) {
            LOG.info("user {} is in black list .", user);
            LOG_INTEGRAL.warn("{}:用户 {} 已拉入黑名单,order:{},adName:{},adId:{},channel:{}," +
                            "points:{},date:{}", IntegralSituation.USER_INBLACKLIST, user.getUniqueUserId(), orderid, adName, adid, channelCode,
                    points, integralTime
            );
//                recordincome(user, channelCode, integralTime, points, orderid, adid, adName, Boolean.FALSE, IntegralSituation.USER_INBLACKLIST);
            return "用户违规以拉入黑名单";
        }
        return recordincome(user, channelCode, integralTime, points, orderid, adid, adName, Boolean.TRUE, null);
    }

    private Integral findByUserIpAndChannelAndAdid(String appId,String ip, String code, String adId) {
        if (StringUtils.isBlank(adId) || StringUtils.isBlank(ip))
            return null;
        return integralRepository.findByUser_IpAndChannel_CodeAndAdId(appId,ip, code, adId);
    }

    private Integral findByUserIdAndChannelAndAdId(String appId,String uniqueUserId, String code, String adId) {
        if(StringUtils.isBlank(adId))
            return null;
        return  integralRepository.findByUser_UserIdAndChannel_CodeAndAdId(appId,uniqueUserId,code,adId);
    }

    @Override
    public String addPoints(String appId,String pointsstr, String userId, String channelCode, String orderid) {
        return addPoints(appId, pointsstr, userId, channelCode, orderid, null, null);
    }



    @Override
    public Page<Integral> getByUserId(String appId, String userId, Pageable pageable) {
        Page<Integral> page = integralRepository.queryByAppIdAndUserId(appId, userId, pageable);
        return page;
    }

    @Override
    public Page<Integral> getByUserId(String appId, String userId) {
        return getByUserId(appId, userId, new PageRequest(0, 20));
    }

    @Override
    public String recordincome(User user, String channelCode, Date date, Integer points, String orderId, String adid, String adName, Boolean status, String msg) {
        Integral integral = new Integral();
        Channel channel = channelRepository.findOneByAppIdAndCode(user.getAppId(), channelCode);
        integral.setChannel(channel);
        integral.setUser(user);
        integral.setCreateTime(date);
        integral.setScore(points);
        integral.setStatus(status);
        integral.setOrderId(orderId);

        if (null != user) {
            integral.setIp(user.getIp());
            integral.setAppId(user.getAppId());
        }
        integral.setUdid(user.getDeviceUdid());

        integral.setAdId(adid);
        integral.setAdName(adName);

        return recordincome(integral);
    }

    @Override
    public String recordincome(Integral integral) {
        User user = integral.getUser();
        if (user.getIsBlack()) {
            LOG.info("user {} is in black list .", user);
            LOG_INTEGRAL.warn("{}:用户 {} 已拉入黑名单,order:{},adName:{},adId:{},channel:{}," +
                            "points:{},date:{}", IntegralSituation.USER_INBLACKLIST, user.getUniqueUserId(), integral.getOrderId(), integral.getAdName(), integral.getAdId(), integral.getChannel().getCode(),
                    integral.getScore(), integral.getCreateTime());
            return "user is in black list";
        }
        if (null != user) {
            integral.setIp(user.getIp());
        }
        integralRepository.save(integral);

      /*  LOG.info(" {} sleep",Thread.currentThread().getId());
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info(" {} wake up",Thread.currentThread().getId());*/

        DailyChannelIntegralStat dailyChannelIntegralStats = dailyChannelIntegralStatRepository.getOneByAppIdAndUserIdAndChannel(user.getAppId(), user.getUniqueUserId(), integral.getChannel().getCode(), integral.getCreateTime());
        if (dailyChannelIntegralStats == null) {
            dailyChannelIntegralStats = new DailyChannelIntegralStat();
            dailyChannelIntegralStats.setAppId(integral.getAppId());
            dailyChannelIntegralStats.setChannel(integral.getChannel().getCode());
            dailyChannelIntegralStats.setDate(integral.getCreateTime());
            dailyChannelIntegralStats.setScore(0);
            dailyChannelIntegralStats.setUserId(user.getUniqueUserId());
        }
        dailyChannelIntegralStats.setScore(dailyChannelIntegralStats.getScore() + integral.getScore());
        dailyChannelIntegralStatRepository.save(dailyChannelIntegralStats);

        if(integral.getStatus()){
            userRepository.awardScore(user.getId(),integral.getScore());
            LOG_INTEGRAL.warn("{}:用户 {} 获得积分{}-{},order:{},adName:{},adId:{}", IntegralSituation.OK, user.getUniqueUserId(), integral.getChannel().getCode(), integral.getScore(),
                    integral.getOrderId(), integral.getAdName(), integral.getAdId());
            iosMessageProvider.push(integral.getAppId(),parseMessage(integral), user.getToken());
        }

        return "OK";
    }

    private String parseMessage(Integral integral) {
        StringBuilder sb = new StringBuilder();
        sb.append(DateTime.now().toString("yy-MM-dd HH:mm"))
                .append("  获得金币 : ").append(integral.getScore());
        return sb.toString();
    }

    @Override
    public Integral findOneByUserIdAndChannel(String appId, String userId, String channelCode) {
        User user = userRepository.findByAppIdAndUserId(appId, userId);
        if (user != null) {
            return integralRepository.findOneLastest(user.getAppId(),user.getUniqueUserId(), channelCode);
        }
        return null;
    }

    @Override
    public Page<Integral> queryAll(String appId,int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, Integral.filedCreateTime);
        return integralRepository.findByAppId(appId, pageable);
    }

    @Override
    public Page<Integral> query(int pageNo, int size, QueryCondition queryCondition) {
        Pageable pageable = new PageRequest(pageNo, size, Sort.Direction.DESC, Integral.filedCreateTime);
        if (null == queryCondition)
            return integralRepository.findAll(pageable);
        Map<String, Object> filterValues = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(queryCondition.getName())) {
            filterValues.put(" user.nickName=", queryCondition.getName());
        }
        if (StringUtils.isNotBlank(queryCondition.getChannel())) {
            filterValues.put(" channel.code= ", queryCondition.getChannel());
        }
        if (null != queryCondition.getAfterDate()) {
            filterValues.put(" createTime>= ", queryCondition.getAfterDate());
        }
        if (null != queryCondition.getBeforeDate()) {
            filterValues.put(" createTime< ", queryCondition.getBeforeDate());
        }
        LOG.debug("filter key set is {} ", Joiner.on(",").join(filterValues.keySet().iterator()));
        LOG.debug("filter value collection is {} ", Joiner.on(",").join(filterValues.values().iterator()));
        return integralDao.filter(filterValues, pageable);
    }

    @Override
    public Integral findOneByOrderId(String appId, String orderId) {
        return integralRepository.findOneByOrderId(appId,orderId);
    }

    @Override
    public boolean hasIntegralByType(String appId, String userId, String integralType) {
        return integralRepository.countByAppIdAndUser_UserIdAndIntegralType_Code(appId, userId, integralType)>0;
    }

    @Override
    public List<Integral> fetchNews(String appId, int newSize, Date latestFetchTime) {
        return integralRepository.findByAppIdAndCreateTime(appId, latestFetchTime, newSize);
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    IntegralTypeRepository integralTypeRepository;
    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    IntegralRepository integralRepository;
    @Autowired
    DailyChannelIntegralStatRepository dailyChannelIntegralStatRepository;
    @Autowired
    private DailyChannelIntegralStatService dailyChannelIntegralStatService;
    @Autowired
    ChannelService channelService;
    @Autowired
    RegionService regionService;
    @Resource(name = "integralDao")
    IntegralDao integralDao;
    @Autowired
    IOSMessageProvider iosMessageProvider;
}
