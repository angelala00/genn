package com.cjteam.xiao.service.impl;

import com.google.common.collect.ImmutableList;
import com.cjteam.xiao.context.ApplicationContextUtils;
import com.cjteam.xiao.model.*;
import com.cjteam.xiao.repository.*;
import com.cjteam.xiao.service.ExchangeService;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.service.ScoreCollector;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.util.ToolKits;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.StringWriter;
import java.util.*;

/**
 * Created by ChenLong on 14-2-27.
 */

@Service("scoreCollector")
@Transactional
public class ScoreCollectorImpl implements ScoreCollector {
    private static final Logger LOG = LoggerFactory.getLogger(ScoreCollectorImpl.class);

    @Override
    public void addScore(String appId, String timeStamp, String point, String channelCode, String orderId) {
        addScore(appId, timeStamp, point, channelCode, orderId, null, null);
    }

    @Override
    public void addScore(String appId, String timeStamp, String point, String channelCode, String orderId, String adId, String adName) {
        Date integralTime = null;
        try {
            integralTime = new Date(Long.valueOf(timeStamp));
        } catch (Throwable t) {
            integralTime = new Date();
        }
        int score = new Double(NumberUtils.toDouble(point)).intValue();
        addScore(appId, integralTime, score, channelCode, orderId, adId, adName);
    }

    @Override
    public void addScore(String appId, Date integralTime, Integer score, String channelCode, String orderId, String adId, String adName) {
        LOG.warn("{},{},{},{},{},{},{}", appId, integralTime, score, channelCode, orderId, adId, adName);
        User targetUser = null;
        try {
            targetUser = chooseOneUser(appId);
            Assert.notNull(targetUser, "cannot choose one user to collect this lost score");
        } catch (Exception e) {
            LOG.warn(e.getLocalizedMessage(), e);
            LOG.warn("lost score {},from channel {},orderId is {},adId is {}, and adName is {}", score, channelCode, orderId, adId, adName);
            return;
        }
        if (null == integralTime) {
            integralTime = new Date();
        }
        integralService.recordincome(targetUser, channelCode, integralTime, score, orderId, adId, adName, Boolean.TRUE, null);

        stateCollectScore(targetUser, score);

        updateCollectorEUserStatus(targetUser.getAppId(), targetUser.getUserId());
    }

    private void updateCollectorEUserStatus(String appId, String userId) {
        int limit = 185;//default : one user one day ,can draws out limit alipay
        SysConf sysConf = sysConfRepository.findByAppIdAndCode(appId, exchange_money_limit_one_user);
        if (null != sysConf && StringUtils.isNotEmpty(sysConf.getValue()) && StringUtils.isNumeric(sysConf.getValue())) {
            limit = Integer.valueOf(sysConf.getValue());
        }
        exchangeUserRepository.updateStatusIfReachTheLimit(appId, userId, limit * 100, ExchangeUser.STATUS_EXCHANGEABLE);
    }

