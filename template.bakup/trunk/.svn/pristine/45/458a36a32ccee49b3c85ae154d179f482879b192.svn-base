package com.cjteam.mrile.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.dao.IChannelDao;
import com.cjteam.mrile.model.Channel;

@Service
public class ChannelService extends BaseService {

	@Autowired
	private IChannelDao channelDao;

	public List<Channel> getValidChannels() {
		return channelDao.getAllValidList();
	}
	public Page<Channel> getAllChannels(Pageable pageableObj) {
		return channelDao.getAllList(pageableObj);
	}
	public Channel getOne(int iid) {
		return channelDao.selectOneById(iid);
	}
	public void removeOne(int iid) {
		channelDao.deleteByPrimaryKey(iid);
	}
	public void save(Channel itemParam) {
		if (itemParam.getId() != null && itemParam.getId() > 0) {
			itemParam.setUpdateTime(new Date());
			channelDao.updateByPrimaryKeySelective(itemParam);
		} else {
			//initoperate
			itemParam.setType("REMOAT");
			itemParam.setDailyTimes(100);
			itemParam.setIsValid(false);
			itemParam.setUsed(new Integer(0).byteValue());
			itemParam.setCreateTime(new Date());
			channelDao.insert(itemParam);
		}
		
	}
}
