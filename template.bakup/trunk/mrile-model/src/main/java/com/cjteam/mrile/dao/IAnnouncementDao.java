package com.cjteam.mrile.dao;

import com.cjteam.mrile.model.Announcement;

public interface IAnnouncementDao extends IBaseDao<Announcement, Integer> {

	public Announcement selectOneByRecommend();
}