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

import com.cjteam.xiao.model.Channel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/repository-context.xml" })
@Transactional(rollbackFor = Throwable.class)
public class ChannelRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(ChannelRepositoryTest.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetChannels() {
		Page<Channel> s = channelRepository.getChannelsByApp("",new PageRequest(0, 10));
		log.info("testGetChannels:"+s.getContent().toString());
	}

	@Autowired
	ChannelRepository channelRepository;
}
