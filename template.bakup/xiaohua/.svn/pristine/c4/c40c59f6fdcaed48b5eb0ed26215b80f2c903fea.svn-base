package com.cjteam.xiao.service;

import com.cjteam.xiao.model.DailyChannelIntegralStat;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

/**
 * User: wuyimin
 * Date: 13-11-11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-context.xml" })
@Transactional
public class DailyChannelIntegralStatServiceImplTest extends TestCase {
    private Integer userId;
    private String channelCode;

    @Before
    public void setUp() throws Exception {
        userId=1;
        channelCode="DUOMENG";
    }

    @Test
    public void testCheckScoreDate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH,7);
        Date focusDate =calendar.getTime();
        DailyChannelIntegralStat stat = dailyChannelIntegralStatService.checkScore(userId,channelCode,focusDate);
        Assert.isTrue(133 == stat.getScore());
    }

    @Autowired
    DailyChannelIntegralStatService dailyChannelIntegralStatService;
}
