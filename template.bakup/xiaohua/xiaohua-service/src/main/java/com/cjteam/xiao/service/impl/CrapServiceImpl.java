package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.CrapBet;
import com.cjteam.xiao.model.CrapIssue;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.repository.CrapBetRepository;
import com.cjteam.xiao.repository.CrapIssueRepository;
import com.cjteam.xiao.repository.UserRepository;
import com.cjteam.xiao.service.CrapService;
import com.cjteam.xiao.service.SysConfService;
import com.cjteam.xiao.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.cjteam.xiao.model.CrapIssue.*;

/**
 * Created by ChenLong on 14-2-23.
 */
@Service(value = "crapService")
@Transactional
public class CrapServiceImpl implements CrapService {
    private static final Logger LOG = LoggerFactory.getLogger(CrapServiceImpl.class);
    public static final Integer ISSUE_DURATION_DEFAULT = 15;
    public static final Integer ISSUE_DURATION_MIN = 5;
    public static final Random random = new Random(System.currentTimeMillis());

    @Value("${sys.conf.crap.issue-duration-minutes}")
    private String sysConfigCrapIssueDurationMinutes;

    @Override
    public CrapIssue getCurrentIssue(String appId) {
        return crapIssueRepository.findByAppIdAndStartTimeGreaterThanAndEndTimeLessThan(appId, new Date());
    }

    @Override
    public void assertCoinsBet(String appId, String userId, int coinsBet) {
        User user = userRepository.findByAppIdAndUserId(appId, userId);
        assertCoinsBet(user, coinsBet);
    }

    private void assertCoinsBet(User user, int coinsBet) {
        Assert.isTrue(!user.getIsBlack(), "帐号已锁定");
        Assert.isTrue(coinsBet > 0, "押注金额必须大于0");
        Assert.isTrue(coinsBet <= user.getSurplus(), "积分不够");
    }

    @Override
    public void betSmall(String appId, String userId, Integer issueNo, int coinsBet) {
        bet(appId, issueNo, coinsBet, CRAP_SMALL, userId);
    }

    @Override
    public void betBig(String appId, String userId, Integer issueNo, int coinsBet) {
        bet(appId, issueNo, coinsBet, CRAP_BIG, userId);
    }

    private void bet(String appId, Integer issueNo, int coinsBet, int crapType, String userId) {
        User user = userRepository.findByAppIdAndUserId(appId, userId);
        assertCoinsBet(user, coinsBet);

        CrapBet bet = new CrapBet();
        bet.setUser(user);
        bet.setAppId(user.getAppId());
        bet.setCoins(coinsBet);
        bet.setBetType(crapType);
        CrapIssue issue = crapIssueRepository.findByAppIdAndIssue(appId, issueNo);
        Assert.notNull(issue, "下注期号不存在");
        Assert.isTrue(CRAP_STATUS_DOING == issue.getStatus(), "下注期以结束或者还未开始");
        bet.setIssue(issue);

        crapBetRepository.save(bet);
        /*LOG.info(" {} sleep",Thread.currentThread().getId());
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info(" {} wake up",Thread.currentThread().getId());*/
        Assert.isTrue(userService.reduceUserScore(user, coinsBet),"减积分失败");
        recordBetPerson(bet);
    }

    private void recordBetPerson(CrapBet bet) {
        int betSMall = 0, betBig = 0, sumGuessSmall = 0, sumGuessBig = 0;
        if (CRAP_SMALL.equals(bet.getBetType())) {
            betSMall = 1;
            sumGuessSmall = bet.getCoins();
        } else if (CRAP_BIG.equals(bet.getBetType())) {
            betBig = 1;
            sumGuessBig = bet.getCoins();
        }
        crapIssueRepository.recordBet(bet.getAppId(),bet.getIssue().getIssue(), bet.getCoins(), betBig, betSMall, sumGuessBig, sumGuessSmall);
    }

    @Override
    public List<CrapBet> getIssueInfoByUserId(String appId, Integer issue, String userId) {
        return crapBetRepository.findByAppIdAndUser_UserIdAndIssue_Issue(appId, userId, issue);
    }

    @Override
    public List<CrapBet> getMyIssue(String appId, String userId, Pageable pageable) {
        return crapBetRepository.findByAppIdAndUser_UserId(appId, userId, pageable);
    }

    @Override
    public Page<CrapIssue> getAllIssues(String appId, Pageable pageable) {
        return crapIssueRepository.findByAppIdAndStatusGreaterThan(appId, CRAP_STATUS_DOING, pageable);
    }

