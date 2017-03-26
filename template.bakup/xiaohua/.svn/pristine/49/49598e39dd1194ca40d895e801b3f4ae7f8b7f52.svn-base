package com.cjteam.xiao.service;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.util.ToolKits;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: wuyimin
 * Date: 13-11-13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class UserServiceTest extends TestCase {
    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    private String userId;
    private User user;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        userId = "b8e86f7f68dba04329c65ed2cb76de4f";
    }

    @Test
    public void testExchangeInRemainingLimitEnough() {
        user = userService.getOne("",userId);
        log.info("user is {} . ", user);
        int cost = 1500;
        boolean result = user.exchangeInRemainingLimit(cost);
        assertTrue(result);
        assertTrue(user.getRemainingLimit() == User.defaultDailyExchangeMoneyLimit - cost);
    }

    @Test
    public void testExchangeInRemainingLimitLack() {
        user = userService.getOne("",userId);
        log.info("user is {} . ", user);
        int cost = 2500;
        boolean result = user.exchangeInRemainingLimit(cost);
        assertFalse(result);
        assertTrue(user.getRemainingLimit() == User.defaultDailyExchangeMoneyLimit);
    }

    @Test
    @Rollback(value = false)
    public void testExchangeInRemainingLimitNewDay() {
        user = userService.getOne("",userId);
        user.setDailyExchangeLimit("2012-11-12,2000");
        log.info("user is {} . ", user);
        int cost = 1500;
        boolean result = user.exchangeInRemainingLimit(cost);
        assertTrue(result);
        assertTrue(user.getRemainingLimit() == 500);
        assertTrue("2013-11-15,500".equalsIgnoreCase(user.getDailyExchangeLimit()));

        log.info("result is [{}]", userService.save(user));
    }

    @Test
    public void testTimestampGenerate() throws Exception {
        log.info(new Date(1384531800000L).toString());
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 5);
        log.info(now.getTime().toString());
        log.info(now.getTime().getTime() + "");
    }

    @Test
    @Rollback(value = true)
    public void testCreateUser() throws Exception {
        String persistMac = "B1D4B5AC-C4CC-4053-9656-4C6B61E39882C";
        String persistToken = "B1D4B5AC4053";
        String openUdid = "openUdid1";
        User newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        User persistUser = userService.create(newUser);
        assertFalse(persistUser.getIsBlack());
        assertEquals(3, persistUser.getValidTimes());
        String uniqueUserId = persistUser.getUniqueUserId();
        String userId = persistUser.getUserId();
        assertEquals(userId, uniqueUserId);


        newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        persistUser = userService.create(newUser);
        assertFalse(persistUser.getIsBlack());
        assertEquals(3, persistUser.getValidTimes());
        assertEquals(persistUser.getUserId(), persistUser.getUniqueUserId());
        assertEquals(persistUser.getUserId(), userId);
        assertEquals(uniqueUserId, persistUser.getUniqueUserId());
        uniqueUserId = persistUser.getUniqueUserId();
        userId = persistUser.getUserId();

        openUdid = "openUdid2";
        newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        persistUser = userService.create(newUser);
        assertFalse(persistUser.getIsBlack());
        assertEquals(2, persistUser.getValidTimes());
        assertTrue(!StringUtils.equals(persistUser.getUserId(), persistUser.getUniqueUserId()));
        assertEquals(uniqueUserId, persistUser.getUniqueUserId());
        assertTrue(!StringUtils.equals(userId, persistUser.getUserId()));
        uniqueUserId = persistUser.getUniqueUserId();
        userId = persistUser.getUserId();

        openUdid = "openUdid3";
        newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        persistUser = userService.create(newUser);
        assertFalse(persistUser.getIsBlack());
        assertEquals(1, persistUser.getValidTimes());
        assertTrue(!StringUtils.equals(persistUser.getUserId(), persistUser.getUniqueUserId()));
        assertEquals(uniqueUserId, persistUser.getUniqueUserId());
        uniqueUserId = persistUser.getUniqueUserId();
        userId = persistUser.getUserId();

        openUdid = "openUdid4";
        newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        persistUser = userService.create(newUser);
        assertFalse(persistUser.getIsBlack());
        assertEquals(0, persistUser.getValidTimes());
        assertTrue(!StringUtils.equals(persistUser.getUserId(), persistUser.getUniqueUserId()));
        assertEquals(uniqueUserId, persistUser.getUniqueUserId());
        uniqueUserId = persistUser.getUniqueUserId();
        userId = persistUser.getUserId();

        openUdid = "openUdid4";
        newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        persistUser = userService.create(newUser);
        assertFalse(persistUser.getIsBlack());
        assertEquals(0, persistUser.getValidTimes());
        assertTrue(!StringUtils.equals(persistUser.getUserId(), persistUser.getUniqueUserId()));
        assertEquals(uniqueUserId, persistUser.getUniqueUserId());
        assertEquals(userId, persistUser.getUserId());
        uniqueUserId = persistUser.getUniqueUserId();
        userId = persistUser.getUserId();

        openUdid = "openUdid5";
        newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        persistUser = userService.create(newUser);
        assertTrue(persistUser.getIsBlack());
        assertEquals(0, persistUser.getValidTimes());
        assertTrue(!StringUtils.equals(persistUser.getUserId(), persistUser.getUniqueUserId()));
        assertEquals(uniqueUserId, persistUser.getUniqueUserId());

        openUdid = "openUdid6";
        newUser = new User();
        newUser.setMac(persistMac);
        newUser.setOpenUdid(openUdid);
        newUser.setToken(persistToken);
        persistUser = userService.create(newUser);
        assertTrue(persistUser.getIsBlack());
        assertEquals(0, persistUser.getValidTimes());
        assertTrue(!StringUtils.equals(persistUser.getUserId(), persistUser.getUniqueUserId()));
        assertEquals(uniqueUserId, persistUser.getUniqueUserId());
    }

    @Test
    @Rollback(value = false)
    public void testName() throws Exception {

        User dbUser = userService.getOne("",userId);
        assertTrue("remaining should be  2000", dbUser.exchangeInRemainingLimit(100));
        assertEquals(1900, dbUser.getRemainingLimit().intValue());
        assertTrue("remaining should be  1900", dbUser.exchangeInRemainingLimit(1900));
        assertEquals(0, dbUser.getRemainingLimit().intValue());
    }

    @Test
    public void testGetBlackListUsers() throws Exception {
        Page<User> userPage = userService.getBlackListUsers(null, 100, null, 0);
        for (User user : userPage.getContent()) {
            assertThat("userService getBlackList interface not realization not correct", user.getIsBlack());
        }
        System.out.printf("black user count %d", userPage.getTotalElements());
    }

    @Test
    @Rollback(value = false)
    public void testUpdate() throws Exception {
        User use = userService.create(ToolKits.randomParamToCreateUser());
//        userService.updateUserInfo(use.getUserId(),"telphone","newpassword","nickName");
//        userService.updateUserInfo(use.getUserId(),"telphone1","newpassword1","nickName1");
//        userService.updateUserInfo(use.getUserId(),"telph2one2","n2ewpassword2","nickN2ame2");
    }

  @Test
    @Rollback(value = false)
    public void testUpdate2() throws Exception {
//        userService.updateUserInfo("d2871496f6481ad2427b6358f28ceb15","telphfadsfone","newpasswfatword","nickNfadsame");
//        userService.updateUserInfo("d2871496f6481ad2427b6358f28ceb15","telphone1","newpassword1","nickNafadfme1");
//        userService.updateUserInfo("d2871496f6481ad2427b6358f28ceb15","telpfadgr2352hone2","newpafdasssword2","nicfasdfkName2");
    }


    @Autowired
    UserService userService;
}