    @Override
    public void doMoneyExchange() {
        final List<ExchangeUser> allExchangeAbleUsers = exchangeUserRepository.findByStatusLessThanOrderByStatusDesc(ExchangeUser.STATUS_EXCHANGED);
        if (CollectionUtils.isEmpty(allExchangeAbleUsers)) {
            LOG.info("there is no exchanger have any score can be used to exchange money");
            return;
        }
        Collections.shuffle(allExchangeAbleUsers);
        for(int index  =0 ;index<allExchangeAbleUsers.size();index++) {
            final int finalIndex = index;
            taskExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        ScoreCollector scoreCollector = (ScoreCollector) ApplicationContextUtils.getBean("scoreCollector");
                        scoreCollector.doMoneyExchange(allExchangeAbleUsers.get(finalIndex));
                    } catch (Throwable t) {
                        LOG.error(t.getLocalizedMessage(), t);
                    }
                }
            });
        }
    }

    @Override
    public void doMoneyExchange(ExchangeUser exchangeUser) {
        LOG.info("there is single on  exchanger {}  have any score can be used to exchange money", exchangeUser.getUserId());
        User targetUser = userRepository.findByAppIdAndUserId(exchangeUser.getAppId(), exchangeUser.getUserId());
        if (null == targetUser)
            return;
        long allPoints = userService.getSurplus(exchangeUser.getAppId(), targetUser.getId());
        LOG.info("surplus score is {} fro user {}", allPoints, targetUser.getUserId());
        Map<Product, Integer> exchangeProducts = parseAllProduct(allPoints);
        int sumExchangeMoney = 0;
        int sumExchangeScore = 0;
        LOG.info("parse to exchange product count is {}", exchangeProducts.size());
        if (exchangeProducts.size() <= 0) {
            if (ExchangeUser.STATUS_EXCHANGEABLE == exchangeUser.getStatus()) {
                exchangeUser.setStatus(ExchangeUser.STATUS_EXCHANGED);
                exchangeUserRepository.save(exchangeUser);
                return;
            }
        } else if (exchangeProducts.size() > 0) {
            for (Product product : exchangeProducts.keySet()) {
                //do exchange
                int times = exchangeProducts.get(product);
                LOG.info("times {} ", times);
                if (times > 0) {
                    String aliPayAccount = randomAlipayNo(targetUser.getAppId());
                    LOG.info("exchange money to alipay no. {}", aliPayAccount);
                    if (StringUtils.isBlank(aliPayAccount)) {
                        continue;
                    } else {
                        try {
                            exchangeService.withdrawFromAlipay(exchangeUser.getAppId(), exchangeUser.getUserId(), product.getCode(), aliPayAccount);
                            LOG.info("user {} exchanged product: {productCode:{},price:{},socre:{}}, pay for account {}", exchangeUser.getUserId(), product.getCode(), product.getPrice(), product.getScore(), aliPayAccount);
                            sumExchangeMoney += product.getPrice() / 100;
                            sumExchangeScore += product.getScore();
                            statExchangeTimeOnExchangeAccount(aliPayAccount);
                        } catch (Throwable t) {
                            continue;
                        }
                    }
                }
                break;// just execute once
            }
            //record the exchange info:
            exchangeUser.setTotallyExchangedMoney(exchangeUser.getTotallyExchangedMoney() + sumExchangeMoney);
            exchangeUserRepository.save(exchangeUser);

            userService.consumeScore(targetUser.getId(), sumExchangeScore);
            userRepository.save(targetUser);
        }

        int limit = 185;//default : one user one day ,can draws out limit alipay
        SysConf sysConf = sysConfRepository.findByAppIdAndCode(exchangeUser.getAppId(), exchange_money_limit_one_user);
        if (null != sysConf && StringUtils.isNotEmpty(sysConf.getValue()) && StringUtils.isNumeric(sysConf.getValue())) {
            limit = Integer.valueOf(sysConf.getValue());
        }
        if (exchangeUser.getPoints() < ((exchangeUser.getTotallyExchangedMoney() + 5) * 100) && exchangeUser.getPoints() >= limit * 100) {
            exchangeUser.setStatus(ExchangeUser.STATUS_EXCHANGED);
            exchangeUserRepository.save(exchangeUser);
        }
    }

    private void statExchangeTimeOnExchangeAccount(String aliPayAccount) {
        exchangeAccountRepository.appendExchangeTimes(aliPayAccount);
    }

    private static final List<String> myAlipayAccount = new ArrayList<String>(10);

    static {
        myAlipayAccount.add("2890094883@qq.com");
        myAlipayAccount.add("1904829576@qq.com");
        myAlipayAccount.add("2731072783@qq.com");
    }

    private static int rateControl = 0;
    private static int myAlipayAccountIndex = 0;

    private String randomAlipayNo(String appId) {
//        if ((++rateControl) % 10 < 1) {
//            return myAlipayAccount.get(myAlipayAccountIndex++ % myAlipayAccount.size());
//        }
        refreshExchangeAccountDrawsStat();
        return shuffleAnAlipayNo(appId);
    }

    private String shuffleAnAlipayNo(String appId) {
        SysConf drawTimeOndeDay = sysConfRepository.findByAppIdAndCode(appId, alipay_drwaout_times_oneday);
        Integer oneAlipayAccountLimitExchangeTime = Integer.MAX_VALUE;
        if (null != drawTimeOndeDay && !StringUtils.isNumeric(drawTimeOndeDay.getValue())) {
            oneAlipayAccountLimitExchangeTime = Integer.valueOf(drawTimeOndeDay.getValue());
        }
        List<ExchangeAccount> alipayAccounts = exchangeAccountRepository.findByTimesLessThanOrderByTimesAsc(oneAlipayAccountLimitExchangeTime);
        if (CollectionUtils.isEmpty(alipayAccounts))
            return null;
        return alipayAccounts.get(0).getAccount();
    }

    private void refreshExchangeAccountDrawsStat() {
        exchangeAccountRepository.refreshDrawsTimesStat();
    }

    private Map<Product, Integer> parseAllProduct(Long allPoints) {
        Map<Product, Integer> productExchangeTimes = new LinkedHashMap<Product, Integer>(0);
        if (null == allPoints || allPoints.longValue() <= 0)
            return productExchangeTimes;
        List<Product> useableProducts = productRepository.findByProductType_CodeAndValidAndPriceLessThanOrderByPriceAsc(ProductType.ALIPAY, 1, 11 * 100);
        if (CollectionUtils.isNotEmpty(useableProducts)) {
            int index = useableProducts.size() - 1;
            int tmp = 0;
            Product product = null;
            int times = 0;
            while (allPoints > 0 && index >= 0) {
                product = useableProducts.get(index);
                tmp = product.getScore();
                if (tmp <= allPoints) {
                    times++;
                    allPoints -= tmp;
                } else {
                    index--;
                    if (times > 0) {
                        productExchangeTimes.put(product, times);
                        times = 0;
                    }
                }
            }
        }
        return productExchangeTimes;
    }

    private void stateCollectScore(User targetUser, int score) {
        exchangeUserRepository.increaseScore(targetUser.getAppId(), targetUser.getUserId(), score);
    }

    private User chooseOneUser(String appId) throws Exception {
        ExchangeUser eUser = chooseAnValidExchangeUser();
        User user = null;
        if (eUser != null && StringUtils.isNotBlank(eUser.getUserId())) {
            user = userService.getOne(eUser.getAppId(), eUser.getUserId());
        }

        if (null == user) {
            try {
                user = initOneUser(appId);
            } catch (Exception e) {
                user = initOneUser(appId); //try agin
            }
            if (null != user) {
                user = updateUserCreateTimeToBeforeTime(user);
                //record to exchange user;
                eUser = new ExchangeUser();
                eUser.setAppId(user.getAppId());
                eUser.setUserId(user.getUserId());
                eUser.setCreateTime(user.getCreateTime());
                eUser.setPoints(NumberUtils.INTEGER_ZERO);
                eUser.setStatus(ExchangeUser.STATUS_USEABLE);
                exchangeUserRepository.save(eUser);
            }
        }
        return user;
    }

    private ExchangeUser chooseAnValidExchangeUser() {
        List<ExchangeUser> exchangeUsers = exchangeUserRepository.findByStatus(ExchangeUser.STATUS_USEABLE, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(exchangeUsers))
            return exchangeUsers.get(0);
        return null;
    }

    private Date parseCurrentDateTen_Oclock() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private User updateUserCreateTimeToBeforeTime(User user) {
        if (null == user)
            return null;
        userRepository.updateUserCreateTime(user.getAppId(), user.getUserId(), -(Integer) ToolKits.ranDomSelect(ToolKits.hours, ToolKits.hoursRandom),
                (Integer) ToolKits.ranDomSelect(ToolKits.minutes, ToolKits.minutesRandom),
                (Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom));
        return userRepository.findByAppIdAndUserId(user.getAppId(), user.getUserId());
    }


    private User initOneUser(String appId) throws Exception {
        User user = randomParamToCreateUser();
        user.setAppId(appId);
        User newlyUser = userService.create(user);
        while (newlyUser.getIsBlack()) {
            user = randomParamToCreateUser();
            user.setAppId(appId);
            newlyUser = userService.create(user);
        }
        return newlyUser;
    }

    private User randomParamToCreateUser() {
        User user = new User();
        user.setMac(randomMac());
        user.setToken(randomToken());
        user.setOpenUdid(randomOpenUdid());
        return user;
    }

    private String randomOpenUdid() {
        return randomString(40);
    }

    private String randomToken() {
        return randomString(64);
    }

    private String randomMac() {
        StringBuilder sb = new StringBuilder();
        sb.append(randomString(8)).append("-");
        sb.append(randomString(4)).append("-");
        sb.append(randomString(4)).append("-");
        sb.append(randomString(4)).append("-");
        sb.append(randomString(12));
        return StringUtils.upperCase(sb.toString());
    }

    private String randomString(int length) {
        int count = 0;
        StringWriter stringWriter = new StringWriter();
        while (count < length) {
            stringWriter.write((String) ToolKits.ranDomSelect(chars, charsRandom));
            count++;
        }
        return stringWriter.toString();
    }

    public synchronized long getRandomSleepTime() {
        return 1000 * 60 * sleepMinutes.get(Math.abs(sleepMinutesRandom.nextInt()) % sleepMinutes.size())
                + 1000 * sleepSeconds.get(Math.abs(sleepSecondsRandom.nextInt()) % sleepSeconds.size());
    }

    public synchronized long getRandomShortSleepTime() {
        return getRandomSleepTime() / 5;
    }

    private static List<String> chars = ImmutableList.of("a", "b", "g", "v", "w", "h", "i", "j", "k", "n", "o", "p", "c", "d", "e", "f", "q", "r", "l", "m", "s", "t", "u", "x", "y", "z",
            "C", "U", "V", "D", "E", "F", "G", "K", "L", "M", "A", "B", "N", "O", "P", "Q", "R", "S", "T", "W", "H", "I", "J", "X", "Y", "Z",
            "0", "2", "5", "1", "9", "6", "7", "3", "4", "8");
    private static final Random charsRandom = new Random(chars.size() + System.currentTimeMillis());
    static List<Integer> sleepMinutes = ImmutableList.of(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
    static Random sleepMinutesRandom = new Random(sleepMinutes.size() + System.currentTimeMillis());
    static List<Integer> sleepSeconds = ImmutableList.of(1, 2, 3, 4, 7, 9, 10, 20, 23, 13, 30, 25, 43, 20, 30, 49, 59, 26, 12, 43, 53, 23, 41, 43, 24, 47, 26, 8, 9, 17, 43, 16, 29, 38, 37, 33, 1, 34, 23, 43, 56);
    static Random sleepSecondsRandom = new Random(sleepMinutes.size() + System.currentTimeMillis());


    @Autowired
    IntegralService integralService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ExchangeUserRepository exchangeUserRepository;
    @Autowired
    ExchangeAccountRepository exchangeAccountRepository;
    @Autowired
    SysConfRepository sysConfRepository;
    @Autowired
    ExchangeService exchangeService;
    @Value("${sys.conf.one.day.money.limit.exchange}")
    private String exchange_money_limit_one_user;
    @Value("${sys.conf.one.day.times.alipaydrawout}")
    private String alipay_drwaout_times_oneday;
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
}
