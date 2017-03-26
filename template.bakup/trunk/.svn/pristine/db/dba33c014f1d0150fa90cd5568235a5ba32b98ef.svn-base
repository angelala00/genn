package com.cjteam.mrile.persistence;

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
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);
	
	@Test
	public void testSelectByPrimaryKey_cache() {
		for (int i = 0; i <= 10; i++) {
			User u = userMapper.selectByPrimaryKey(2);
			if (u == null ) {
//			fail("no data");
			} else {
				logger.debug("<<<<<>>>>>>>>>>"+u.getCreateTime());
			}
		}
	}
	@Test
	public void testSelectByPrimaryKey() {
		User u = userMapper.selectByPrimaryKey(2);
		if (u == null ) {
//			fail("no data");
		} else {
			logger.debug("<<<<<>>>>>>>>>>"+u.getCreateTime());
		}
	}

}
