package com.cjteam.xiao.service;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cjteam.xiao.model.DailyRegionIntegralLimit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-context.xml" })
public class DailyRegionIntegralLimitServiceImplTest extends TestCase {
    private Integer userId;
    private String channelCode;

    @Before
    public void setUp() throws Exception {
        userId=1;
        channelCode="DUOMENG";
    }

    @Test
//    @Rollback(value=true)
    public void changeLimitValueTest() throws Exception {
        dailyRegionIntegralLimitService.changeLimitValue(3, 1000);
    }
    @Test
    public void DailyRegionIntegralLimitTest() throws Exception {
    	DailyRegionIntegralLimit s = dailyRegionIntegralLimitService.get(3);
    	System.out.println("===========" + s);
    }

//	public PageImpl<DailyRegionIntegralLimit> getPage(String appId, PageBasicInfo PageBasicInfo, String cityName) ;
//	public DailyRegionIntegralLimit get(Integer id);
//
//    void changeLimitValue(Integer id, Integer score);
    @Autowired
    DailyRegionIntegralLimitService dailyRegionIntegralLimitService;
}