    @Override
    public CrapIssue getIssue(String appId, Integer issue) {
        return crapIssueRepository.findByAppIdAndIssue(appId, issue);
    }

    @Override
    public int initHodiernalIssues(String appId) {
        CrapIssue issue = null;
        Integer maxIssueNo = getCurrentMaxIssueNo(appId);
        Date latestIssueEndTime = crapIssueRepository.findLatestEndTime(appId);
        LOG.info("latest issue end time is {}",latestIssueEndTime);
        DateTime startTime = DateTime.now();
        LOG.info("start init time is {}",startTime);
        if (null != latestIssueEndTime) {
            startTime = new DateTime(latestIssueEndTime);
        }

        startTime = startTime.withSecondOfMinute(0).withMillisOfSecond(0);
        int minute = startTime.getMinuteOfHour();
        if (minute % getCrapIssueDuration(appId) != 0) {
            minute += getCrapIssueDuration(appId) - minute % getCrapIssueDuration(appId);
        }
        startTime = startTime.withMinuteOfHour(minute);

        DateTime expiration = startTime.withHourOfDay(0).withMinuteOfHour(0).withFieldAdded(DurationFieldType.days(), 1);
        List<CrapIssue> crapIssues = new ArrayList<CrapIssue>(100);
        while (expiration.isAfter(startTime)) {
            issue = new CrapIssue();
            issue.setAppId(appId);
            issue.setIssue(++maxIssueNo);

            issue.setStartTime(startTime.toDate());
            startTime = startTime.withFieldAdded(DurationFieldType.minutes(), getCrapIssueDuration(appId));
            issue.setEndTime(startTime.toDate());

            issue.setStatus(CRAP_STATUS_INIT);

            crapIssues.add(issue);
        }
        LOG.info("init {}'s issues into db", crapIssues.size());
        crapIssueRepository.save(crapIssues);
        return crapIssues.size();
    }

    private int getCrapIssueDuration(String appId) {
        Integer configDuration = (Integer) sysConfService.getConfigValueByConfigCode(appId, sysConfigCrapIssueDurationMinutes, new Integer(0));
        if (null == configDuration || configDuration.intValue() < ISSUE_DURATION_MIN)
            return ISSUE_DURATION_DEFAULT;
        else return configDuration.intValue();
    }

    public Integer getCurrentMaxIssueNo(String appId) {
        Integer maxIssue = crapIssueRepository.getMaxIssue(appId);
        if (null == maxIssue)
            maxIssue = 1;
        return maxIssue;
    }

    @Override
    public void lottery(String appId) {
        List<CrapIssue> lotteryIssues = crapIssueRepository.findByAppIdAndStatus(appId, CRAP_STATUS_LOTTERY);
        int counter = CollectionUtils.isNotEmpty(lotteryIssues) ? CollectionUtils.size(lotteryIssues) : 0;
        LOG.info("lottery issues count {}", counter);
        if (counter > 0) {
            for (CrapIssue lotteryIssue : lotteryIssues) {
                lottery(lotteryIssue);
            }
        }
    }

    @Override
    public void lottery(CrapIssue issue) {
        LOG.info("START OPEN LOTTERY {}", issue.getIssue());
        if (null == issue)
            return;
        randDomResult(issue);
        LOG.info("lottery issue base info {}", issue.toString());
        if (noBodyBet(issue)) {
            LOG.info(" not body bet this issue {}", issue.getIssue());
        } else {
            int lotteryPersonCount = calculateLotteryPerson(issue);
            if (lotteryPersonCount <= 0) {
                LOG.info("there is no one war the bet right one.");
            } else {
                LOG.info("pool score sum  {}, lottery person sum {}", issue.getSumScore(), lotteryPersonCount);
                LOG.info("winner count {}", lotteryPersonCount);
//                int averageScore = sumScore / lotteryPersonCount;
                Date lotteryTime = new Date();

                awardWinner(issue, lotteryTime);
                updateBetLoserStatus(issue, lotteryTime);
            }
        }
        issue.setStatus(CRAP_STATUS_DONE);
        crapIssueRepository.save(issue);
        LOG.info("END OPEN LOTTERY {}", issue.getIssue());
    }

    private boolean noBodyBet(CrapIssue issue) {
        if (issue.getSumUser() == 0 || issue.getSumScore() == 0)
            return true;
        return false;
    }

