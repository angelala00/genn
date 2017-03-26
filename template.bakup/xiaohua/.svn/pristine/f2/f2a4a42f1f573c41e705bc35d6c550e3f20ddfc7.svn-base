package com.cjteam.xiao.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.fail;

/**
 * Created by ChenLong Date: 13-10-9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repository-context.xml"})
@Transactional
public class IpUserLimitRepositoryTest {
    private static final Logger LOG = LoggerFactory.getLogger(IpUserLimitRepositoryTest.class);

    @Autowired
    IpUserLimitRepository ipUserLimitRepository;

    @Test
    @Rollback(value = false)
    public void testName() throws Exception {
        LOG.info(ipUserLimitRepository.insertNewUserAccessRecord("", "192.168.1.1", "0b40b1121d53ecb780b018053a2f6af1") + "");
        LOG.info(ipUserLimitRepository.insertNewUserAccessRecord("", "192.168.1.1", "02f7eb422f24e24cb70f11a812c2d7c8") + "");
    }
}
