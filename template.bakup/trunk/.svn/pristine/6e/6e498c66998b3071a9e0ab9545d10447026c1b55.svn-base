package com.cjteam.mrile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.dao.IUserDao;
import com.cjteam.mrile.model.User;

@Service
public class UserService extends BaseService {

	@Autowired
	private IUserDao userDao;

	public User getOne(int id) {
		return userDao.selectOneById(id);
	}

	public User getOneByUserId(String userId) {
		return userDao.selectOneByClientUserId(userId);
	}

	public void addScore(String userId, int score) {
		userDao.addScore(userId, score);
	}

	public boolean consumeScore(String userId, int consumeScore) {
		if (consumeScore <= 0)
			return false;
		int attachedRows = userDao.consumeScore(userId, consumeScore);
		return attachedRows > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	public Page<User> getAll(Pageable pageableObj) {
		return userDao.getAllList(pageableObj);
	}
}
