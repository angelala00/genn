package com.cjteam.xiao.service;

import com.cjteam.xiao.model.IpLocation;
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

/**
 * Created by ChenLong on 14-3-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class RegionServiceTest extends TestCase {
    private static final Logger LOG = LoggerFactory.getLogger(RegionServiceTest.class);
    @Autowired
    RegionService regionService;

    @Test
    public void testSinaIpLibraryInterface() throws Exception {
        String ip ="36.251.209.237";
        IpLocation ipLocation = regionService.queryLocationInfoFromSinaIPInterface(ip);
        assertNotNull(ipLocation);
        System.out.println(ipLocation.toString());
    }

    @Test
    @Rollback(value = false)
    public void testCheck() throws Exception {
        String ip ="36.251.209.237";
        boolean checkResult = regionService.check("appId", ip, "WANPU", 100);
        System.out.println(checkResult);
    }
}