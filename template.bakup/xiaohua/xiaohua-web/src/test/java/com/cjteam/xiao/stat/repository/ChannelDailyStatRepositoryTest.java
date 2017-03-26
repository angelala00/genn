package com.cjteam.xiao.stat.repository;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * User: wuyimin
 * Date: 13-11-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repository-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class ChannelDailyStatRepositoryTest extends TestCase {
    private static final Logger log = LoggerFactory.getLogger(ChannelDailyStatRepositoryTest.class);
    private String appId;

    @Test
    public void testQueryDailyStatForChannel() {
        List<Object[]> channelStats = channelDailyStatRepository.queryDailyStatForChannel(appId);
        Iterator<Object[]> iterator = channelStats.iterator();
        while (iterator.hasNext()) {
            Object[] tmp = iterator.next();
            log.info(Arrays.toString(tmp));
        }
    }

    @Test
    public void testStatUserBaseInfo() throws Exception {
        BigInteger userStatBaseInfo = channelDailyStatRepository.countUserIntegralDays(appId,"c59dcd87ea974f3009385e3346e35f9");
        log.info(userStatBaseInfo + "");
    }

    @Autowired
    ChannelDailyStatRepository channelDailyStatRepository;
}
