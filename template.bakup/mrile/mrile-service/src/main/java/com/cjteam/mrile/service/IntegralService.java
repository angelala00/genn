package com.cjteam.mrile.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.dao.IIntegralDao;
import com.cjteam.mrile.dao.IUserDao;
import com.cjteam.mrile.model.Integral;
import com.cjteam.mrile.model.User;

@Service
public class IntegralService extends BaseService {

	@Autowired
	private IIntegralDao integralDao;
	@Autowired
	private IUserDao userDao;

	public Integral getOne(int id) {
		return integralDao.selectOneById(id);
	}

	/**
	 * 
	 * @param userId
	 * @param channel
	 * @param score
	 * @return 
	 * TODO 这个方法移出，重构到普通的积分获取方法里面
	 */
	public int addIntegral(String userId, String channel, int score) {
		Integral i = new Integral();
		i.setUserId(userId);
		i.setChannel(channel);
		i.setScore(score);
		i.setCreateTime(new Date());
		return integralDao.insert(i);
	}

	public int countByUserIdAndChannelCodeAndCreateTime(String userId, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 先查询用户信息，再根据用户信息去查询积分记录
	 * 如果查询不到用户信息，返回null
	 * @param clientUserId 不可为空
	 * @param pageableObj
	 * @return
	 * @throws Exception 
	 */
	public Page<Integral> getListByClientUserId(String clientUserId, Pageable pageableObj) throws Exception {
		if (StringUtils.isBlank(clientUserId)) {
			throw new Exception("userId is not be null or empty");
		}
		User user = userDao.selectOneByClientUserId(clientUserId);
		if (user == null || StringUtils.isBlank(user.getUserId())) {
			return null;
		}
		return integralDao.getListByUserId(user.getUserId(), pageableObj);
	}
	/**
	 * userId如果为空，则查询全部
	 * @param userId
	 * @param pageableObj
	 * @return
	 */
	public Page<Integral> getList(String userid, Pageable pageableObj) {
		return integralDao.getListByUserId(userid, pageableObj);
	}
	public Page<Integral> getList(Pageable pageableObj) {
		return integralDao.getAllList(pageableObj);
	}
}
