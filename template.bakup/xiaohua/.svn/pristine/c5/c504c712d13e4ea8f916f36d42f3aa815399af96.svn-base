package com.cjteam.xiao.stat.service;

import com.cjteam.xiao.stat.model.ComprehensiveStatInfo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/service-context.xml")
public class StatisticsServiceTest extends TestCase {
    private static final Logger log = LoggerFactory.getLogger(StatisticsServiceTest.class);
    private String appId;

    @Test
    public void testUserStat() throws Exception {
        log.info(statisticsService.statUser(appId, "693304d03389c423fd2c814a3d472c91").toString());
    }

    @Test
    public void testComprehensiveStat() throws Exception {
        List<ComprehensiveStatInfo> comprehensiveStatInfoList = statisticsService.comprehensiveStat(appId);
        for (ComprehensiveStatInfo statInfo : comprehensiveStatInfoList) {
            log.info(statInfo.toString());
        }
    }

    @Autowired
    StatisticsService statisticsService;
}