    private void awardWinner(CrapIssue issue, Date lotteryTime) {
        List<CrapBet> winnerBets = crapBetRepository.findByAppIdAndIssue_IssueAndBetType(issue.getAppId(), issue.getIssue(), issue.getResult());
        if (CollectionUtils.isEmpty(winnerBets))
            return;
        LOG.info("bet winner count {}", winnerBets.size());
        int award = 0;
        int portionCount = getAwardPortion(issue);
        int awardPoolScore = getAllAwardScore(issue);
        int sumAward = 0;
        for (CrapBet bet : winnerBets) {
            bet.setLotteryTime(lotteryTime);
            bet.setIssueResult(issue.getResult());
            award = awardPoolScore * bet.getCoins() / portionCount;
            LOG.info("issue {} ,bet user {} award score {}.", issue.getIssue(), bet.getUser().getUserId(), award);
            bet.setLotteryScore(bet.getCoins() + award);
            sumAward += bet.getLotteryScore();
            userService.increaseScore(bet.getUser().getId(), bet.getLotteryScore());
        }
        crapBetRepository.save(winnerBets);
        issue.setSurplus(issue.getSumScore() - sumAward);
        LOG.debug("updated bet winner count {} ", winnerBets.size());
    }

    private int getAllAwardScore(CrapIssue issue) {
        int counter = 0;
        if (CRAP_BIG.equals(issue.getResult())) {
            counter = issue.getSumGuessSmall();
        } else {
            issue.setResult(CRAP_SMALL);
            counter = issue.getSumGuessBig();
        }
        return counter;
    }

    private int getAwardPortion(CrapIssue issue) {
        int counter = 0;
        if (CRAP_BIG.equals(issue.getResult())) {
            counter = issue.getSumGuessBig();
        } else {
            issue.setResult(CRAP_SMALL);
            counter = issue.getSumGuessSmall();
        }
        return counter;
    }

    private void updateBetLoserStatus(CrapIssue issue, Date lotteryTime) {
        int loserCount = crapBetRepository.updateBetLoserStatus(issue.getAppId(),issue.getIssue(), issue.getResult(), lotteryTime);
        LOG.debug("updated bet loser count {} ", loserCount);
    }

    private int calculateLotteryPerson(CrapIssue issue) {
        int counter = 0;
        if (CRAP_BIG.equals(issue.getResult())) {
            counter = issue.getBetBigPersonSum();
        } else {
            issue.setResult(CRAP_SMALL);
            counter = issue.getBetSmallPersonSum();
        }
        return counter;
    }

    private void randDomResult(CrapIssue issue) {
        long product = issue.getSumUser() * issue.getSumScore();
        int sum = issue.getBetBigPersonSum() + issue.getBetSmallPersonSum() +
                issue.getSumGuessBig() + issue.getSumGuessSmall() +
                issue.getSumUser() + issue.getSumScore();
        if (product == 0 || sum == 0) {
            issue.setResult(CRAP_BIG);
            return;
        }

        long result = Math.abs(random.nextInt()) % System.currentTimeMillis() % NumberUtils.max(new int[]{CRAP_BIG, CRAP_SMALL});

        if (result + 1 == NumberUtils.max(new int[]{CRAP_BIG, CRAP_SMALL}))
            result = NumberUtils.max(new int[]{CRAP_BIG, CRAP_SMALL});
        else
            result = NumberUtils.min(new int[]{CRAP_BIG, CRAP_SMALL});

        issue.setResult((int) result);
    }

    @Override
    public void issueStatusManage(String appId) {
        CrapIssue issue = getCurrentIssue(appId);
        if (null == issue)
            return;
        if (issue.getStatus().equals(CrapIssue.CRAP_STATUS_DOING)) {
            return;
        } else {
            issue.setStatus(CRAP_STATUS_DOING);
            updateEarlierIssueStatus(issue);
            crapIssueRepository.save(issue);
        }
    }

    @Override
    public List<CrapBet> getMyWinIssues(String appId, String userId) {
        User user = userService.getOne(appId,userId);
        if(null == user)
            return null;
        return crapBetRepository.findWinIssuesByAppIdAndUserId(appId, user.getUniqueUserId());
    }

    public void setSysConfigCrapIssueDurationMinutes(String sysConfigCrapIssueDurationMinutes) {
        this.sysConfigCrapIssueDurationMinutes = sysConfigCrapIssueDurationMinutes;
    }

    private void updateEarlierIssueStatus(CrapIssue issue) {
        crapIssueRepository.updateCompleteIssueByEndTime(issue.getAppId(), issue.getStartTime(), CRAP_STATUS_LOTTERY);
    }

    @Autowired
    CrapIssueRepository crapIssueRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CrapBetRepository crapBetRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SysConfService sysConfService;


}