package com.cjteam.xiao.repository;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cjteam.xiao.model.Exchange;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/repository-context.xml" })
@Transactional(rollbackFor = Throwable.class)
public class ExchangeRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(ExchangeRepositoryTest.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testQueryByUserId() {
		Page<Exchange> s = exchangeRepository.findByAppIdAndUser_UserIdAndProduct_ProductType_CodeIn("", "2c59dcd87ea974f3009385e3346e35f9", new String[]{"QB", "MOBILE"}, new PageRequest(0, 10));
		log.info("testQueryByUserId:"+s.getContent().toString());
	}

	@Autowired
	ExchangeRepository exchangeRepository;
}
