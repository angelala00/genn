package com.cjteam.mrile.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IIntegralDao;
import com.cjteam.mrile.model.Integral;
import com.cjteam.mrile.model.IntegralExample;
import com.cjteam.mrile.model.IntegralExample.Criteria;
import com.cjteam.mrile.persistence.IntegralMapper;

@Component
public class IntegralDaoImpl implements IIntegralDao {

	@Autowired
	private IntegralMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Integral record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Integral record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<Integral> getAllList(Pageable pageableObj) {
		return getListByUserId(null, pageableObj);
	}

	@Override
	public Integral selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Integral> getListByUserId(String userId, Pageable pageableObj) {
		IntegralExample example = new IntegralExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNoneBlank(userId)) {
			c.andUserIdEqualTo(userId);
		}
		example.setOrderByClause("id desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<Integral> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<Integral> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<Integral>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

}