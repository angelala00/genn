package com.cjteam.xiao.manager;

import com.google.common.collect.ImmutableList;
import com.cjteam.xiao.context.ApplicationContextUtils;
import com.cjteam.xiao.jdbc.ExternalTools;
import com.cjteam.xiao.model.*;
import com.cjteam.xiao.repository.ChannelRepository;
import com.cjteam.xiao.repository.ExchangeAccountRepository;
import com.cjteam.xiao.repository.SysConfRepository;
import com.cjteam.xiao.service.ExchangeService;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.service.ProductService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.util.ToolKits;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by ChenLong
 * Date: 14-2-16
 */

@Component("dataFixManager")
public class DataFixManager {
    private static final Logger LOG = LoggerFactory.getLogger(DataFixManager.class);

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    private Integer totallyExchangeMoney = 0;

    static List<Integer> exchangeMoney = ImmutableList.of(5, 10, 20, 15, 25, 30, 35, 40, 50);
    static Random exchangeMoneyRandom = new Random(exchangeMoney.size() + System.currentTimeMillis());
    static List<Integer> integrals = ImmutableList.of(199, 198, 196, 195, 194, 193, 192, 191, 190, 189, 187, 186, 185, 184, 183, 182, 180, 179, 178, 176, 175, 174, 173, 172, 171, 170, 169, 168, 167, 166, 165, 164, 163, 162, 161, 160, 159, 158, 157, 156, 155, 154, 153, 152, 151, 150, 149, 148, 147, 146, 145, 144, 143, 142, 141, 140, 139, 138, 137, 136, 135, 134, 133, 132, 131, 130, 129, 128, 127, 126, 125, 124, 123, 122, 121, 120, 119, 118, 117, 116, 115, 114, 113, 112, 111, 110, 109, 108, 107, 106, 105, 104, 103, 102, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36);
    static Random integralRandom = new Random(integrals.size() + System.currentTimeMillis());
    static List<Integer> exchange = ImmutableList.of(30, 50, 100);
    static Random exchangeRandom = new Random(exchange.size() + System.currentTimeMillis());
    static List<String> channels = ImmutableList.of("DUOMENG", "YIJIFEN", "YOUMI", "AIPU", "LIMEI", "DIANRU", "ADWO");
    static Random channelsRandom = new Random(channels.size() + System.currentTimeMillis());
    static List<String> chars = ImmutableList.of("a", "b", "g", "v", "w", "h", "i", "j", "k", "n", "o", "p", "c", "d", "e", "f", "q", "r", "l", "m", "s", "t", "u", "x", "y", "z",
            "c", "u", "v", "d", "e", "f", "g", "k", "l", "m", "a", "b", "n", "o", "p", "q", "r", "s", "t", "w", "h", "i", "j", "x", "y", "z",
            "0", "2", "5", "1", "9", "6", "7", "3", "4", "8");
    static final Random charsRandom = new Random(chars.size() + System.currentTimeMillis());

    public void doFix(String appId) throws Exception {
        totallyExchangeMoney = getDailyExchangeMoneyConf(appId);
        LOG.warn("fix money  : {}", totallyExchangeMoney);
        if (null == totallyExchangeMoney || totallyExchangeMoney <= 0)
            return;
        List<AlipayAverage> alipayAverages = apportionExchangeTask(appId,totallyExchangeMoney);
        if (null == alipayAverages || alipayAverages.size() <= 0)
            return;

        final Iterator<AlipayAverage> alipayAverageIterable = alipayAverages.iterator();
//        List<Future> apportionResult = new ArrayList<Future>(100);
//        int completed = 0;
//        int totalyTastCount = 0;

        while (alipayAverageIterable.hasNext()) {
            final AlipayAverage alipayAverage = alipayAverageIterable.next();
            final String alipayNo = alipayAverage.getAlipayAccount();
            List<ExchangeUser> exchangeUsers = alipayAverage.getExchangeUsers();
            if (null == exchangeUsers || exchangeUsers.size() <= 0)
                continue;
            final Iterator<ExchangeUser> exchangeUsersIterator = exchangeUsers.iterator();

            while (exchangeUsersIterator.hasNext()) {
                final ExchangeUser exchangeUser = exchangeUsersIterator.next();
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DataFixManager dataFixManager = (DataFixManager) ApplicationContextUtils.getBean("dataFixManager");
                            dataFixManager.runTask(alipayNo, exchangeUser);
                        } catch (Throwable t) {
                            LOG.error(t.getLocalizedMessage(), t);
                        }
                    }
                };
                taskExecutor.submit(task);
//                apportionResult.add(taskExecutor.submit(task));
//                totalyTastCount++;
            }
        }
        /*
        while (completed < totalyTastCount) {
            for (Future future : apportionResult) {
                if (future.isDone()) {
                    completed++;
                }
            }
            LOG.info("percentage complete  :  " + completed + "/" + totalyTastCount);
        }*/

