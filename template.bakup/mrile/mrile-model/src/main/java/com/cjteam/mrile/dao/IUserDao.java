package com.cjteam.mrile.dao;

import com.cjteam.mrile.model.User;

public interface IUserDao extends IBaseDao<User, Integer> {

	public User selectOneByClientUserId(String userId);

	public int consumeScore(String userId, int consumeScore);

	public int addScore(String userId, int score);
}