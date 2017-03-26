package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Exchange;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong
 * Date: 13-12-12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-context.xml" })
@Transactional(rollbackFor = Throwable.class)
public class ExchangeServiceImplTest extends TestCase {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeServiceImplTest.class);

    @Test
    public void testGetQBAndMobileChargeRecords() throws Exception {
        Page<Exchange>  qbAndMobileCharges =  exchangeService.getQBAndMobileChargeRecords("3njsp", "e404826f5f5606b2c87338ac427ef081");
        LOG.info("query resutl size is {}",qbAndMobileCharges.getTotalElements());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawFromAlipayLimitedConfigTimes() throws Exception {
        String userId = "00b948942005d38cfcb07d025caf3916";
        String alipayNo = "13621391346";
        String product = "P14";
        exchangeService.withdrawFromAlipay(null, userId, product, alipayNo);
        alipayNo = "15001056821";
        product = "P17";
        exchangeService.withdrawFromAlipay("3njsp", userId,  product, alipayNo);
    }

    @Test
    public void testCharge2telphone() throws Exception {
        String userId = "19e7e0d4f58c2a87e83bcaf1527d7b15";
        String telphoneNumber = "13621391346";
        String product = "P2";
        int times = 5;
        while (times-- > 0) {
            exchangeService.charge2telphone("3njsp", userId, product, telphoneNumber);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCharge2telphoneExpectedIllegalArgumentException() throws Exception {
        String userId = "19e7e0d4f58c2a87e83bcaf1527d7b15";
        String telphoneNumber = "13621391346";
        String product = "P2";
        int times = 6;
        while (times-- > 0) {
            exchangeService.charge2telphone("3njsp", userId, product, telphoneNumber);
        }
    }

    @Test
    @Rollback(value = false)
    public void testCharge2Qbphone() throws Exception {
        String userId = "05606fda024f8f730653e74db9052af2";
        String qqNumber = "420136392";
        String product = "P3";
//        int times = 5;
//        while (times-- > 0) {
            exchangeService.charge2Qb("3njsp", userId, product, qqNumber);
//        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCharge2QbExpectedIllegalArgumentException() throws Exception {
        String userId = "19e7e0d4f58c2a87e83bcaf1527d7b15";
        String telphoneNumber = "420136392";
        String product = "P3";
        int times = 6;
        while (times-- > 0) {
            exchangeService.charge2Qb("3njsp", userId, product, telphoneNumber);
        }
    }

    @Autowired
    private ExchangeService exchangeService;
}
