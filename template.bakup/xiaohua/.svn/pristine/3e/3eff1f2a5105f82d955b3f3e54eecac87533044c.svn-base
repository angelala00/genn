package com.cjteam.xiao.repository;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cjteam.xiao.model.User;

/**
 * Created by ChenLong Date: 13-10-9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/repository-context.xml" })
@Transactional
public class UserRepositoryTest {
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Autowired
	UserRepository userRepository;

	@Test
	@Rollback(value = false)
	public void testSaveForInsert() throws Exception {
		User user = new User();
		user.setMac("mac" + System.currentTimeMillis());
		user.setOpenUdid("openUdid" + System.currentTimeMillis());
		user.setToken("token" + System.currentTimeMillis());
		user.setUserId(String.valueOf(System.currentTimeMillis()));
		user = userRepository.save(user);
		log.info("new user entity insert success.user id is {}", user.getId());
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Rollback(value = false)
	public void testSaveForUpdate() {
		User user = userRepository.findByAppIdAndUserId("", "1382452311274");
		String newmac = "test" + System.currentTimeMillis();
		user.setMac(newmac);
		userRepository.save(user);
		user = userRepository.findByAppIdAndUserId("", "1382452311274");
		if (!newmac.equals(user.getMac())) {
			fail("not success");
		}
	}

	@Test
	public void testGetOne() {
        log.info("testgetone {}", userRepository.findByAppIdAndUserId("3njsp", "e404826f5f5606b2c87338ac427ef081"));
    }

    @Test
    @Rollback(value = false)
    public void testRollbackExchangeConsumptionScore(){
        userRepository.rollbackExchangeConsumptionScore(1,10000L);
    }

	@Test
	public void testQueryTopSortByScore() {
		Page<User> usersPage = userRepository.queryTopSortBySurplusScore("",new PageRequest(0, 10));
		log.info("size:" + usersPage.getContent().size() + " testQueryTopSortByScore" + usersPage.getContent());
	}

    @Test
    public void testCountUserBySimilarInfo() throws Exception {
        log.info("count result is {}",userRepository.countUserBySimilarInfo("","123","fdas","dfa"));
    }

    @Test
    public void testQueryUserBySimilarInfo() throws Exception {
        log.info("get result is {}",userRepository.queryUserBySimilarInfo("","123","fdas","dfa"));
    }
}
