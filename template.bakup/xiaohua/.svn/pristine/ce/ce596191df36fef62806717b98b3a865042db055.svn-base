package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.service.impl.QueryCondition;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ChenLong
 * Date: 13-11-9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
public class IntegralServiceTest extends TestCase {
    private static final Logger log = LoggerFactory.getLogger(IntegralServiceTest.class);

    @Test
    public void testGetByUserId() throws Exception {

    }

    @Test
    public void testRecordincome() throws Exception {

    }

    @Test
    public void testFindOneByOrderId() throws Exception {

    }

    @Test
    public void testFindOneByUserMacAndChannel() throws Exception {

    }

    @Test
    public void testFindOneByUserIdAndChannel() throws Exception {
        Integral integral = integralService.findOneByUserIdAndChannel(null, "b8e86f7f68dba04329c65ed2cb76de4f", "DUOMENG");
        assertNotNull(integral);
        log.info(integral.toString());
    }

    @Test
    public void testQueryAllByQueryCondition() throws Exception {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setName("user");
        Page<Integral> integrals = integralService.query(0, 10, queryCondition);
        log.info("integrals size {}", integrals.getContent().size());
        for (Integral integral : integrals.getContent()) {
            log.info("result user name is {}", integral.getUser().getNickName());
            assertTrue(StringUtils.contains(integral.getUser().getNickName(), "user"));
        }

    }

    @Test
    @Rollback(value = true)
    public void testAddPoints() throws Exception {
        //clear data in table integral and   daily_channel_integral_stat of this usr today
        Integer sumScore = 0;
        String poinst;
        String appId = "3njsp";//todo
        String userId = "e404826f5f5606b2c87338ac427ef081";//"67ebdf6966e4abfc784b22c8b299419e";
        String channelCode = "YIJIFEN";       //channel   max_score_times 300,max_score_daily 1500,twice_internal:3 second ,daily_times:5
        String orderId;
        orderId = String.valueOf(System.currentTimeMillis());
        poinst = "301";
        assertEquals("单次积分太多", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        poinst = "300";
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("OK", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        sumScore += 300;
        assertEquals("重复", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("太频繁", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("OK", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        sumScore += 300;
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("OK", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        sumScore += 300;
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("OK", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        sumScore += 300;
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("OK", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        sumScore += 300;
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("单日积分太多", integralService.addPoints(appId, poinst, userId, channelCode, orderId));
        log.info("sumsocre is {}", sumScore);
    }

    @Test
    public void testAddPointsMutiTimeOfOneADAndChannel() throws Exception {
        String poinst = "300";
        String userId = "9405ea101b74b957cf5add216cfbedcf";
        String channelCode = "YIJIFEN";       //channel   max_score_times 300,max_score_daily 1500,twice_internal:3 second
        String orderId;
        String adId = "EJFDLFJKX-148923";
        String adName = "adName";
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("OK", integralService.addPoints(null, poinst, userId, channelCode, orderId, adId, adName));
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("the user  duplicate integral on this ad", integralService.addPoints(null, poinst, userId, channelCode, orderId, adId, adName));
        Thread.sleep(3 * 1005);
        orderId = String.valueOf(System.currentTimeMillis());
        assertEquals("the user  duplicate integral on this ad", integralService.addPoints(null, poinst, userId, channelCode, orderId, adId, adName));
    }

    @Autowired
    IntegralService integralService;
    @Autowired
    ChannelService channelService;
}