/*        LOG.info("========================================================================================");
        LOG.info("========================================================================================");
        LOG.info("goal is {}", totallyExchangeMoney);

        for (AlipayAverage alipayAverage : alipayAverages) {
            LOG.info("alipay amount  {},apportion is {} ,complete {}", alipayAverage.getAlipayAccount(),
                    alipayAverage.getTotallyExchangeMoney(), sumRealStat(alipayAverage.getExchangeUsers()));
        }
        LOG.info("========================================================================================");
        LOG.info("========================================================================================");
        LOG.info("all task completed");*/
    }

    private int sumRealStat(List<ExchangeUser> exchangeUsers) {
        int sum = 0;
        if (CollectionUtils.isNotEmpty(exchangeUsers)) {
            for (ExchangeUser exchangeUser : exchangeUsers) {
                sum += exchangeUser.getRealExchangeMoney();
            }
        }
        return sum;
    }

    private Integer getDailyExchangeMoneyConf(String appId) {
        SysConf sysConf = sysConfRepository.findByAppIdAndCode(appId, sysconf_daily_money_exchange_limit);
        if (null == sysConf || !StringUtils.isNumeric(sysConf.getValue()))
            return 0;
        try {
            Integer config = Integer.valueOf(sysConf.getValue());
            if (config != null && config <= 0)
                return 0;
            else return config;
        } catch (Throwable t) {
            return 0;
        }
    }

    @Autowired
    ExchangeAccountRepository exchangeAccountRepository;

    private List<AlipayAverage> apportionExchangeTask(String appId, Integer totallyExchangeMoney) {
        Iterable<ExchangeAccount> allExchangeAccount = exchangeAccountRepository.findAll();
        if (null == allExchangeAccount)
            return null;
        Iterator<ExchangeAccount> exchangeAccountIterator = allExchangeAccount.iterator();

        List<AlipayAverage> reApportion = new ArrayList<AlipayAverage>();
        while (exchangeAccountIterator.hasNext()) {
            ExchangeAccount account = exchangeAccountIterator.next();
            AlipayAverage alipayAverage = new AlipayAverage();
            alipayAverage.setAppId(appId);
            alipayAverage.setAlipayAccount(account.getAccount());
            reApportion.add(alipayAverage);
        }

        int sumApportion = 0;
        while (sumApportion < totallyExchangeMoney) {
            Iterator<AlipayAverage> reApportionIterator = reApportion.iterator();
            while (reApportionIterator.hasNext() && sumApportion < totallyExchangeMoney) {
                AlipayAverage account = reApportionIterator.next();
                int tmp = 20;
                if (sumApportion + tmp > totallyExchangeMoney) {
                    tmp = totallyExchangeMoney - sumApportion;
                }
                account.addTotallyExchangeMoney(tmp);
                sumApportion += tmp;
            }
        }

        for (AlipayAverage account : reApportion) {
            try {
                account.apportion();
                LOG.warn("account {} apportion money is {}", account.getAlipayAccount(), account.getTotallyExchangeMoney());
            } catch (Exception e) {
            }
        }
        return reApportion;
    }

    public void runTask(String alipayNo, ExchangeUser exchangeUser) {
        Integer totallyExchange = exchangeUser.getExchangeMoney();
        Integer totallyIntegralScore = 100 * totallyExchange;

        User user = exchangeUser.getUser();

        int sum = 0;
        int currentIntegralScore = 0;
        while (sum <= totallyIntegralScore) {
            currentIntegralScore = integrals.get(Math.abs(integralRandom.nextInt()) % integrals.size());

            if (currentIntegralScore <= 0) {
                continue;
            }
            if (increaseIntegralScore(user, currentIntegralScore)) {
                sum += currentIntegralScore;
            }
        }
        user.setTotalPoints(Long.valueOf(sum));
        user.setSurplus(Long.valueOf(sum));
        exchangeUser.setRealApportionScore(sum);

//        LOG.info("user {} integral score {}", user.getNickName(), sum);
//       
        sum = 0;
        int currentExchangeMoney = 0;
        while (sum <= totallyExchange) {

            if (totallyExchange - sum <= 30) {
                currentExchangeMoney = totallyExchange - sum;
            } else {
                currentExchangeMoney = exchange.get(Math.abs(exchangeRandom.nextInt()) % exchange.size());
                while (sum + currentExchangeMoney > totallyExchange) {
                    currentExchangeMoney = exchange.get(Math.abs(exchangeRandom.nextInt()) % exchange.size());
                }
            }
            if (sum + currentExchangeMoney > totallyExchange) {
                currentExchangeMoney = totallyExchange - sum;
            }

            if (currentExchangeMoney <= 0) {
                break;
            }

            if (exchangeMoney(user, currentExchangeMoney, alipayNo)) {
                sum += currentExchangeMoney;
            }
        }
        user.setSurplus(user.getSurplus() - sum * 100);
        exchangeUser.setRealExchangeMoney(sum);
        LOG.info(" ============= RESULT ============= {} executor user {},apportion moeny is {} ,real complted {}, real apportion score is {} ", alipayNo, exchangeUser.getUser().getUserId(), exchangeUser.getExchangeMoney(),
                exchangeUser.getRealExchangeMoney(), exchangeUser.getRealApportionScore());
        userService.save(user);//update
    }

    private boolean exchangeMoney(User user, int currentExchangeMoney, String alipayNo) {
        Product exchangeProduct = getProductByMoney(currentExchangeMoney);
        try {
            Date updateTIme = dbTools.addExchangeForAlipay(user, exchangeProduct, alipayNo);
            if (null != updateTIme)
                user.setUpdateTime(updateTIme);
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return false;
        }
        return true;
    }

    private Product getProductByMoney(int currentExchangeMoney) {
        return productService.findByExchangeMoney(currentExchangeMoney);
    }

    private boolean increaseIntegralScore(User user, int currentIntegralScore) {
        String orderId = String.valueOf(System.currentTimeMillis());
        Integral integral = new Integral();
        integral.setUser(user);
        integral.setAppId(user.getAppId());
        integral.setScore(currentIntegralScore);
        integral.setChannel(channelRepository.findOneByAppIdAndCode(user.getAppId(), randomIntegralChannel()));
        integral.setOrderId(randomOrderId(integral.getChannel()));
        try {
            Date updateTIme = dbTools.addIntegral(integral);
            if (null != updateTIme)
                user.setUpdateTime(updateTIme);
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return false;
        }
        return true;
    }

    private String randomOrderId(Channel channel) {
        //todo
        return String.valueOf(System.currentTimeMillis());
    }

    private String randomIntegralChannel() {
        return channels.get(Math.abs(channelsRandom.nextInt() % channels.size()));
    }

    // create one newly an un-locked user
    private User initOneUser(String appId) throws Exception {
        User user = randomParamToCreateUser();
        user.setAppId(appId);
        User newlyUser = userService.create(user);
        while (newlyUser.getIsBlack()) {
            user = randomParamToCreateUser();
            user.setAppId(appId);
            newlyUser = userService.create(user);
        }
        Calendar createCalendar = Calendar.getInstance();
        createCalendar.setTime(newlyUser.getCreateTime());
        createCalendar.set(Calendar.HOUR_OF_DAY, 8);
        createCalendar.add(Calendar.HOUR_OF_DAY, ((Integer) ToolKits.ranDomSelect(ToolKits.hours, ToolKits.hoursRandom)) % 3);
        createCalendar.set(Calendar.MINUTE, (Integer) ToolKits.ranDomSelect(ToolKits.minutes, ToolKits.minutesRandom));
        createCalendar.set(Calendar.SECOND, (Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom));
        user.setCreateTime(createCalendar.getTime());
        user.setUpdateTime(user.getCreateTime());
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
            stringWriter.write(chars.get(Math.abs(charsRandom.nextInt() % chars.size())));
            count++;
        }
        return stringWriter.toString();
    }

    @Autowired
    IntegralService integralService;
    @Autowired
    UserService userService;
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    ProductService productService;
    @Autowired
    SysConfRepository sysConfRepository;
    @Value("${sysconf_daily_money_exchange_limit}")
    String sysconf_daily_money_exchange_limit;


    public synchronized long getRandomSleepTime() {
//        return 1000*2;
        return 1000 * 60 * ToolKits.sleepMinutes.get(Math.abs(ToolKits.sleepMinutesRandom.nextInt()) % ToolKits.sleepMinutes.size())
                + 1000 * ToolKits.sleepSeconds.get(Math.abs(ToolKits.sleepSecondsRandom.nextInt()) % ToolKits.sleepSeconds.size());
    }

    public synchronized long getRandomShortSleepTime() {
        return 1000 * ToolKits.sleepSeconds.get(Math.abs(ToolKits.sleepSecondsRandom.nextInt()) % ToolKits.sleepSeconds.size());
    }

    private static List<Integer> exchangeUserCount = ImmutableList.of(1, 2, 3, 2, 4, 1, 2, 2, 2);
    private static Random exchangeUserCountRandom = new Random(exchangeUserCount.size() + System.currentTimeMillis());

    class AlipayAverage {
        private String alipayAccount;
        private String appId;
        private List<ExchangeUser> exchangeUsers;
        private Integer totallyExchangeMoney;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        AlipayAverage() {
            totallyExchangeMoney = 0;
        }

        String getAlipayAccount() {
            return alipayAccount;
        }

        void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }

        List<ExchangeUser> getExchangeUsers() {
            return exchangeUsers;
        }

        void addExchangeUsers(ExchangeUser newExchangeUser) {
            if (null != newExchangeUser)
                exchangeUsers.add(newExchangeUser);
        }

        Integer getTotallyExchangeMoney() {
            return totallyExchangeMoney;
        }

        void addTotallyExchangeMoney(Integer totallyExchangeMoney) {
            if (totallyExchangeMoney != null && totallyExchangeMoney > 0)
                this.totallyExchangeMoney += totallyExchangeMoney;
        }

        public void apportion() throws Exception {
// one
            int exchangeUserCounter = DataFixManager.exchangeUserCount.get(Math.abs(exchangeUserCountRandom.nextInt()) % DataFixManager.exchangeUserCount.size());
            exchangeUsers = new ArrayList<ExchangeUser>(exchangeUserCounter);

            if (totallyExchangeMoney > 80 && exchangeUserCounter <= 1) {
                exchangeUserCounter++;
            }

            while (exchangeUserCounter > 0) {
                ExchangeUser exchangeUser = new ExchangeUser(getAppId());
                exchangeUser.setUser(initOneUser(getAppId()));
                try {
                    exchangeUsers.add(exchangeUser);
                    exchangeUserCounter--;
                } catch (Exception e) {
                }
            }

// two
            int sumApportion = 0;
            while (sumApportion < this.totallyExchangeMoney) {
                Iterator<ExchangeUser> exchangeUsersIterator = exchangeUsers.iterator();
                while (exchangeUsersIterator.hasNext() && sumApportion < this.totallyExchangeMoney) {
                    ExchangeUser account = exchangeUsersIterator.next();
                    if (account.getUser() == null)
                        continue;
                    int tmp = 10;
                    if (totallyExchangeMoney <= 50) {
                        tmp = totallyExchangeMoney;
                    } else {
                        if (sumApportion + tmp > this.totallyExchangeMoney) {
                            tmp = this.totallyExchangeMoney - sumApportion;
                            if (tmp < 5) {
                                userService.delUser(account.getUser());
                                account.setUser(null);
                            }
                        }
                    }
                    account.addTotallyExchangeMoney(tmp);
                    sumApportion += tmp;
                }
            }

            for (DataFixManager.ExchangeUser exchangeUser : exchangeUsers) {
                LOG.info("============={} executor user {},apportion moeny is {} ", alipayAccount, exchangeUser.getUser().getUserId(), exchangeUser.getExchangeMoney());
            }
        }

        @Override
        public String toString() {
            return "AlipayAverage{" +
                    "alipayAccount='" + alipayAccount + '\'' +
                    ", exchangeUsers=[" + toString2(this.exchangeUsers) +
                    "], totallyExchangeMoney=" + totallyExchangeMoney +
                    '}';
        }

        private String toString2(List<ExchangeUser> exchangeUsers) {
            if (CollectionUtils.isEmpty(exchangeUsers))
                return " empty";
            StringBuilder sb = new StringBuilder(exchangeUsers.size());
            for (ExchangeUser exchangeUser : exchangeUsers) {
                if (exchangeUser.getUser() != null)
                    sb.append(";").append(exchangeUser.getUser().getUserId()).append(",").append(exchangeUser.getExchangeMoney());
            }
            return sb.toString();
        }
    }

    public ThreadPoolTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    class ExchangeUser {
        private User user;
        private String appId;
        private Integer exchangeMoney = 0;
        private int realApportionScore;
        private int realExchangeMoney;

        public ExchangeUser(String appId) {
            setAppId(appId);
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        User getUser() {
            return user;
        }

        void setUser(User user) {
            this.user = user;
        }

        Integer getExchangeMoney() {
            return exchangeMoney;
        }

        void setExchangeMoney(Integer exchangeMoney) {
            this.exchangeMoney = exchangeMoney;
        }

        public void addTotallyExchangeMoney(Integer apportion) {
            if (apportion != null && apportion > 0)
                this.exchangeMoney += apportion;
        }

        public void setRealApportionScore(int realApportionScore) {
            this.realApportionScore = realApportionScore;
        }

        public int getRealApportionScore() {
            return realApportionScore;
        }

        public void setRealExchangeMoney(int realExchangeMoney) {
            this.realExchangeMoney = realExchangeMoney;
        }

        public int getRealExchangeMoney() {
            return realExchangeMoney;
        }
    }

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    ExternalTools dbTools;
}
