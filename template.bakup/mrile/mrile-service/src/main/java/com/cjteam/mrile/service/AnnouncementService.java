package com.cjteam.mrile.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cjteam.mrile.dao.IAnnouncementDao;
import com.cjteam.mrile.model.Announcement;

@Service
public class AnnouncementService extends BaseService {

	@Autowired
	private IAnnouncementDao announcementDao;

	public Page<Announcement> getAllList(Pageable pageableObj) {
		return announcementDao.getAllList(pageableObj);
	}

	public Announcement getOne(int id) {
		return announcementDao.selectOneById(id);
	}

	public Announcement getRecommend() {
		return announcementDao.selectOneByRecommend();
	}

	public void save(Announcement itemParam) {
		if (itemParam.getId() != null && itemParam.getId() > 0) {
			announcementDao.updateByPrimaryKeySelective(itemParam);
		} else {
			//initoperate
			itemParam.setCreateTime(new Date());
			announcementDao.insert(itemParam);
		}
		
	}

	public void removeOne(int iid) {
		announcementDao.deleteByPrimaryKey(iid);
	}
}
