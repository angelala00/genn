package com.cjteam.mrile.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-applicationContext.xml" })
@Transactional
public class BaseServiceTest {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testVoid(){
		logger.info("test ok");
	}
}
