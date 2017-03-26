package com.cjteam.xiao.repository;

import com.cjteam.xiao.dao.WithdrawDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/repository-context.xml" })
@Transactional(rollbackFor = Throwable.class)
public class WithdrawDaoTest {
	private static final Logger log = LoggerFactory.getLogger(WithdrawDaoTest.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

    @Test
    public void testName() throws Exception {
        withdrawDao.filter(null,new PageRequest(0,1));

    }

    @Autowired
    WithdrawDao withdrawDao;
}
