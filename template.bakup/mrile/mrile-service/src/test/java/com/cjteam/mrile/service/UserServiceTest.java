package com.cjteam.mrile.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjteam.mrile.model.User;

public class UserServiceTest extends BaseServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testGetOne() {
		User userr = userService.getOne(13769);
		logger.info("============user:" + userr);
	}

}
