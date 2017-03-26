package com.cjteam.mrile.dao;

import java.util.List;

import com.cjteam.mrile.model.Channel;

public interface IChannelDao extends IBaseDao<Channel, Integer> {
	public List<Channel> getAllValidList();
}