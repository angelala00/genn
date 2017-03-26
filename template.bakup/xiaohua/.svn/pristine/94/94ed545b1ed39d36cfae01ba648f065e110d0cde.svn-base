package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Withdraw;
import com.cjteam.xiao.service.impl.QueryCondition;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChenLong
 * Date: 13-12-12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-context.xml" })
@Transactional(rollbackFor = Throwable.class)
public class WithdrawServiceImplTest extends TestCase {
    private static final Logger LOG = LoggerFactory.getLogger(WithdrawServiceImplTest.class);

    @Test
    public void testQuery() throws Exception {
        String name = "user1388585499496";
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setName(name);
        Page<Withdraw> withdraws =   withdrawService.query(0, 10, queryCondition);
        for(Withdraw withdraw:withdraws.getContent()){
            assertEquals("query error",withdraw.getUser().getNickName(),name);
        }
        LOG.info("result count is {}",withdraws.getNumberOfElements());
    }

    @Test
    public void testQueryByDateBetween() throws Exception {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setAfterDate(DateTime.parse("2013-12-31").toDate());
        queryCondition.setBeforeDate(DateTime.parse("2014-01-02").toDate());
        queryCondition.setUnPaid(true);
        Page<Withdraw> withdraws =   withdrawService.query(0, 10, queryCondition);
        LOG.info("result count is {}",withdraws.getNumberOfElements());
        LOG.info("totally count is {}",withdraws.getTotalElements());
        for(Withdraw withdraw:withdraws.getContent()){
//            assertEquals("query error",withdraw.getUser().getNickName(),name);
            assertTrue("after date filter error",queryCondition.getAfterDate().before(withdraw.getCreateTime()));
            assertTrue("before date filter error",withdraw.getCreateTime().before(queryCondition.getBeforeDate()));
            assertEquals("pay status filter error","doing",withdraw.getState().getCode());
        }
    }

    @Resource(name = "withdrawService")
    WithdrawService withdrawService;
}
