package com.cjteam.mrile.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IChannelDao;
import com.cjteam.mrile.model.Channel;
import com.cjteam.mrile.model.ChannelExample;
import com.cjteam.mrile.persistence.ChannelMapper;

@Component
public class ChannelDaoImpl implements IChannelDao {

	@Autowired
	private ChannelMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Channel record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Channel record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<Channel> getAllList(Pageable pageableObj) {
		ChannelExample example = new ChannelExample();
		example.setOrderByClause(" order_ desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<Channel> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<Channel> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<Channel>(itemList, pageableObj, total);
		}
		return itemsPage;
	}
	@Override
	public List<Channel> getAllValidList() {
		ChannelExample example = new ChannelExample();
		example.createCriteria().andIsValidEqualTo(true);
		example.setOrderByClause(" order_ desc");
		List<Channel> itemList = mapper.selectByExample(example);
		return itemList;
	}

	@Override
	public Channel selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
}