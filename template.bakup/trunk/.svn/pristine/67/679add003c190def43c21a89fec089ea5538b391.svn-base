package com.cjteam.mrile.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IAnnouncementDao;
import com.cjteam.mrile.model.Announcement;
import com.cjteam.mrile.model.AnnouncementExample;
import com.cjteam.mrile.persistence.AnnouncementMapper;

@Component
public class AnnouncementDaoImpl implements IAnnouncementDao {

	@Autowired
	private AnnouncementMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Announcement record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Announcement record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<Announcement> getAllList(Pageable pageableObj) {
		AnnouncementExample example = new AnnouncementExample();
		example.setOrderByClause(" sort desc, create_time desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<Announcement> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<Announcement> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<Announcement>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public Announcement selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Announcement selectOneByRecommend() {
		AnnouncementExample example = new AnnouncementExample();
		example.createCriteria().andRecommendEqualTo("recommend");
		example.setLimitClause("LIMIT 1");
		List<Announcement> itemList = mapper.selectByExample(example);
		Announcement item = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			item = itemList.get(0);
		}
		return item;
	}

}