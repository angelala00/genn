package com.cjteam.xiao.service;

import com.cjteam.xiao.model.User;
import junit.framework.TestCase;
import org.junit.After;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * User: wuyimin
 * Date: 13-12-17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class IpUsersLimitServiceImplTest extends TestCase {
    private static final Logger LOG = LoggerFactory.getLogger(IpUsersLimitServiceImplTest.class);

    String ip = "192.168.1.100";
    List<String> userIds;
    List<User> userList;
    private String appId;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        taskExecutor = new ScheduledThreadPoolExecutor(20);
        Page<User> users = userService.queryAll(null, 100, null, 0);
        assertNotNull("没有用户数据，无法测试", users);
        assertNotNull("没有用户数据，无法测试", users.getContent());
        assertTrue("没有用户数据，无法测试", users.getContent().size() > 0);
        userList = users.getContent();
//        Collections.shuffle(userList);
        userList = userList.subList(11, 17);
        userIds = new ArrayList<String>(10);
        for (User user : userList) {
            userIds.add(user.getUserId());
        }
    }

    @Test
    @Rollback(value = false)
    public void testControlV2() throws Exception {
        for (String userId : userIds) {
            ipUsersLimitService.control(appId,ip, userId);
        }

        //current limit config is 6
        for (int index = 0; index < 5; index++) {
            LOG.info("allow access userId is {} ", userIds.get(index));
//            assertFalse("allow access userId is " + userIds.get(index), userService.findOneByOrderId(userIds.get(index)).getIsBlack());
        }
        for (int index = 5; index < userIds.size(); index++) {
            LOG.info("forbidden access userId is {} ", userIds.get(index));
//            assertTrue("forbidden access userId is " + userIds.get(index), userService.findOneByOrderId(userIds.get(index)).getIsBlack());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Rollback(value = false)
    public void testControl(String appId) throws Exception {
        int index = 1;
        for (String userId : userIds) {
            taskExecutor.submit(new NewUserAssessor(ip, userId,appId));
            LOG.info("submitted {} ", index);
            index++;
        }
        Thread.sleep(10000);
    }

    class NewUserAssessor implements Runnable {
        private String ip;
        private String userId;
        private String appId;

        NewUserAssessor(String ip, String userId,String appId) {
            this.ip = ip;
            this.userId = userId;
            this.appId=appId;
        }

        @Override
        public void run() {
            try {
                ipUsersLimitService.control(appId,ip, userId);
            } catch (Throwable t) {
                LOG.info(t.getLocalizedMessage(), t);
            }
        }
    }

    @Autowired
    IpUsersLimitService ipUsersLimitService;
    @Autowired
    UserService userService;
    ScheduledThreadPoolExecutor taskExecutor;
}
