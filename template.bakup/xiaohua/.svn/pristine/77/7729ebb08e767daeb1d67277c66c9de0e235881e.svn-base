package com.cjteam.xiao.service;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.util.ToolKits;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuYimin on 14-3-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class CrapServiceTest extends TestCase {
    private static final Logger LOG = LoggerFactory.getLogger(CrapServiceTest.class);
    @Autowired
    CrapService crapService;
    @Autowired
    UserService userService;

    String appId;

    //    @Test
    @Rollback(value = false)
    public void testInitHodiernalIssues() throws Exception {
        LOG.info("totally init {} crap issues today", crapService.initHodiernalIssues(appId));
    }

    @Test
    public void test(){
        LOG.info("current issue is {}", crapService.getCurrentIssue("3njsp"));
    }

    @Test
    @Rollback(value = false)
    public void testIssueStatusManage() throws Exception {
//        crapService.issueStatusManage();
    }

    @Test
    @Rollback(value = false)
    public void testLottery() throws Exception {
        crapService.lottery(appId);
    }

    @Test
    @Rollback(value = false)
    public void testBet() throws Exception {
        List<User> userList = createTenUser((Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom));
        int sum = 0, sumBetBig = 0, sumBetSmall = 0, tmp = 0;

        for (User user : userList) {
            tmp = (Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom) * 10;
            sum += tmp;
            sumBetBig += tmp;
            crapService.betBig(user.getAppId(), user.getUserId(), crapService.getCurrentIssue(user.getAppId()).getIssue(), tmp);
        }
        LOG.info("sum bet big score {} ,bet big person count {}", sumBetBig, userList.size());
        userList = createTenUser((Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom));
        for (User user : userList) {
            tmp = (Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom) * 10;
            sum += tmp;
            sumBetSmall += tmp;
            crapService.betSmall(user.getAppId(), user.getUserId(), crapService.getCurrentIssue(user.getAppId()).getIssue(), tmp);
        }
        LOG.info("sum bet small score {},bet small person count {}", sumBetSmall, userList.size());
        LOG.info("sum bet score {}", sum);
    }

    private List<User> createTenUser(int count) {
        List<User> re = new ArrayList<User>(count);
        User user = null;
        while (count-- > 0) {
            try {
                user = userService.create(ToolKits.randomParamToCreateUser());
                user.setTotalPoints(1000L);
                user.setSurplus(user.getTotalPoints());
                re.add(user);
            } catch (Exception e) {
                count++;
            }
        }

        return re;
    }
}