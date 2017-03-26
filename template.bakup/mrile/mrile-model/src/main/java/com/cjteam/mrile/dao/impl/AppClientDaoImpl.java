package com.cjteam.mrile.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IAppClientDao;
import com.cjteam.mrile.model.AppClient;
import com.cjteam.mrile.model.AppClientExample;
import com.cjteam.mrile.persistence.AppClientMapper;

@Component
public class AppClientDaoImpl implements IAppClientDao {

	@Autowired
	private AppClientMapper mapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AppClient record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(AppClient record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<AppClient> getAllList(Pageable pageableObj) {
		AppClientExample example = new AppClientExample();
		example.setOrderByClause(" id desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<AppClient> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<AppClient> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<AppClient>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public AppClient selectOneById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

}