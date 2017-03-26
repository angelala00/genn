package com.cjteam.mrile.dao.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cjteam.mrile.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-applicationContext.xml" })
@Transactional
public class UserDaoImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);
	
	@Autowired
	private UserDaoImpl userDao;

	@Test
	public void testSelectOneById() {
		User userr = userDao.selectOneById(13769);
		logger.info("============user:" + userr);
	}

}
