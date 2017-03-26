package com.cjteam.mrile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cjteam.mrile.dao.IUserDao;

public class MainTest {

	private static final Logger logger = LoggerFactory.getLogger(MainTest.class);
	public static void main(String[] args) {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("/spring-applicationContext.xml");
		Object ss = c.getBean(IUserDao.class);
		logger.debug(ss + "");
	}

}
